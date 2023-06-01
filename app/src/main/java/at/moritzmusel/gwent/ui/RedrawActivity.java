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
    private List<Card> mPlayerCards;
    private List<List<Card>> mRedrawCards;
    private static GameState gameState;
    private List<Card> allCardsList;
    TextView mRedrawDropView;
    TextView mRedrawCountView;
    int mRedrawCount = 0;
    SecureRandom mRandom = new SecureRandom();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_redraw);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // mPlayerCards = sPlayerCards; //b.getSerializable("cards");
        this.allCardsList = gameState.getAllCards();

        //init 10 myHandcards
        int zz = 0;
        for (int i = 0; i < 10; i++) {
            zz = mRandom.nextInt(this.allCardsList.size());
            Card card = this.allCardsList.get(zz);
            while (card.getCount() == 0) {
                zz = mRandom.nextInt(this.allCardsList.size());
                card = this.allCardsList.get(zz);
            }

            gameState.addToMyHand(card);
            this.allCardsList.get(i).setCount(card.getCount() - 1);
        }
        mPlayerCards = gameState.getMyHand();

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
    }

    public static void showRedraw(GameViewActivity gameView, List<Card> playerCards, GameState gameState) {
        RedrawActivity.gameState = gameState;
        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
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

        // TODO übergabe an GameState !!!!!!!(check if it is mPlayerCards)!!!!!!!
        gameState.setMyHand(this.mPlayerCards);
        for(Card card: gameState.getMyHand()){
            System.out.println(card.toString());
        }
        System.out.println(gameState.toString());
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
            mRedrawDropView.setText("Bitte auf Fertig tippen");
        }
    }

    private Card drawRandomCard() {
        this.allCardsList = gameState.getAllCards();
        /* TODO: get list from gamestate object */
        SecureRandom random = new SecureRandom();
        int zz = random.nextInt(allCardsList.size());
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

