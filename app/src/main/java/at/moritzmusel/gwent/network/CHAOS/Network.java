package at.moritzmusel.gwent.network.CHAOS;

import static at.moritzmusel.gwent.network.Utils.Logger.d;
import static at.moritzmusel.gwent.network.Utils.Logger.e;
import static at.moritzmusel.gwent.network.Utils.Logger.i;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;

import java.util.ArrayList;
import java.util.UUID;
import at.moritzmusel.gwent.BuildConfig;
import at.moritzmusel.gwent.network.Utils.Utils;
import at.moritzmusel.gwent.network.data.GameState;

public class Network {
    private static String TAG = "Network";
    private static Strategy STRATEGY = Strategy.P2P_POINT_TO_POINT;

    //Current Gamestate
    public MutableLiveData<GameState> currentState = new MutableLiveData<GameState>(GameState.UNITIALIZED);
    public LiveData<GameState> getCurrentState(){
        return currentState;
    }

    //Nearby connections infos
    private ConnectionsClient connectionsClient;
    private String localUsername = UUID.randomUUID().toString();
    private String opponentEndpointId = "";

    //Listener for update on connection state
    private TriggerValueChange onConnectionSuccessfullTrigger = new TriggerValueChange();

    public Network(ConnectionsClient connectionsClient, TriggerValueChangeListener onConnectionSuccessfullTrigger){
        this.connectionsClient = connectionsClient;
        this.onConnectionSuccessfullTrigger.setListener(onConnectionSuccessfullTrigger);
    }

    //Listener for in/out going payloads, eg. send/receive data(gamestate)
    private final PayloadCallback payloadCallback = new PayloadCallback() {
        @Override
        public void onPayloadReceived(@NonNull String s, @NonNull Payload
                payload) {
            GameState opponentGS =
                    (GameState)Utils.byteArrayToObject(payload.asBytes());
            GameState currGamestate = getCurrentState().getValue();
            i(TAG, opponentGS.toString());

            if(opponentGS.isRedrawPhase()){
                currGamestate.setRedrawPhase(false);
                currGamestate.setOpponentHand(opponentGS.getMyHand());
            }

            if(currGamestate.getRoundTracker() <
                    opponentGS.getRoundTracker()) {
                currGamestate.incrementRoundTracker();
                currGamestate.sendToMyGrave();
                currGamestate.setMyPassed(false);
            }

            currGamestate.setOpponentHand(opponentGS.getMyHand());
            currGamestate.setOpponentClose(opponentGS.getMyClose());
            currGamestate.setOpponentRanged(opponentGS.getMyRanged());
            currGamestate.setOpponentDeck(opponentGS.getMyDeck());
            currGamestate.setOpponentGrave(opponentGS.getMyGrave());
            currGamestate.setOpponentLeader(opponentGS.getMyLeader());
            currGamestate.setUsedOpponentLeader(opponentGS.getUsedMyLeader());
            currGamestate.setOpponentRoundCounter(opponentGS.getMyRoundCounter());
            currGamestate.setOpponentPassed(opponentGS.isMyPassed());

            currentState.postValue(currGamestate);
        }

        @Override
        public void onPayloadTransferUpdate(@NonNull String s, @NonNull PayloadTransferUpdate payloadTransferUpdate) {
        }
    };

    //Lifecycle
    private final ConnectionLifecycleCallback connectionLifecycleCallback
            = new ConnectionLifecycleCallback() {
        @Override
        public void onConnectionInitiated(@NonNull String s, @NonNull
                ConnectionInfo connectionInfo) {
            connectionsClient.acceptConnection(s, payloadCallback);
        }

        @Override
        public void onConnectionResult(@NonNull String s, @NonNull
                ConnectionResolution connectionResolution) {
            // d(TAG, "onConnectionResult");
            switch (connectionResolution.getStatus().getStatusCode()) {
                case ConnectionsStatusCodes.STATUS_OK:
                    connectionsClient.stopAdvertising();
                    connectionsClient.stopDiscovery();
                    opponentEndpointId = s;
                    onConnectionSuccessfullTrigger.setValue(true);
                    break;
                case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED:
                    onConnectionSuccessfullTrigger.setValue(false);
                    break;
                case ConnectionsStatusCodes.STATUS_ERROR:
                    onConnectionSuccessfullTrigger.setValue(false);
                    break;
                default:
                onConnectionSuccessfullTrigger.setValue(false);
                break;
            }
        }

        @Override
        public void onDisconnected(@NonNull String s) {
            stopClient();
        }
    };
    //
    private final EndpointDiscoveryCallback endpointDiscoveryCallback =
            new EndpointDiscoveryCallback() {
                @Override
                public void onEndpointFound(@NonNull String s, @NonNull
                        DiscoveredEndpointInfo discoveredEndpointInfo) {
                    connectionsClient.requestConnection(
                            localUsername,
                            s,
                            connectionLifecycleCallback
                    ).addOnSuccessListener(command -> {
                    }).addOnFailureListener(command -> {
                    });
                }

                @Override
                public void onEndpointLost(@NonNull String s) {
                }
            };

    public void startHosting() {
        AdvertisingOptions advertisingOptions = new AdvertisingOptions.Builder().setStrategy(STRATEGY).build();

        connectionsClient.startAdvertising(localUsername,
                        BuildConfig.APPLICATION_ID, connectionLifecycleCallback,
                        advertisingOptions)
                .addOnSuccessListener(command -> {
                })
                .addOnFailureListener(command -> {
                });
    }

    public void startDiscovering() {
        DiscoveryOptions discoveryOptions = new
                DiscoveryOptions.Builder().setStrategy(STRATEGY).build();
        connectionsClient.startDiscovery(
                BuildConfig.APPLICATION_ID,
                endpointDiscoveryCallback,
                discoveryOptions
        ).addOnSuccessListener(command -> {
        }).addOnFailureListener(command -> {
        });
    }

    public void sendGameState(GameState gameState) {
        connectionsClient.sendPayload(opponentEndpointId,
                dataToPayload(gameState));
    }

    private void stopClient() {
        connectionsClient.stopAdvertising();
        connectionsClient.stopDiscovery();
        connectionsClient.stopAllEndpoints();
        opponentEndpointId = "";
    }

    //converts the data/gamestate into a Payload object
    private Payload dataToPayload(GameState gameState) {
        return Payload.fromBytes(Utils.objectToByteArray(gameState));
    }
}