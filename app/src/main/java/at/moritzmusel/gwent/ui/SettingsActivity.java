package at.moritzmusel.gwent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import at.moritzmusel.gwent.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button changeName = findViewById(R.id.button_changeName);
        Button backToMenu = findViewById(R.id.button_backToMenu);

        changeName.setOnClickListener(view -> {
            Intent nameIntent = new Intent(SettingsActivity.this, SelectNameActivity.class);
            nameIntent.putExtra("comingFromWelcome", false);
            startActivity(nameIntent);
        });

        backToMenu.setOnClickListener(view -> {
            Intent menuIntent = new Intent(SettingsActivity.this, MainMenuActivity.class);
            startActivity(menuIntent);
        });
    }
}