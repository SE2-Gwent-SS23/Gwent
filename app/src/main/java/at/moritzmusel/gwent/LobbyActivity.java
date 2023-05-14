package at.moritzmusel.gwent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.nearby.Nearby;

import java.util.Arrays;
import java.util.List;

import at.moritzmusel.gwent.network.viewmodel.GwentViewModel;
import at.moritzmusel.gwent.network.viewmodel.GwentViewModelFactory;

public class LobbyActivity extends AppCompatActivity {
    private static String TAG = "LobbyActivity";
    private boolean lobbyTypeHost;

    private final String[] REQUIRED_PERMISSIONS;
    private static ActivityResultLauncher<String[]> requestMultiplePermissions;
    private GwentViewModel viewModel;

    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            REQUIRED_PERMISSIONS = new String[]{
                    android.Manifest.permission.BLUETOOTH_SCAN,
                    android.Manifest.permission.BLUETOOTH_ADVERTISE,
                    android.Manifest.permission.BLUETOOTH_CONNECT,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.NEARBY_WIFI_DEVICES
            };
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            REQUIRED_PERMISSIONS = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};
        } else {
            REQUIRED_PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
        }

        requestMultiplePermissions = this.registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                permissions -> {
                    if (permissions.entrySet().stream().anyMatch(val -> !val.getValue())) {
                        Log.e(TAG, "Missing permissions");
                        Toast.makeText(this, "Required permissions needed. Go to settings!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else recreate();
                });

        Log.i(TAG, Arrays.toString(REQUIRED_PERMISSIONS));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        viewModel = new GwentViewModel(Nearby.getConnectionsClient(getApplicationContext()));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("lobby_type");
            if(value.equals("join")) {
                lobbyTypeHost = false;
                viewModel.startDiscovering();
            } else {
                lobbyTypeHost = true;
                viewModel.startHosting();
            }
            ((TextView)findViewById(R.id.text_lobby_name)).setText(lobbyTypeHost?"Create Lobby":"Join Lobby");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!hasPermissions(this, REQUIRED_PERMISSIONS)){
            Log.i(TAG, "requestMultiplePermissions called");
            requestMultiplePermissions.launch(REQUIRED_PERMISSIONS);
        }
    }

    private boolean hasPermissions(Activity activity, String[] permissions) {
        List<String> permissionsList = Arrays.asList(permissions);
        return permissionsList.isEmpty() ||
                permissionsList.stream().allMatch(vals -> ContextCompat.checkSelfPermission(activity, vals) == PackageManager.PERMISSION_GRANTED);
    }
}