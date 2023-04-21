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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.CardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.model.Listener;

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
        list1.add(new Card("1", android.R.drawable.ic_dialog_email));
        list1.add(new Card("2", android.R.drawable.ic_dialog_info));
        list1.add(new Card("3", android.R.drawable.ic_dialog_map));
        list1.add(new Card("1", android.R.drawable.ic_dialog_email));
        list1.add(new Card("2", android.R.drawable.ic_dialog_info));
        list1.add(new Card("3", android.R.drawable.ic_dialog_map));
        list1.add(new Card("1", android.R.drawable.ic_dialog_email));
        list1.add(new Card("2", android.R.drawable.ic_dialog_info));
        list1.add(new Card("3", android.R.drawable.ic_dialog_map));
        list1.add(new Card("1", android.R.drawable.ic_dialog_email));

        List<Card> list2 = new ArrayList<>();

        RecyclerView userRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewUserCardStack);
        CardAdapter adapterUser = new CardAdapter(list1, getApplicationContext());
        userRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GameViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
        userRecyclerView.setLayoutManager(linearLayoutManager);
        userRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userRecyclerView.setAdapter(adapterUser);


        RecyclerView lineOneRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewUserCardStack);
        CardAdapter adapterLineOne = new CardAdapter(list1, getApplicationContext());
        lineOneRecyclerView.setHasFixedSize(true);
        lineOneRecyclerView.setLayoutManager(linearLayoutManager);
        lineOneRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lineOneRecyclerView.setAdapter(adapterLineOne);


        /*ll = findViewById(R.id.linearLayoutMainCardsDeck);

        imageViewList = new ArrayList<>();
        int size = 9; // default value to test the gui

        for (int i = 0; i < size; i++) {

            RelativeLayout childLayout = new RelativeLayout(ll.getContext());

            ImageView im  = new ImageView(ll.getContext());
            TextView tv = new TextView(ll.getContext());

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            im.setId(i + 1); // to be set by backend
            im.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    int x_cord, y_cord;
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                            x_cord = (int) event.getX();
                            y_cord = (int) event.getY();
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                            x_cord = (int) event.getX();
                            y_cord = (int) event.getY();
                            layoutParams.leftMargin = x_cord;
                            layoutParams.topMargin = y_cord;
                            v.setLayoutParams(layoutParams);
                            break;
                        case DragEvent.ACTION_DRAG_LOCATION:
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                            x_cord = (int) event.getX();
                            y_cord = (int) event.getY();
                            break;
                        case DragEvent.ACTION_DRAG_ENDED:
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                            break;
                        case DragEvent.ACTION_DROP:
                            Log.d(msg, "ACTION_DROP event");
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

            im.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(im);

                        im.startDrag(data, shadowBuilder, im, 0);
                        im.setVisibility(View.INVISIBLE);
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            setImageFromAsset(im);
            if(i==0) {
                childLayout.setPadding(10, 10, 0, 10);
            } else if(i == size-1) {
                childLayout.setPadding(10, 10, 10, 10);
            } else {
                childLayout.setPadding(10, 10, 0, 10);
            }
            childLayout.addView(im, params);

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            tv.setId(i+1); // to be set by backend

            tv.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.card_tv_points));
            tv.setText("8");
            tv.setPadding(20,10,20,10);
            tv.setTextSize(15);
            tv.setTextColor(Color.BLACK);
            childLayout.addView(tv, params);

            LinearLayout.LayoutParams parentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(childLayout, parentParams);
        }*/
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

        int size = ThreadLocalRandom.current().nextInt(1, 6);

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
