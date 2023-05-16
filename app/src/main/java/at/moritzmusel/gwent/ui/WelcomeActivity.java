package at.moritzmusel.gwent.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import at.moritzmusel.gwent.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Setting background color for Welcome Screen
        ImageView backgroundImg = (ImageView) findViewById(R.id.imageViewWelcomeScreen);
        backgroundImg.setBackgroundColor(Color.rgb(255, 255, 255));
        backgroundImg.setOnClickListener(view -> {
            // Starting Load Screen when Welcome Screen is clicked
            Intent welcomeToLoadingIntent = new Intent(WelcomeActivity.this, LoadingActivity.class);
            startActivity(welcomeToLoadingIntent);
        });
    }
}
