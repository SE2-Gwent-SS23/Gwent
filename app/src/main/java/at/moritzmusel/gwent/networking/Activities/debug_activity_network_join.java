package at.moritzmusel.gwent.networking.Activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.databinding.ActivityDebugNetworkJoinBinding;

public class debug_activity_network_join extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDebugNetworkJoinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDebugNetworkJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textview = findViewById(R.id.debug_txt);
        //TODO: HIER KOMMT JOIN LOGIK HIN UND EIN EVENET LISTENER DER string updated und Textview setzt
        String para = "";

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());
    }
}