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

import org.json.JSONException;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.network.data.GameState;

public class RedrawActivity extends AppCompatActivity {
    private static List<Card> sPlayerCards; //prototyp
    private static GameViewActivity sGameViewAdapter;//prototyp
    private List<Card> mPlayerCards;
    private List<List<Card>> mRedrawCards;
    private static GameState gameState;
    private static List<Card> allCardsList;
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
        try {
            GameViewActivity.setCards(findViewById(R.id.redrawUserCards1), true, mRedrawCards.get(0), getApplicationContext(), this, listener, gameState);
            GameViewActivity.setCards(findViewById(R.id.redrawUserCards2), true, mRedrawCards.get(1), getApplicationContext(), this, listener, gameState);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //findViewById(R.id.redrawUserCards1).setOnDragListener(new RedrawDragListener());
        //findViewById(R.id.btnEndRedraw).setOnDragListener(new RedrawDragListener());
    }

    public static void showRedraw(GameViewActivity gameView, List<Card> playerCards, GameState gameState) {
        RedrawActivity.gameState = gameState;
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
        List<Card> firstHalf = new ArrayList<>();
        List<Card> secondHalf = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i < marker) {
                firstHalf.add(list.get(i));
            } else {
                secondHalf.add(list.get(i));
            }
        }
        List<List<Card>> result = new ArrayList<>();
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
        int img = mRandom.nextInt(214);
        int pts = mRandom.nextInt(7);
        /* TODO: get list from gamestate object */
        allCardsList = GameViewActivity.getAllCardsList();
        SecureRandom random = new SecureRandom();
        int zz;

        zz = random.nextInt(allCardsList.size());
        Card card = allCardsList.get(zz);
        while (card.getCount() == 0) {
            zz = random.nextInt(allCardsList.size());
            card = allCardsList.get(zz);
        }

        allCardsList.get(zz).setCount(card.getCount() - 1);
        GameViewActivity.updateAllCardsList(allCardsList);
        return card;
    }

}

