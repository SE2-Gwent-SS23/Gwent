package at.moritzmusel.gwent.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;

public class RedrawActivity extends AppCompatActivity {
    private static List<Card> sPlayerCards; //prototyp
    private static GameViewActivity sGameViewAdapter;//prototyp
    private List<Card> mPlayerCards;
    private List<List<Card>> mRedrawCards;
    TextView mRedrawDropView;
    TextView mRedrawCountView;
    int mRedrawCount = 0;
    SecureRandom mRandom = new SecureRandom();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_redraw);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPlayerCards = sPlayerCards; //b.getSerializable("cards");

        mRedrawCountView = findViewById(R.id.txtRedrawCount);
        mRedrawDropView = findViewById(R.id.txtRedrawDrop);
        RedrawDragListener listener = new RedrawDragListener(this, mRedrawDropView);
        mRedrawDropView.setOnDragListener(listener);

        mRedrawCards = halveList(mPlayerCards);
        GameViewActivity.setCards(findViewById(R.id.redrawUserCards1), mRedrawCards.get(0), getApplicationContext(), this, listener);
        GameViewActivity.setCards(findViewById(R.id.redrawUserCards2), mRedrawCards.get(1), getApplicationContext(), this, listener);


        //findViewById(R.id.redrawUserCards1).setOnDragListener(new RedrawDragListener());
        //findViewById(R.id.btnEndRedraw).setOnDragListener(new RedrawDragListener());
    }

    public static void showRedraw(GameViewActivity gameView, List<Card> playerCards) {
        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
//                Intent intent = new Intent(gameView, RedrawActivity.class);
//                Bundle b = new Bundle();
//                b.putSerializable("cards", playerCards); //todo: checken wie man list übergeben kann
//                intent.putExtras(b);
//                gameView.startActivity(intent);
                sPlayerCards = playerCards; //workaround
                sGameViewAdapter = gameView;
                gameView.startActivity(new Intent(gameView, RedrawActivity.class));

            }
        }.start();
    }

    public void onClickCloseRedraw(View view) {
        //mPlayerCards.set(0, new Card(6, 10, false, true));
        //sPlayerCards.set(0, new Card(7,22,false, true));

        for (int i = 0; i < mRedrawCards.size(); i++) {
            for (int j = 0; j < mRedrawCards.get(i).size(); j++) {
                mPlayerCards.set((j + i * 5), mRedrawCards.get(i).get(j));
            }
        }
        sGameViewAdapter.refreshUserHandCards();

        //RecyclerView userHandCards = findViewById(R.id.recyclerViewUserCardStack);
        //GameViewActivity.setCards(userHandCards,

        finish();
    }

    public List<List<Card>> halveList(List<Card> list) {
        int marker = list.size() / 2;
        List<Card> firstHalf = new ArrayList<Card>();
        List<Card> secondHalf = new ArrayList<Card>();

        for (int i = 0; i < list.size(); i++) {
            if (i < marker) {
                firstHalf.add(list.get(i));
            } else {
                secondHalf.add(list.get(i));
            }
        }
        List<List<Card>> result = new ArrayList<List<Card>>();
        result.add(firstHalf);
        result.add(secondHalf);

        return result;
    }

    public void replaceCard(View cardView) {
        if (mRedrawCount <= 2) {
            UserCardAdapter adapter = (UserCardAdapter) ((RecyclerView) cardView.getParent()).getAdapter();
            int cardPos = (int) cardView.getTag();
            //Card card = adapter.getList().get(cardPos);
            List<Card> listSource = adapter.getList();
            listSource.set(cardPos, drawRandomCard());
            adapter.updateList(listSource);
            adapter.notifyDataSetChanged();
            mRedrawCount++;
            mRedrawCountView.setText(mRedrawCount + "/3");
        }
        if (mRedrawCount == 3) {
            mRedrawDropView.setText("Bitte auf Fertig tippen");
        }
    }

    private Card drawRandomCard() {
        int img = mRandom.nextInt(214);
        int pts = mRandom.nextInt(7);
        return new Card(pts + 1, img, false, true);
    }

}

