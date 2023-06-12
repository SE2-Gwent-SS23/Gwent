package at.moritzmusel.gwent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import at.moritzmusel.gwent.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // below line is used to check if frame layout is empty or not.
        if (findViewById(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // below line is to inflate our fragment.
            getFragmentManager().beginTransaction().add(R.id.idFrameLayout, new SettingsFragment()).commit();
        }
    }
}