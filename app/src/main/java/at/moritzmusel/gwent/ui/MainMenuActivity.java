package at.moritzmusel.gwent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import at.moritzmusel.gwent.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button playBtn = findViewById(R.id.button_play);
        Button settingsBtn = findViewById(R.id.button_settings);
        Button quitBtn = findViewById(R.id.button_quit);

        playBtn.setOnClickListener(view -> {
            Intent GameViewIntent = new Intent(MainMenuActivity.this, GameViewActivity.class);
            startActivity(GameViewIntent);
        });

        settingsBtn.setOnClickListener(view -> {
            Intent SettingsIntent = new Intent(MainMenuActivity.this, SettingsActivity.class);
            startActivity(SettingsIntent);
        });

        quitBtn.setOnClickListener(view -> {
            this.finishAffinity();
        });
    }
}