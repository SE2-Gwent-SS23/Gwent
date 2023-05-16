package at.moritzmusel.gwent.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.network.data.GameState;
import at.moritzmusel.gwent.network.viewmodel.GwentViewModel;

public class GameViewActivity extends AppCompatActivity {

    private Button buttonOpponentCards;
    private static TextView tvMyGrave;
    private static TextView tvOpponentMonster;
    private static TextView tvOpponentGrave;
    private PopupWindow popupWindow;
    private static Context context;

    //variables for shake sensor
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private static GameState gameState;
    private GwentViewModel gwentViewModel;
    private static List<Card> allCardsList;

    // GameState Attributes
    private String myDeck; // = filename
    private String opponentDeck;
    private List<Card> myHand;
    private List<Card> opponentHand;
    private List<Card> myGrave;
    private List<Card> opponentGrave;
    private List<Card> weather;
    private List<Card> myClose; // 3. Reihe
    private Boolean myWeatherClose;
    private List<Card> myRanged; // 4. Reihe
    private Boolean myWeatherRanged;
    private List<Card> opponentClose; // 2. Reihe
    private Boolean opponentWeatherClose;
    private List<Card> opponentRanged; // 1. Reihe
    private Boolean opponentWeatherRanged;
    private Card myLeader;
    private Boolean usedMyLeader;
    private Card opponentLeader;
    private Boolean usedOpponentLeader;

    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        tvMyGrave = findViewById(R.id.tvMyGrave);

        context = this.getApplicationContext();
        gameState = new GameState();
        // Fill all Cards
        try {
            this.allCardsList = new ArrayList<>();
            fillAllCardsIntoList();
            // Init Game State
            initGameState();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //gwentViewModel.newGame();
        //gwentViewModel.play(gameState);

        buttonOpponentCards = findViewById(R.id.buttonOpponentCards);
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

        try {
            setCards(R.id.recyclerViewCardOpponentLaneOne, false, this.opponentRanged);
            setCards(R.id.recyclerViewCardOpponentLaneTwo, false, this.opponentClose);
            setUserCards(this.myHand);
            setCards(R.id.recyclerViewCardUserLaneOne, false, this.myClose);
            setCards(R.id.recyclerViewCardUserLaneTwo, false, this.myRanged);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //shake sensor initialisation
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        RedrawActivity.showRedraw(this, this.myHand, this.gameState);
    }

    private void fillAllCardsIntoList() throws JSONException, IOException {
        String name;
        int strength;
        int count;
        String flavor_txt;
        String filename;
        JSONObject jsonObject = new JSONObject(loadCardJSONFromAsset());
        JSONArray jsonArray = jsonObject.optJSONArray("cards");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            name = obj.optString("name");
            strength = Integer.parseInt(obj.optString("strength"));
            filename = obj.optString("filename");
            count = Integer.parseInt(obj.optString("count"));
            flavor_txt = obj.optString("flavor_txt");

            Card newCard = new Card();
            newCard.setName(name);
            newCard.setStrength(strength);
            newCard.setCount(count);
            newCard.setFilename(filename);
            newCard.setFlavor_txt(flavor_txt);
            System.out.println(obj.optString("type"));
            newCard.changeType(obj.optString("type"));
            newCard.changeRow(obj.optString("row"));
            newCard.changeAbility(obj.optString("ability"));
            allCardsList.add(newCard);

        }
    }

    private String loadCardJSONFromAsset() throws IOException {
        String jsonString = null;
        InputStream is = null;
        try {
            is = context.getAssets().open("cards.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            while (is.read(buffer) > 0) {
                jsonString = new String(buffer, "UTF-8");
            }
            if (is != null) is.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        } finally {
            if (is != null) is.close();
        }

        return jsonString;
    }

    private void initGameState() throws JSONException, IOException {

        SecureRandom random = new SecureRandom();
        int zz;

        // myHand
        this.myHand = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            zz = random.nextInt(this.allCardsList.size());
            Card card = this.allCardsList.get(zz);
            while (card.getCount() == 0) {
                zz = random.nextInt(this.allCardsList.size());
                card = this.allCardsList.get(zz);
            }

            this.myHand.add(card);
            this.allCardsList.get(i).setCount(card.getCount() - 1);
        }

        // Ranged
        this.myRanged = new ArrayList<>();
        this.opponentRanged = new ArrayList<>();

        // Close
        this.myClose = new ArrayList<>();
        this.opponentClose = new ArrayList<>();

        // opponentHand
        this.opponentHand = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            zz = random.nextInt(this.allCardsList.size());
            Card card = this.allCardsList.get(zz);
            while (card.getCount() == 0) {
                zz = random.nextInt(this.allCardsList.size());
                card = this.allCardsList.get(zz);
            }

            this.opponentHand.add(card);
            this.allCardsList.get(i).setCount(card.getCount() - 1);
        }

        // Grave
        this.myGrave = new ArrayList<>();
        this.opponentGrave = new ArrayList<>();

        gameState.setMyHand(this.myHand);
        gameState.setMyClose(this.myClose);
        gameState.setMyGrave(this.myGrave);
        gameState.setMyRanged(this.myRanged);
        gameState.setOpponentHand(this.opponentHand);
        gameState.setOpponentClose(this.opponentClose);
        gameState.setOpponentGrave(this.opponentGrave);
        gameState.setOpponentRanged(this.opponentRanged);
        updateUI();

    }

    public void setCards(int recyclerViewUserCardStack, Boolean isMyHand, List<Card> cards) throws JSONException, IOException {
        setCards(findViewById(recyclerViewUserCardStack), isMyHand, cards, getApplicationContext(), GameViewActivity.this, gameState);
    }

    public static void setCards(RecyclerView view, Boolean isMyHand, List<Card> cards, Context context, Activity parentActivity, GameState gameState) throws JSONException, IOException {
        setCards(view, isMyHand, cards, context, parentActivity, null, gameState);
    }

    public static void setCards(RecyclerView view, Boolean isMyHand, List<Card> cards, Context context, Activity parentActivity, View.OnDragListener dragListener, GameState gameState) throws JSONException, IOException {
        UserCardAdapter adapterLanes = new UserCardAdapter(cards, isMyHand, context, gameState);
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

    public void refreshUserHandCards() {
        UserCardAdapter adapter = (UserCardAdapter) ((RecyclerView) findViewById(R.id.recyclerViewUserCardStack)).getAdapter();
        adapter.notifyDataSetChanged();
    }

    public static List<Card> getAllCardsList() {
        return allCardsList;
    }

    public static void updateAllCardsList(List<Card> list) {
        allCardsList = list;
    }

    public static void updateUI() {
        tvMyGrave.setText(gameState.getMyGrave().size() + "");

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_opponent, null);
        LinearLayout llOpponent = popupView.findViewById(R.id.linearLayoutMainCardsDeckOpponent);

        tvOpponentMonster = popupView.findViewById(R.id.tvOpponentMonsters);
        tvOpponentMonster.setText(gameState.getOpponentHand().size() + "");
        tvOpponentGrave = popupView.findViewById(R.id.tvOpponentGrave);
        tvOpponentGrave.setText(gameState.getOpponentGrave().size() + "");
    }
}