package at.moritzmusel.gwent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {

    int progressValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ProgressBar progressBar = findViewById(R.id.progressBar); // initiate the progress bar
        progressBar.setVisibility(View.VISIBLE);
        progressValue = progressBar.getProgress();
        progressBar.setMax(100); // 100 maximum value for the progress value
        progressBar.setProgress(20); // 10 default progress value for the progress bar

        //placeholder
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long l) {
                progressValue += 20;
                progressBar.setProgress(progressValue, true);
            }

            @Override
            public void onFinish() {
                Intent MainMenuIntent = new Intent(LoadingActivity.this, MainMenuActivity.class);
                startActivity(MainMenuIntent);
            }
        }.start();
    }
}