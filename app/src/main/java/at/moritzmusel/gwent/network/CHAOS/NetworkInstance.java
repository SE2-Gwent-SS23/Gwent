package at.moritzmusel.gwent.network.CHAOS;

import android.annotation.SuppressLint;

import com.google.android.gms.nearby.connection.ConnectionsClient;

public class NetworkInstance {
    @SuppressLint("StaticFieldLeak")
    private static Network network;

    private NetworkInstance(ConnectionsClient connectionsClient, TriggerValueChangeListener onConnectionSuccessfulTrigger){
        network = new Network(connectionsClient, onConnectionSuccessfulTrigger);
    }

    public static Network getInstance(ConnectionsClient connectionsClient, TriggerValueChangeListener onConnectionSuccessfulTrigger){
        if(network == null) new NetworkInstance(connectionsClient, onConnectionSuccessfulTrigger);
        return network;
    }
}
