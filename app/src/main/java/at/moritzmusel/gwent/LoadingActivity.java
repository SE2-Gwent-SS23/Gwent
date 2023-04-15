package at.moritzmusel.gwent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        /*Intent MenuIntent = new Intent(LoadingActivity.this, MenuActivity.class);
        startActivity(MenuIntent);*/

        ProgressBar progressBar = findViewById(R.id.progressBar); // initiate the progress bar
        progressBar.setVisibility(View.VISIBLE);
    }
}