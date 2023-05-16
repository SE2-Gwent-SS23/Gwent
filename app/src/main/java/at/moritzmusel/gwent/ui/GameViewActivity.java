package at.moritzmusel.gwent.ui;

import static at.moritzmusel.gwent.network.Utils.Logger.e;
import static at.moritzmusel.gwent.network.Utils.Logger.i;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.nearby.Nearby;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.network.CHAOS.Network;
import at.moritzmusel.gwent.network.CHAOS.NetworkInstance;
import at.moritzmusel.gwent.network.CHAOS.TriggerValueChangeListener;
import at.moritzmusel.gwent.network.data.GameState;


public class GameViewActivity extends AppCompatActivity {
    private static final String TAG = "GameViewActivity";
    private Button buttonOpponentCards;
    private PopupWindow popupWindow;
    private Dialog lobbyDialog;
    private Context context;
    
    //variables for shake sensor
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    //Network stuff
    private Network network;
    private String sessionType = "";
    private final String[] REQUIRED_PERMISSIONS;
    private static ActivityResultLauncher<String[]> requestMultiplePermissions;
    private TriggerValueChangeListener onConnectionSuccessfullTrigger;

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
                    }
                    else recreate();
                });

        Log.i(TAG, Arrays.toString(REQUIRED_PERMISSIONS));
    }
    // end

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

        sessionType = getIntent().getExtras().getString("lobby_type");

        context = getApplicationContext();
        buttonOpponentCards = (Button) findViewById(R.id.buttonOpponentCards);
        buttonOpponentCards.setOnTouchListener(new OnSwipeTouchListener(this, findViewById(R.id.buttonOpponentCards)) {
            @Override
            void onSwipeTop() {
                buttonOpponentCards.performClick();
                buttonOpponentCards.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_keyboard_arrow_down_24));
                popupWindow.dismiss();
                super.onSwipeTop();
            }

            @Override
            void onSwipeBottom() {
                buttonOpponentCards.performClick();
                buttonOpponentCards.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_keyboard_arrow_up_24));
                onButtonShowPopupWindowClick(getWindow().getDecorView().getRootView());
                super.onSwipeBottom();
            }
        });



        List<Card> userCards = new ArrayList<>();
        userCards.add(new Card(1, 2, false, true));
        userCards.add(new Card(2, 3, false, true));
        userCards.add(new Card(3, 4,false, true));
        userCards.add(new Card(1, 5, false, true));
        userCards.add(new Card(2, 6, false, true));
        userCards.add(new Card(3, 7, false, true));
        userCards.add(new Card(1, 8, false, true));
        userCards.add(new Card(2, 17, false, true));
        userCards.add(new Card(3, 16, false, true));
        userCards.add(new Card(1, 15, false, true));

        List<Card> list2 = new ArrayList<>();
        list2.add(new Card(1, 18, false, false));

        List<Card> list3 = new ArrayList<>();
        list3.add(new Card(1, 19, true, false));

        List<Card> list4 = new ArrayList<>();
        list4.add(new Card(1, 20, false, false));

        List<Card> list5 = new ArrayList<>();
        list5.add(new Card(1, 21, false, false));

        setCards(R.id.recyclerViewCardOpponentLaneOne, list2);
        setCards(R.id.recyclerViewCardOpponentLaneTwo, list3);

        setUserCards(userCards);
        setCards(R.id.recyclerViewCardUserLaneOne, list4);
        setCards(R.id.recyclerViewCardUserLaneTwo, list5);

