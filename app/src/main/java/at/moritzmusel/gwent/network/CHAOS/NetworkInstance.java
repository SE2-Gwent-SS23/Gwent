package at.moritzmusel.gwent.network.CHAOS;

import android.annotation.SuppressLint;

import com.google.android.gms.nearby.connection.ConnectionsClient;

public class NetworkInstance {
    @SuppressLint("StaticFieldLeak")
    private static Network network;
    private NetworkInstance(){}

    public static Network getInstance(ConnectionsClient connectionsClient, TriggerValueChangeListener onConnectionSuccessfulTrigger){
        if (network == null) network = new Network(connectionsClient, onConnectionSuccessfulTrigger);
        return network;
    }
}
