package at.moritzmusel.gwent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Setting background color for Welcome Screen
        ImageView backgroundImg = (ImageView) findViewById(R.id.imageViewWelcomeScreen);
        backgroundImg.setBackgroundColor(Color.rgb(255, 255, 255));
        backgroundImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starting Load Screen when Welcome Screen is clicked
                Intent welcomeToLoadingIntent = new Intent(WelcomeActivity.this, LoadingActivity.class);
                startActivity(welcomeToLoadingIntent);
            }
        });

    }
}
