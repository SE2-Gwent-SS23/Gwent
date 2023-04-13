package at.moritzmusel.gwent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

        //Intent gameViewIntent = new Intent(MainActivity.this, GameViewActivity.class);
        //startActivity(gameViewIntent);
    }
}