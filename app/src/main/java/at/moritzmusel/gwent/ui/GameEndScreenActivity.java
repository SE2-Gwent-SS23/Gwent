package at.moritzmusel.gwent.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import at.moritzmusel.gwent.MainActivity;
import at.moritzmusel.gwent.R;

public class GameEndScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
    }

}