package at.moritzmusel.gwent.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.model.Card;

public class RedrawActivity extends AppCompatActivity {
    private static List<Card> sPlayerCards;
    private List<Card> mPlayerCards;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_redraw);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPlayerCards = sPlayerCards; //b.getSerializable("cards");

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
                gameView.startActivity(new Intent(gameView, RedrawActivity.class));
            }
        }.start();
    }

    public void onClickCloseRedraw(View view) {
        mPlayerCards.set(0, new Card(6, 10, false, true));
        finish();
    }

}
