package at.moritzmusel.gwent.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;

public class GameViewActivity extends AppCompatActivity {

    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll, llOpponent;
    private String msg;
    private android.widget.LinearLayout.LayoutParams layoutParams;
    private Button buttonOpponentCards;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

        buttonOpponentCards = (Button) findViewById(R.id.buttonOpponentCards);
        buttonOpponentCards.setOnTouchListener(new OnSwipeTouchListener(this, findViewById(R.id.buttonOpponentCards)) {
            @Override
            void onSwipeTop() {
                buttonOpponentCards.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_keyboard_arrow_down_24));
                popupWindow.dismiss();
                super.onSwipeTop();
            }

            @Override
            void onSwipeBottom() {
                buttonOpponentCards.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_keyboard_arrow_up_24));
                onButtonShowPopupWindowClick(getWindow().getDecorView().getRootView());
                super.onSwipeBottom();
            }
        });

        List<Card> list1 = new ArrayList<>();
        list1.add(new Card(1, 2));
        list1.add(new Card(2, 3));
        list1.add(new Card(3, 4));
        list1.add(new Card(1, 5));
        list1.add(new Card(2, 6));
        list1.add(new Card(3, 7));
        list1.add(new Card(1, 8));
        list1.add(new Card(2, 17));
        list1.add(new Card(3, 16));
        list1.add(new Card(1, 15));

        List<Card> list2 = new ArrayList<>();
        list2.add(new Card(1, 18));

        List<Card> list3 = new ArrayList<>();
        list3.add(new Card(1, 19));

        List<Card> list4 = new ArrayList<>();
        list4.add(new Card(1, 20));

        List<Card> list5 = new ArrayList<>();
        list5.add(new Card(1, 21));

        RecyclerView userRecyclerView = findViewById(R.id.recyclerViewUserCardStack);
        UserCardAdapter adapterUser = new UserCardAdapter(list1, getApplicationContext());
        userRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        userRecyclerView.setLayoutManager(linearLayoutManagerUser);
        userRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userRecyclerView.setAdapter(adapterUser);
        userRecyclerView.setOnDragListener(adapterUser.getDragInstance());

        RecyclerView lineOneRecyclerView = findViewById(R.id.recyclerViewCardLineOne);
        UserCardAdapter adapterLineOne = new UserCardAdapter(list2, getApplicationContext());
        lineOneRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneOne = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineOneRecyclerView.setLayoutManager(linearLayoutManagerLaneOne);
        lineOneRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineOneRecyclerView.setAdapter(adapterLineOne);
        lineOneRecyclerView.setOnDragListener(adapterLineOne.getDragInstance());

        RecyclerView lineTwoRecyclerView = findViewById(R.id.recyclerViewCardLineTwo);
        UserCardAdapter adapterLineTwo = new UserCardAdapter(list3, getApplicationContext());
        lineTwoRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneTwo = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineTwoRecyclerView.setLayoutManager(linearLayoutManagerLaneTwo);
        lineTwoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineTwoRecyclerView.setAdapter(adapterLineTwo);
        lineTwoRecyclerView.setOnDragListener(adapterLineTwo.getDragInstance());

        RecyclerView lineThreeRecyclerView = findViewById(R.id.recyclerViewCardLineThree);
        UserCardAdapter adapterLineThree = new UserCardAdapter(list4, getApplicationContext());
        lineThreeRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneThree = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineThreeRecyclerView.setLayoutManager(linearLayoutManagerLaneThree);
        lineThreeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineThreeRecyclerView.setAdapter(adapterLineThree);
        lineThreeRecyclerView.setOnDragListener(adapterLineThree.getDragInstance());

        RecyclerView lineFourRecyclerView = findViewById(R.id.recyclerViewCardLineFour);
        UserCardAdapter adapterLineFour = new UserCardAdapter(list5, getApplicationContext());
        lineFourRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLaneFour = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lineFourRecyclerView.setLayoutManager(linearLayoutManagerLaneFour);
        lineFourRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineFourRecyclerView.setAdapter(adapterLineFour);
        lineFourRecyclerView.setOnDragListener(adapterLineFour.getDragInstance());
    }

    private void setImageFromAssetForOpponent(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) getDrawable(R.drawable.card_deck_back_opponent_right)).getBitmap();
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
        llOpponent = popupView.findViewById(R.id.linearLayoutMainCardsDeckOpponent);

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
}
