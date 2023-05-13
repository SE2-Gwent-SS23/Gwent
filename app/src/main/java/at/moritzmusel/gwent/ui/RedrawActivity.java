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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        for (int i = 0; i < mRedrawCards.size(); i++) {
            for (int j = 0; j < mRedrawCards.get(i).size(); j++) {
                mPlayerCards.set((j + i * 5), mRedrawCards.get(i).get(j));
            }
        }
        sGameViewAdapter.refreshUserHandCards();
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
            List<Card> listSource = adapter.getList();
            listSource.set(cardPos, drawRandomCard());
            adapter.updateList(listSource);
            adapter.notifyDataSetChanged();
            mRedrawCount++;
            mRedrawCountView.setText(mRedrawCount + "/3");
        }
        if (mRedrawCount == 3) {
            String str = getString(R.string.RedrawDropFinished);
            mRedrawDropView.setText(str); //ToDo: use String res
        }
    }

    private Card drawRandomCard() {
        //213Cards, ToDo
        Random rand = new Random();
        int img = rand.nextInt(214);
        int pts = rand.nextInt(7);
        return new Card(pts + 1, img, false, true);
    }

}

