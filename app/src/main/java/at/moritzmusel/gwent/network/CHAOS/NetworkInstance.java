package at.moritzmusel.gwent.network.CHAOS;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.android.gms.nearby.connection.ConnectionsClient;

public class NetworkInstance {
    @SuppressLint("StaticFieldLeak")
    private static Network network;

    private NetworkInstance(ConnectionsClient connectionsClient, Context context, TriggerValueChangeListener onConnectionSuccessfulTrigger){
        network = new Network(connectionsClient, context, onConnectionSuccessfulTrigger);
    }

    public static Network getInstance(ConnectionsClient connectionsClient, Context context, TriggerValueChangeListener onConnectionSuccessfulTrigger){
        if(network == null) new NetworkInstance(connectionsClient, context, onConnectionSuccessfulTrigger);
        return network;
    }
}
