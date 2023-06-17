package at.moritzmusel.gwent.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.model.CardGenerator;
import at.moritzmusel.gwent.model.RedrawObjectGenerator;
import at.moritzmusel.gwent.model.ReturnCard;
import at.moritzmusel.gwent.network.data.GameState;

public class RedrawActivity extends AppCompatActivity {
    private List<Card> mPlayerCards;
    private List<List<Card>> mRedrawCards;
    private GameState gameState;
    private TextView mRedrawDropView;
    private TextView mRedrawCountView;
    private int mRedrawCount = 0;
    private CardGenerator cardGenerator;
    private RedrawObjectGenerator redrawObjectGenerator;
    private static final String TAG = "GameViewActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_redraw);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // receiving the gameState from GameViewActivity
        Intent intent = getIntent();
        this.gameState = (GameState) intent.getSerializableExtra("gameState");
        int deviceHeight = intent.getIntExtra("deviceHeight", 0);

        this.redrawObjectGenerator = RedrawObjectGenerator.getInstance();
        this.cardGenerator = new CardGenerator(this.getApplicationContext(), deviceHeight);
        this.gameState = cardGenerator.initMyHandCards(gameState);

        mPlayerCards = gameState.getMyHand();

        mRedrawCountView = findViewById(R.id.txtRedrawCount);
        mRedrawDropView = findViewById(R.id.txtRedrawDrop);
        RedrawDragListener listener = new RedrawDragListener(this, mRedrawDropView);
        mRedrawDropView.setOnDragListener(listener);

        mRedrawCards = redrawObjectGenerator.halveList(mPlayerCards);
        try {
            cardGenerator.setCards(findViewById(R.id.redrawUserCards1), true, mRedrawCards.get(0), getApplicationContext(), this, listener, gameState);
            cardGenerator.setCards(findViewById(R.id.redrawUserCards2), true, mRedrawCards.get(1), getApplicationContext(), this, listener, gameState);
        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        //add event listener to button
        Button redrawButton = (Button) findViewById(R.id.btnEndRedraw);
        redrawButton.setOnClickListener(x -> onClickCloseRedraw());
    }

    public void onClickCloseRedraw() {
        for (int i = 0; i < mRedrawCards.size(); i++) {
            for (int j = 0; j < mRedrawCards.get(i).size(); j++) {
                mPlayerCards.set((j + i * 5), mRedrawCards.get(i).get(j));
            }
        }

        gameState.setMyHand(this.mPlayerCards);

        // Sending the modified object back to the GameViewActivity
        Intent intent = new Intent();
        intent.putExtra("gameState", gameState);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void replaceCard(View cardView) {
        if (mRedrawCount <= 2) {
            UserCardAdapter adapter = (UserCardAdapter) ((RecyclerView) cardView.getParent()).getAdapter();
            int cardPos = (int) cardView.getTag();
            List<Card> listSource = adapter.getList();
            ReturnCard returnCard = redrawObjectGenerator.drawRandomCard(gameState);
            listSource.set(cardPos, returnCard.getCard());
            this.gameState = returnCard.getGameState();
            adapter.updateList(listSource);
            adapter.notifyDataSetChanged();
            mRedrawCount++;
            mRedrawCountView.setText(mRedrawCount + "/3");
        }
        if (mRedrawCount == 3) {
            mRedrawDropView.setText("Bitte auf Fertig tippen");
        }
    }
}