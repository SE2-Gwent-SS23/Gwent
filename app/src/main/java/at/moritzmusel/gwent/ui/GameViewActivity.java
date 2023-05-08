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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;

public class GameViewActivity extends AppCompatActivity {

    private Button buttonOpponentCards;
    private PopupWindow popupWindow;
    private Context context;
    
    //variables for shake sensor
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

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

        setUserCards(userCards);

//<<<<<<< HEAD
//        setCards(R.id.recyclerViewCardLineOne, list2);
//
//        setCards(R.id.recyclerViewCardLineTwo, list3);
//
//        setCards(R.id.recyclerViewCardLineThree, list4);
//
//        setCards(R.id.recyclerViewCardLineFour, list5);
//=======
        RecyclerView lineOneRecyclerView = findViewById(R.id.recyclerViewCardOpponentLaneOne);
        UserCardAdapter adapterLineOne = new UserCardAdapter(list2, getApplicationContext());
        lineOneRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneOne = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineOneRecyclerView.setLayoutManager(linearLayoutManagerLaneOne);
        lineOneRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineOneRecyclerView.setAdapter(adapterLineOne);
        lineOneRecyclerView.setOnDragListener(adapterLineOne.getDragInstance());

        RecyclerView lineTwoRecyclerView = findViewById(R.id.recyclerViewCardOpponentLaneTwo);
        UserCardAdapter adapterLineTwo = new UserCardAdapter(list3, getApplicationContext());
        lineTwoRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneTwo = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineTwoRecyclerView.setLayoutManager(linearLayoutManagerLaneTwo);
        lineTwoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineTwoRecyclerView.setAdapter(adapterLineTwo);
        lineTwoRecyclerView.setOnDragListener(adapterLineTwo.getDragInstance());

        RecyclerView lineThreeRecyclerView = findViewById(R.id.recyclerViewCardUserLaneOne);
        UserCardAdapter adapterLineThree = new UserCardAdapter(list4, getApplicationContext());
        lineThreeRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneThree = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineThreeRecyclerView.setLayoutManager(linearLayoutManagerLaneThree);
        lineThreeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineThreeRecyclerView.setAdapter(adapterLineThree);
        lineThreeRecyclerView.setOnDragListener(adapterLineThree.getDragInstance());

        RecyclerView lineFourRecyclerView = findViewById(R.id.recyclerViewCardUserLaneTwo);
        UserCardAdapter adapterLineFour = new UserCardAdapter(list5, getApplicationContext());
        lineFourRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneFour = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineFourRecyclerView.setLayoutManager(linearLayoutManagerLaneFour);
        lineFourRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineFourRecyclerView.setAdapter(adapterLineFour);
        lineFourRecyclerView.setOnDragListener(adapterLineFour.getDragInstance());
//>>>>>>> main

        //shake sensor initialisation
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        RedrawActivity.showRedraw(this, userCards);
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
    }
}