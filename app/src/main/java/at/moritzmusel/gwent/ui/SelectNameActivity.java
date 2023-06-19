package at.moritzmusel.gwent.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import at.moritzmusel.gwent.R;

public class SelectNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name);

        EditText name = findViewById(R.id.editText_userName);
        TextView error = findViewById(R.id.textView_Error);
        error.setVisibility(View.INVISIBLE);

        Intent loadingIntent = new Intent(SelectNameActivity.this, LoadingActivity.class);
        String storedName = "" + SaveStringToFileClass.getSavedData(getBaseContext(), getString(R.string.playerName_fileName));

        Boolean extra = getIntent().getExtras().getBoolean("comingFromWelcome", true);

        if (storedName.length() > 4 && Boolean.TRUE.equals(extra)) {
            startActivity(loadingIntent);
        }

        findViewById(R.id.button_enterName).setOnClickListener(view -> {
            String userInput = name.getText().toString();

            if (userInput.length() > 4) {
                SaveStringToFileClass.saveData(getBaseContext(), getString(R.string.playerName_fileName), userInput);
                startActivity(loadingIntent);
            } else {
                error.setVisibility(View.VISIBLE);
            }
        });
    }
}
