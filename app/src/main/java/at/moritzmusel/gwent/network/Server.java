package at.moritzmusel.gwent.network;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.Strategy;
import at.moritzmusel.gwent.network.Utils.Utils;

public class Server {
    private Context context;
    private final String SERVICE_ID;
    private int fourDigitPassword;
    private final NearbyConnectionsHelper nearbyConnectionsHelper;
    private final ConnectionsClient connectionsClient;
    private StringBuilder logs;

    public Server(Context context, String SERVICE_ID) {
        this.context = context;
        this.SERVICE_ID = SERVICE_ID;
        this.fourDigitPassword = Utils.generatePassword();
        this.connectionsClient = Nearby.getConnectionsClient(this.context);
        nearbyConnectionsHelper = new NearbyConnectionsHelper(this.context, this.connectionsClient, this.fourDigitPassword) {
            @Override
            protected String getName() {
                return null;
            }

            @Override
            protected String getServiceId() {
                return null;
            }

            @Override
            protected Strategy getStrategy() {
                return null;
            }
        };
        this.logs = new StringBuilder();
    }

    public void createLobby() {
        AdvertisingOptions advertisingOptions =
                new AdvertisingOptions.Builder().setStrategy(Strategy.P2P_STAR).build();
        connectionsClient.startAdvertising(
                        Utils.getDeviceName(), this.SERVICE_ID, nearbyConnectionsHelper.getConnectionLifecycleCallback(), advertisingOptions)
                .addOnSuccessListener(
                        (Void unused) -> {
                            // We're advertising!
                            logs.append("Server started successfull. Waiting for client connections!").append('\n');
                            Log.i("SERVER", "Success");
                        })
                .addOnFailureListener(
                        (Exception e) -> {
                            // We were unable to start advertising.
                            logs.append("Server failed to start. Aborting!").append('\n');
                            Log.e("SERVER", "Failure");
                        });
    }
    public String getLogs(){
        return this.logs.toString();
    }

    public int getPassword(){
        return this.fourDigitPassword;
    }
}
