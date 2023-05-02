package at.moritzmusel.gwent.network.Activities;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.network.Server;
import at.moritzmusel.gwent.network.Utils.Permissions;

public class CreateDebugActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;

    private TextView debugTextField;
    private TextView ipAndPwField;
    private Permissions permissions;
    private Server server;

    @Override
    protected void onStart() {
        super.onStart();
        this.server = new Server(this, getApplicationContext().getPackageName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_debug);

        this.permissions = new Permissions(this, this);
        permissions.checkPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Runnable callback = () -> {
            // Location permission granted, start advertising
            this.debugTextField = findViewById(R.id.debug_txt);
            this.ipAndPwField = findViewById(R.id.ipandpw);
            Server server = new Server(this, getApplicationContext().getPackageName());
            server.createLobby();
            this.debugTextField.setMovementMethod(new ScrollingMovementMethod());
            this.ipAndPwField.setText(Html.fromHtml("Lobby password: " + this.server.getPassword() + "</b>", Html.FROM_HTML_MODE_COMPACT));
            this.debugTextField.setText(server.getLogs());
        };
        this.permissions.handlePermissionsResult(requestCode, permissions, grantResults, callback);
    }
}