//        RecyclerView lineOneRecyclerView = findViewById(R.id.recyclerViewCardOpponentLaneOne);
//        UserCardAdapter adapterLineOne = new UserCardAdapter(list2, getApplicationContext());
//        lineOneRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManagerLaneOne = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        lineOneRecyclerView.setLayoutManager(linearLayoutManagerLaneOne);
//        lineOneRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        lineOneRecyclerView.setAdapter(adapterLineOne);
//        lineOneRecyclerView.setOnDragListener(adapterLineOne.getDragInstance());
//
//        RecyclerView lineTwoRecyclerView = findViewById(R.id.recyclerViewCardOpponentLaneTwo);
//        UserCardAdapter adapterLineTwo = new UserCardAdapter(list3, getApplicationContext());
//        lineTwoRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManagerLaneTwo = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        lineTwoRecyclerView.setLayoutManager(linearLayoutManagerLaneTwo);
//        lineTwoRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        lineTwoRecyclerView.setAdapter(adapterLineTwo);
//        lineTwoRecyclerView.setOnDragListener(adapterLineTwo.getDragInstance());
//
//        RecyclerView lineThreeRecyclerView = findViewById(R.id.recyclerViewCardUserLaneOne);
//        UserCardAdapter adapterLineThree = new UserCardAdapter(list4, getApplicationContext());
//        lineThreeRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManagerLaneThree = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        lineThreeRecyclerView.setLayoutManager(linearLayoutManagerLaneThree);
//        lineThreeRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        lineThreeRecyclerView.setAdapter(adapterLineThree);
//        lineThreeRecyclerView.setOnDragListener(adapterLineThree.getDragInstance());
//
//        RecyclerView lineFourRecyclerView = findViewById(R.id.recyclerViewCardUserLaneTwo);
//        UserCardAdapter adapterLineFour = new UserCardAdapter(list5, getApplicationContext());
//        lineFourRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManagerLaneFour = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        lineFourRecyclerView.setLayoutManager(linearLayoutManagerLaneFour);
//        lineFourRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        lineFourRecyclerView.setAdapter(adapterLineFour);
//        lineFourRecyclerView.setOnDragListener(adapterLineFour.getDragInstance());


        //shake sensor initialisation
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        //NETWORKING
        onConnectionSuccessfullTrigger = value -> {
            if((Boolean) value){
                if (lobbyDialog.isShowing()) {
                    lobbyDialog.dismiss();
                    RedrawActivity.showRedraw(GameViewActivity.this, userCards);
                }
            }else {
                startActivity(new Intent(this, MainMenuActivity.class));
                Toast.makeText(this, "Error creating/hosting lobby.", Toast.LENGTH_LONG).show();
            }
        };
        network = NetworkInstance.getInstance(Nearby.getConnectionsClient(this), this, onConnectionSuccessfullTrigger);
        lobbyDialog = new Dialog(this);
        lobbyDialog.setContentView(R.layout.lobby_window);
        showLobbyPopup();

        network.getCurrentState().observeForever(gameState -> {
            i(TAG + " From Network:", gameState.toString());
        });
    }

    public void setCards(int recyclerViewUserCardStack, List<Card> cards) {
        setCards(findViewById(recyclerViewUserCardStack), cards, getApplicationContext(), GameViewActivity.this);
//        RecyclerView userRecyclerView = findViewById(recyclerViewUserCardStack);
//        UserCardAdapter adapterUser = new UserCardAdapter(cards, getApplicationContext());
//        userRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        userRecyclerView.setLayoutManager(linearLayoutManagerUser);
//        userRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        userRecyclerView.setAdapter(adapterUser);
//        userRecyclerView.setOnDragListener(adapterUser.getDragInstance());
    }

    public static void setCards(RecyclerView view, List<Card> cards, Context context, Activity parentActivity) {
        setCards(view, cards, context, parentActivity, null);
    }
    public static void setCards(RecyclerView view, List<Card> cards, Context context, Activity parentActivity, View.OnDragListener dragListener) {
        UserCardAdapter adapterUser = new UserCardAdapter(cards, context);
        view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(parentActivity, LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(linearLayoutManagerUser);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setAdapter(adapterUser);
        if (dragListener == null){
            view.setOnDragListener(adapterUser.getDragInstance());
        } else{
            view.setOnDragListener(dragListener);
        }
    }

    public void setUserCards(List<Card> cards) {
        setCards(R.id.recyclerViewUserCardStack, cards);
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

        // Compliant for security-sensitive use cases
        SecureRandom random = new SecureRandom();
        int bound = 10;
        int size = random.nextInt(bound);

        if (size == 1) {
            ImageView im = new ImageView(view.getContext());
            im.setPadding(10, 10, 10, 10);
            setImageFromAssetForOpponent(im);
            llOpponent.addView(im);
        } else {
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

    public void refreshUserHandCards(){
        UserCardAdapter adapter = (UserCardAdapter) ((RecyclerView) findViewById(R.id.recyclerViewUserCardStack)).getAdapter();
        adapter.notifyDataSetChanged();
        // Kein plan ob das hierher kommt???
        GameState gs = network.getCurrentState().getValue();
        gs.setMyHand(new ArrayList<>());
        network.play(gs);
        //
    }

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

        if(sessionType.equals("join")){
            lobbyText.setText("Join Lobby");
            infoText.setText("Searching for a game...");
            network.startDiscovering();
        }else if(sessionType.equals("create")){
            lobbyText.setText("Create Lobby");
            infoText.setText("Creating game. Looking for opponents...");
            network.startHosting();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!hasPermissions(this, REQUIRED_PERMISSIONS)){
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
}