package at.moritzmusel.gwent.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import at.moritzmusel.gwent.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ImageView startImg = findViewById(R.id.imageView_play);
        ImageView settingsImg = findViewById(R.id.imageView_settings);
        ImageView quitImg = findViewById(R.id.imageView_quit);
        TextView playerName = findViewById(R.id.textView_username);

        playerName.setText(SaveStringToFileClass.getSavedData(getBaseContext(), getString(R.string.playerName_fileName)));

        startImg.setOnClickListener(view -> {
            // Initializing the popup menu and giving the reference as current context
            PopupMenu popupMenu = new PopupMenu(MainMenuActivity.this, startImg);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                // Toast message on menu item clicked
                Toast.makeText(MainMenuActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();

                if (menuItem.getTitle().equals("Host")) {
                    Intent i = new Intent(MainMenuActivity.this, GameViewActivity.class);
                    i.putExtra("lobby_type", "create");
                    startActivity(i);
                } else if (menuItem.getTitle().equals("Join")) {
                    Intent i = new Intent(MainMenuActivity.this, GameViewActivity.class);
                    i.putExtra("lobby_type", "join");
                    startActivity(i);
                } else {
                    Toast.makeText(MainMenuActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            });
            popupMenu.show();
        });

        settingsImg.setOnClickListener(view -> {
            Intent SettingsIntent = new Intent(MainMenuActivity.this, SettingsActivity.class);
            startActivity(SettingsIntent);
        });

        quitImg.setOnClickListener(view -> {
            this.finishAffinity();
        });
    }
}