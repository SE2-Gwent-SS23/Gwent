package at.moritzmusel.gwent.networking.Activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.databinding.ActivityDebugNetworkJoinBinding;
import at.moritzmusel.gwent.networking.Client;

public class debug_activity_network_join extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDebugNetworkJoinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDebugNetworkJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO NE RICHTIGE SCHEIßIDEE ABER KEIN BOCK GRADE GESAMTEN SERVER UMZUSCHREIBEN DAMIT ER IN EINEM THREAD LÄUFT
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
        // -----------

        TextView textview = findViewById(R.id.debug_txt);
        //TODO: HIER KOMMT JOIN LOGIK HIN UND EIN EVENET LISTENER DER string updated und Textview setzt
        String para = "";

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());

        InputFilter[] filters = new InputFilter[1];
        filters[0] = (source, start, end, dest, dstart, dend) -> {
            if (end > start) {
                String destTxt = dest.toString();
                String resultingTxt = destTxt.substring(0, dstart)
                        + source.subSequence(start, end)
                        + destTxt.substring(dend);
                if (!resultingTxt
                        .matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                    return "";
                } else {
                    String[] splits = resultingTxt.split("\\.");
                    for (int i = 0; i < splits.length; i++) {
                        if (Integer.valueOf(splits[i]) > 255) {
                            return "";
                        }
                    }
                }
            }
            return null;
        };
        ((TextInputEditText)findViewById(R.id.ip_input_text)).setFilters(filters);

        ((AppCompatButton)findViewById(R.id.join_btn)).setOnClickListener(view -> {
            TextInputLayout ipAddress = findViewById(R.id.ip_text);
            TextInputLayout password = findViewById(R.id.pw_text);
            Client client = new Client("10.0.2.15", password.getEditText().getText().toString(), "Player Two");
            client.initConnection();
        });
    }
}