package at.moritzmusel.gwent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import at.moritzmusel.gwent.MainActivity;
import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.network.data.GameState;

public class GameEndScreenActivity extends AppCompatActivity {

    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        Intent intent = getIntent();
        this.gameState = (GameState) intent.getSerializableExtra("gameStateEnd");

        TextView textViewVictoryOrLost = findViewById(R.id.textViewVictoryOrLost);
        if (this.gameState.calculateMyWins(this.gameState.getOpponentRoundCounter()) > 1) {
            textViewVictoryOrLost.setText("Victory");
        } else if(this.gameState.calculateMyWins(this.gameState.getOpponentRoundCounter()) <= 1) {
            textViewVictoryOrLost.setText("Lost");
        } else {
            textViewVictoryOrLost.setText("Draw");
        }

        TextView tvRoundOneUser = findViewById(R.id.tvRoundOneUser);
        tvRoundOneUser.setText(this.gameState.getMyRoundCounter()[0]);

        TextView tvRoundTwoUser = findViewById(R.id.tvRoundTwoUser);
        tvRoundTwoUser.setText(this.gameState.getMyRoundCounter()[1]);

        TextView tvRoundThreeUser = findViewById(R.id.tvRoundThreeUser);
        tvRoundThreeUser.setText(this.gameState.getMyRoundCounter()[2]);

        TextView tvRoundOneOpponent = findViewById(R.id.tvRoundOneOpponent);
        tvRoundOneOpponent.setText(this.gameState.getOpponentRoundCounter()[0]);

        TextView tvRoundTwoOpponent = findViewById(R.id.tvRoundTwoOpponent);
        tvRoundTwoOpponent.setText(this.gameState.getOpponentRoundCounter()[1]);

        TextView tvRoundThreeOpponent = findViewById(R.id.tvRoundThreeOpponent);
        tvRoundThreeOpponent.setText(this.gameState.getOpponentRoundCounter()[2]);
    }
}