package at.moritzmusel.gwent.networking.Activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.databinding.ActivityDebugNetworkCreateBinding;
import at.moritzmusel.gwent.networking.Client;
import at.moritzmusel.gwent.networking.Server;

public class debug_activity_network_create extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDebugNetworkCreateBinding binding;
    private TextView debugTextField;
    private TextView ipAndPwField;
    private Server server;
    private String[] ipAndPw = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDebugNetworkCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO NE RICHTIGE SCHEIßIDEE ABER KEIN BOCK GRADE GESAMTEN SERVER UMZUSCHREIBEN DAMIT ER IN EINEM THREAD LÄUFT
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
        // -----------

        init();

        Client client = new Client(this.ipAndPw[0], this.ipAndPw[1], "Player One (Same device as server)");
        //client.initConnection();
    }

    void init(){
        this.debugTextField = findViewById(R.id.debug_txt);
        this.ipAndPwField = findViewById(R.id.ipandpw);
        this.server = new Server();
        this.ipAndPw = server.createLobby();

        // to perform the movement action. Moves the cursor or scrolls to the top or bottom of the document
        this.debugTextField.setMovementMethod(new ScrollingMovementMethod());
        this.ipAndPwField.setText(Html.fromHtml("IP: <b>"+ ipAndPw[0] +"</b><br>PW: <b>"+ ipAndPw[1] +"</b>", Html.FROM_HTML_MODE_COMPACT));
        this.debugTextField.setText(server.getLogs());
    }
}