package at.moritzmusel.gwent.ui;

import static at.moritzmusel.gwent.network.Utils.Logger.i;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.nearby.Nearby;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.network.CHAOS.Network;
import at.moritzmusel.gwent.network.CHAOS.NetworkInstance;
import at.moritzmusel.gwent.network.CHAOS.TriggerValueChange;
import at.moritzmusel.gwent.network.CHAOS.TriggerValueChangeListener;
import at.moritzmusel.gwent.network.data.GameState;


public class GameViewActivity extends AppCompatActivity {
    private List<RecyclerView> recyclerViews;
    private static final String TAG = "GameViewActivity";
    private Button buttonOpponentCards;
    private static TextView tvMyGrave;
    private static TextView tvOpponentMonster;
    private static TextView tvOpponentGrave;
    private PopupWindow popupWindow;
    private Dialog lobbyDialog;
    private static Context context;

    private GameState gameState;
    private static int deviceHeight;
    private int buttonHelp = 0;

    // variables for shake sensor
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    // network variables
    private Network network;
    private String sessionType = "";
    private final String[] REQUIRED_PERMISSIONS;
    private static ActivityResultLauncher<String[]> requestMultiplePermissions;
    private TriggerValueChangeListener onConnectionSuccessfullTrigger;
    private TriggerValueChange waitingCallback = new TriggerValueChange();
    public static TriggerValueChange gameStateUpdate = new TriggerValueChange();

    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            REQUIRED_PERMISSIONS = new String[]{
                    android.Manifest.permission.BLUETOOTH_SCAN,
                    android.Manifest.permission.BLUETOOTH_ADVERTISE,
                    android.Manifest.permission.BLUETOOTH_CONNECT,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.NEARBY_WIFI_DEVICES
            };
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            REQUIRED_PERMISSIONS = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};
        } else {
            REQUIRED_PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
        }

        requestMultiplePermissions = this.registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                permissions -> {
                    if (permissions.entrySet().stream().anyMatch(val -> !val.getValue())) {
                        Log.e(TAG, "Missing permissions");
                        Toast.makeText(this, "Required permissions needed. Go to settings!", Toast.LENGTH_LONG).show();
                        finish();
                    } else recreate();
                });

        Log.i(TAG, Arrays.toString(REQUIRED_PERMISSIONS));

        waitingCallback.setListener(value -> {
            GameState g = (GameState) value;

            if (g.getOpponentHand() != null) {
                try {
                    //setCards here
                    setCards(R.id.recyclerViewCardOpponentLaneOne, false, this.gameState.getOpponentRanged());
                    setCards(R.id.recyclerViewCardOpponentLaneTwo, false, this.gameState.getOpponentClose());
                    setUserCards(this.gameState.getMyHand());
                    setCards(R.id.recyclerViewCardUserLaneOne, false, this.gameState.getMyClose());
                    setCards(R.id.recyclerViewCardUserLaneTwo, false, this.gameState.getMyRanged());
                    i("Callback", this.gameState.toString());
                    enableDisableYourTurn(true);
                    updateUI(gameState);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gameStateUpdate.setListener((value -> {
            this.gameState = (GameState) value;
            network.currentState.setValue(this.gameState);
            try {
                enableDisableYourTurn(false);
            } catch (JSONException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
            network.sendGameState((GameState) value);
        }));
    }
    // end


    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

        this.context = this.getApplicationContext();
        this.gameState = new GameState(0, 0, 0, false);

        this.tvMyGrave = findViewById(R.id.tvMyGrave);

        try {
            this.gameState.initGameState();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.gameState.initAllCards(this.getApplicationContext());

        // adding views to list
        initRecyclerViewsToList();

        sessionType = getIntent().getExtras().getString("lobby_type");
        settingResponsiveGameBoard();

        initClickOpponentCardsListener();
        initShakeSensor();
        doNetworking();
    }

    private void initClickOpponentCardsListener() {
        buttonOpponentCards.setOnClickListener(view -> {
            if((buttonHelp++)%2==0) {
                buttonOpponentCards.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_keyboard_arrow_up_24));
                onButtonShowPopupWindowClick(getWindow().getDecorView().getRootView());
            } else {
                buttonOpponentCards.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_keyboard_arrow_down_24));
                popupWindow.dismiss();
            }
        });
    }

    private void initRecyclerViewsToList() {
        this.recyclerViews = new ArrayList<>();
        this.recyclerViews.add(findViewById(R.id.recyclerViewCardOpponentLaneOne));
        this.recyclerViews.add(findViewById(R.id.recyclerViewCardOpponentLaneTwo));
        this.recyclerViews.add(findViewById(R.id.recyclerViewCardUserLaneOne));
        this.recyclerViews.add(findViewById(R.id.recyclerViewCardUserLaneTwo));
        this.recyclerViews.add(findViewById(R.id.recyclerViewUserCardStack));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("gameState")) {
                this.gameState = (GameState) data.getSerializableExtra("gameState");
                // send/receive gamestate here to receive hand
                // call to send
                // merge gamestate
                GameState gs = network.getCurrentState().getValue();
                gs.setMyHand(gameState.getMyHand());
                waitingCallback.setValue(gs);
                network.sendGameState(gs);
                //call to receive
                Toast.makeText(this, "Waiting for opponent hand.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void doNetworking() {
        onConnectionSuccessfullTrigger = value -> {
            if ((Boolean) value) {
                if (lobbyDialog.isShowing()) {
                    lobbyDialog.dismiss();
                    Intent redrawActivityIntent = new Intent(GameViewActivity.this, RedrawActivity.class);
                    redrawActivityIntent.putExtra("gameState", this.gameState);
                    startActivityForResult(redrawActivityIntent, 123);
                }
            } else {
                startActivity(new Intent(this, MainMenuActivity.class));
                Toast.makeText(this, "Error creating/hosting lobby.", Toast.LENGTH_LONG).show();
            }
        };
        network = NetworkInstance.getInstance(Nearby.getConnectionsClient(this), this, onConnectionSuccessfullTrigger);
        lobbyDialog = new Dialog(this);
        lobbyDialog.setContentView(R.layout.lobby_window);
        showLobbyPopup();

        network.getCurrentState().observeForever(gameState -> {
            this.waitingCallback.setValue(gameState);
            i(TAG + " From Network:", gameState.toString());
        });
    }

    private void initShakeSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    private void settingResponsiveGameBoard() {
        /* Setting responsive bounds of the game lanes */
        RecyclerView rvOpponentOne = findViewById(R.id.recyclerViewCardOpponentLaneOne);
        RecyclerView rvOpponentTwo = findViewById(R.id.recyclerViewCardOpponentLaneTwo);
        RecyclerView rvUserOne = findViewById(R.id.recyclerViewCardUserLaneOne);
        RecyclerView rvUserTwo = findViewById(R.id.recyclerViewCardUserLaneTwo);
        RecyclerView rvUser = findViewById(R.id.recyclerViewUserCardStack);
        buttonOpponentCards = findViewById(R.id.buttonOpponentCards);

        // Creating and initializing variable for display metrics
        DisplayMetrics displayMetrics = new DisplayMetrics();

        // On below line we are getting metrics for display using window manager.
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // on below line we are getting height and width using display metrics.
        this.deviceHeight = displayMetrics.heightPixels;
        int deviceWidth = displayMetrics.widthPixels;

        // Setting bounds for lanes
        rvOpponentOne.getLayoutParams().width = deviceWidth / 2;
        rvOpponentOne.getLayoutParams().height = deviceHeight / 6;
        rvOpponentTwo.getLayoutParams().width = deviceWidth / 2;
        rvOpponentTwo.getLayoutParams().height = deviceHeight / 6;
        rvUserOne.getLayoutParams().width = deviceWidth / 2;
        rvUserOne.getLayoutParams().height = deviceHeight / 6;
        rvUserTwo.getLayoutParams().width = deviceWidth / 2;
        rvUserTwo.getLayoutParams().height = deviceHeight / 6;
        rvUser.getLayoutParams().height = deviceHeight / 6;
    }

    public void setCards(int recyclerViewUserCardStack, Boolean isMyHand, List<Card> cards) throws JSONException, IOException {
        setCards(findViewById(recyclerViewUserCardStack), isMyHand, cards, getApplicationContext(), GameViewActivity.this, gameState);
    }

    public static void setCards(RecyclerView view, Boolean isMyHand, List<Card> cards, Context context, Activity parentActivity, GameState gameState) throws JSONException, IOException {
        setCards(view, isMyHand, cards, context, parentActivity, null, gameState);
    }

    public static void setCards(RecyclerView view, Boolean isMyHand, List<Card> cards, Context context, Activity parentActivity, View.OnDragListener dragListener, GameState gameState) throws JSONException, IOException {
        UserCardAdapter adapterLanes = new UserCardAdapter(cards, isMyHand, context, deviceHeight / 6, gameState);
        view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(parentActivity, LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(linearLayoutManagerUser);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setAdapter(adapterLanes);
        if (dragListener == null) {
            view.setOnDragListener(adapterLanes.getDragInstance());
        } else {
            view.setOnDragListener(dragListener);
        }
    }

    public void setUserCards(List<Card> cards) throws JSONException, IOException {
        setCards(R.id.recyclerViewUserCardStack, true, cards);
    }

    private void setImageFromAssetForOpponent(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) AppCompatResources.getDrawable(this.context, R.drawable.card_deck_back_opponent_right)).getBitmap();
        Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 70, true));
        image.setImageDrawable(dr);
    }

    /**
     * Pop-op windows of cards from opponent.
     * At the moment the number of cards is randomly chosen.
     *
     * @param view
     */
    public void onButtonShowPopupWindowClick(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_opponent, null);
        LinearLayout llOpponent = popupView.findViewById(R.id.linearLayoutMainCardsDeckOpponent);

        int size = gameState.getOpponentHand().size();

        for (int i = 0; i < size; i++) {
            ImageView im = new ImageView(view.getContext());
            if (i == 0) {
                im.setPadding(10, 10, 0, 10);
            } else if (i == size - 1) {
                im.setPadding(10, 10, 10, 10);
            } else {
                im.setPadding(10, 10, 0, 10);
            }
            setImageFromAssetForOpponent(im);
            llOpponent.addView(im);
        }

        // important: before getting the size of pop-up we should assign default measurements for the view
        popupView.measure(0, 0);

        // create the popup window
        popupWindow = new PopupWindow(popupView, popupView.getMeasuredWidth(), popupView.getMeasuredHeight(), false);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    //shake sensor listener
    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt(x * x + y * y + z * z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                Toast.makeText(getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
    //END shake sensor listener

    private void showLobbyPopup() {
        TextView lobbyText = lobbyDialog.findViewById(R.id.lobby_text);
        TextView infoText = lobbyDialog.findViewById(R.id.info_text);

        // Set dialog window attributes
        Window window = lobbyDialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        }
        lobbyDialog.show();

        if (sessionType.equals("join")) {
            lobbyText.setText("Join Lobby");
            infoText.setText("Searching for a game...");
            network.startDiscovering();
        } else if (sessionType.equals("create")) {
            lobbyText.setText("Create Lobby");
            infoText.setText("Creating game. Looking for opponents...");
            network.startHosting();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!hasPermissions(this, REQUIRED_PERMISSIONS)) {
            Log.i(TAG, "requestMultiplePermissions called");
            requestMultiplePermissions.launch(REQUIRED_PERMISSIONS);
        }
    }

    private boolean hasPermissions(Activity activity, String[] permissions) {
        List<String> permissionsList = Arrays.asList(permissions);
        return permissionsList.isEmpty() ||
                permissionsList.stream().allMatch(vals -> ContextCompat.checkSelfPermission(activity, vals) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public  void updateUI(GameState gameState) {
        tvMyGrave.setText(gameState.getMyGrave().size() + "");

        // inflate the layout of the popup window
        /*
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_opponent, null);
        LinearLayout llOpponent = popupView.findViewById(R.id.linearLayoutMainCardsDeckOpponent);


        tvOpponentMonster = popupView.findViewById(R.id.tvOpponentMonsters);
        tvOpponentMonster.setText(gameState.getOpponentHand().size() + "");
        tvOpponentGrave = popupView.findViewById(R.id.tvOpponentGrave);
        tvOpponentGrave.setText(gameState.getOpponentGrave().size() + "");

         */
        TextView opponentCardsInHand = findViewById(R.id.tvNumberOfOpponent);
        TextView myCardsInHand = findViewById(R.id.tvNumberOf);

        TextView opponentPoints = findViewById(R.id.tvWhiteFrost);
        TextView myPoints = findViewById(R.id.tvNaturesGift);

        opponentCardsInHand.setText(this.gameState.getOpponentHand().size() + "/10");
        myCardsInHand.setText(this.gameState.getMyHand().size() + "/10");
        opponentPoints.setText(Integer.toString(this.gameState.calculateOpponentPoints()));
        myPoints.setText(Integer.toString(this.gameState.calculateMyPoints()));

    }

    public static Context getContext() {
        return context;
    }

    /**
     * Change Boolean "yourTurn" to enables and disables the "End Turn" button (false to disable)
     * Also disables all relevant DragListeners.
     */
    public void enableDisableYourTurn(boolean yourTurn) throws JSONException, IOException {
         ImageView endTurn = findViewById(R.id.iv_buttonGamePassWaitEndTurn);
        // Create a color filter with a color matrix
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0); // 0 means grayscale
        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        if (!yourTurn) {
            for (RecyclerView view : this.recyclerViews) {
                view.setOnDragListener(null);
            }
            endTurn.setOnClickListener(null);
            endTurn.setColorFilter(colorFilter);

            /* why is it not removing the animation?
            opponentRangedView.setItemAnimator(null);
            opponentCloseView.setItemAnimator(null);
            myCloseView.setItemAnimator(null);
            myRangedView.setItemAnimator(null);
            myHandView.setItemAnimator(null);
             */
        } else {
            for (RecyclerView view : this.recyclerViews) {
                view.setOnDragListener(new DragListener(this.getApplicationContext(), gameState));
            }
            endTurn.setOnClickListener(clickEndTurn());
            endTurn.setColorFilter(null);
            endTurn.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_OVER);
        }
    }

    private View.OnClickListener clickEndTurn() {
        return (view -> {
            try {
                enableDisableYourTurn(false);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}