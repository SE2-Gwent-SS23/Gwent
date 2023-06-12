package at.moritzmusel.gwent.network.Utils;

import static androidx.core.app.ActivityCompat.requestPermissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import at.moritzmusel.gwent.R;

public class Permissions {
    private static final String TAG = "Permissions";
    /**
     * These permissions are required before connecting to Nearby Connections.
     */
    public static final String[] REQUIRED_PERMISSIONS;
    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;
    private final Activity activity;
    private final Context context;

    public Permissions(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            REQUIRED_PERMISSIONS =
                    new String[]{
                            Manifest.permission.BLUETOOTH_SCAN,
                            Manifest.permission.BLUETOOTH_ADVERTISE,
                            Manifest.permission.BLUETOOTH_CONNECT,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                    };
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            REQUIRED_PERMISSIONS =
                    new String[]{
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                    };
        } else {
            REQUIRED_PERMISSIONS =
                    new String[]{
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                    };
        }
    }

    /**
     * Returns {@code true} if the app was granted all the permissions. Otherwise, returns {@code
     * false}.
     */
    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void checkPermissions() {
        if (!hasPermissions(this.context, getRequiredPermissions())) {
            requestPermissions(this.activity, getRequiredPermissions(), REQUEST_CODE_REQUIRED_PERMISSIONS);
        }
    }

    /**
     * An optional hook to pool any permissions the app needs with the permissions ConnectionsActivity
     * will request.
     *
     * @return All permissions required for the app to properly function.
     */
    private String[] getRequiredPermissions() {
        return REQUIRED_PERMISSIONS;
    }

    public void handlePermissionsResult(int requestCode, String[] permissions, int[] grantResults, Runnable callbackFunction){
        if (requestCode == REQUEST_CODE_REQUIRED_PERMISSIONS) {
            int i = 0;
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    Log.w(TAG, "Failed to request the permission " + permissions[i]);
                    Toast.makeText(activity, R.string.error_missing_permissions, Toast.LENGTH_LONG).show();
                    activity.finish();
                    return;
                }
                i++;
            }
            activity.recreate();
            callbackFunction.run();
        }
        activity.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
