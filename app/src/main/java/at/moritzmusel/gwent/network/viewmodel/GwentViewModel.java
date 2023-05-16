package at.moritzmusel.gwent.network.viewmodel;

import static at.moritzmusel.gwent.network.Utils.Logger.d;
import static at.moritzmusel.gwent.network.Utils.Logger.e;
import static at.moritzmusel.gwent.network.Utils.Logger.i;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import at.moritzmusel.gwent.BuildConfig;
import at.moritzmusel.gwent.network.model.GameState;
import at.moritzmusel.gwent.network.model.GwentGame;


public class GwentViewModel extends ViewModel {
    private static String TAG = "GwentViewModel";
    private static Strategy STRATEGY = Strategy.P2P_POINT_TO_POINT;

    private ConnectionsClient connectionsClient;
    private String localUsername = UUID.randomUUID().toString();
    private int localPlayer = 0;
    private int opponentPlayer = 0;
    private String opponentEndpointId = "";
    private GwentGame game;

    private MutableLiveData<GameState> iniState = new MutableLiveData(GameState.UNITIALIZED);
    private LiveData<GameState> state = iniState;

    //Listener for in/out going payloads, eg. send/receive data (gamestate)
    private PayloadCallback payloadCallback = new PayloadCallback() {
        @Override
        public void onPayloadReceived(@NonNull String s, @NonNull Payload payload) {
            //TODO logic for receive
            i(TAG, "onPayloadReceive");
        }

        @Override
        public void onPayloadTransferUpdate(@NonNull String s, @NonNull PayloadTransferUpdate payloadTransferUpdate) {
            //TODO logic for send
            i(TAG, "onPayloadSend");
        }
    };
    //Lifecycle
    private ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() {
        @Override
        public void onConnectionInitiated(@NonNull String s, @NonNull ConnectionInfo connectionInfo) {
            d(TAG, "onConnectionInitiated");
            d(TAG, "Accepting connection...");
            connectionsClient.acceptConnection(s, payloadCallback);
        }

        @Override
        public void onConnectionResult(@NonNull String s, @NonNull ConnectionResolution connectionResolution) {
            d(TAG, "onConnectionResult");
            switch (connectionResolution.getStatus().getStatusCode()) {
                case ConnectionsStatusCodes.STATUS_OK:
                    d(TAG, "ConnectionsStatusCodes.STATUS_OK");
                    connectionsClient.stopAdvertising();
                    connectionsClient.stopDiscovery();
                    opponentEndpointId = s;
                    d(TAG, "opponentEndpointId: " + opponentEndpointId);
                    newGame();
                    //TODO NAVIGATE TO GAME VIEW HERE
                    break;
                case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED:
                    d(TAG, "ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED");
                    break;
                case ConnectionsStatusCodes.STATUS_ERROR:
                    d(TAG, "ConnectionsStatusCodes.STATUS_ERROR");
                    break;
                default:
                    d(TAG, "Unknown status code ${resolution.status.statusCode}");
                    break;
            }
        }

        @Override
        public void onDisconnected(@NonNull String s) {
            d(TAG, "onDisconnected");
            goToHome();
        }
    };
    //
    private EndpointDiscoveryCallback endpointDiscoveryCallback = new EndpointDiscoveryCallback() {
        @Override
        public void onEndpointFound(@NonNull String s, @NonNull DiscoveredEndpointInfo discoveredEndpointInfo) {
            d(TAG, "onEndpointFound");
            d(TAG, "Requesting connection...");
            connectionsClient.requestConnection(
                    localUsername,
                    s,
                    connectionLifecycleCallback
            ).addOnSuccessListener(command -> {
                d(TAG, "Successfully requested a connection");
            }).addOnFailureListener(command -> {
                d(TAG, "Failed to request the connection");
            });
        }

        @Override
        public void onEndpointLost(@NonNull String s) {
            d(TAG, "onEndpointLost");
        }
    };

    public GwentViewModel(ConnectionsClient connectionsClient) {
        this.connectionsClient = connectionsClient;
    }

    public void startHosting() {
        d(TAG, "Start advertising...");
        //TODO HOSTING VIEW HERE
        AdvertisingOptions advertisingOptions = new AdvertisingOptions.Builder().setStrategy(STRATEGY).build();

        connectionsClient.startAdvertising(localUsername, BuildConfig.APPLICATION_ID, connectionLifecycleCallback, advertisingOptions)
                .addOnSuccessListener(command -> {
                    d(TAG, "Advertising...");
                    localPlayer = 1;
                    opponentPlayer = 2;
                })
                .addOnFailureListener(command -> {
                    d(TAG, "Unable to start advertising");
                    //TODO NAVIGATE BACK TO HOMESCREEN
                });
    }

    private void startDiscovering() {
        d(TAG, "Start discovering...");
        //TODO NAVIGATE TO JOIN LOBBY SCREEN HERE
        DiscoveryOptions discoveryOptions = new DiscoveryOptions.Builder().setStrategy(STRATEGY).build();

        connectionsClient.startDiscovery(
                BuildConfig.APPLICATION_ID,
                endpointDiscoveryCallback,
                discoveryOptions
        ).addOnSuccessListener(command -> {
            d(TAG, "Discovering...");
            localPlayer = 2;
            opponentPlayer = 1;
        }).addOnFailureListener(command -> {
            d(TAG, "Unable to start discovering");
            //TODO NAVIGATE BACK TO HOMESCREEN
        });
    }

    public void newGame() {
        d(TAG, "Starting new game");
        game = new GwentGame();
        //TODO CHANGE INIT GAME LOGIC
        iniState.setValue(new GameState(localPlayer, game.playerTurn, game.playerWon, game.isOver, Collections.singletonList(game.board)));
    }

    //TODO pass correct data/gamestate into play function
    public void play(int[] position) {
        if (game.playerTurn != localPlayer) return;
        play(localPlayer, position);
        sendPosition(position);
    }

    //TODO pass correct data/gamestate into play function
    private void play(int player, int[] position) {
        d(TAG, "Player " + player);
        game.play(player, position);
        iniState.setValue(new GameState(localPlayer, game.playerTurn, game.playerWon, game.isOver, Collections.singletonList(game.board)));
    }

    private void sendPosition(int[] position) {
        d(TAG, "Sending [${position.first},${position.second}] to $opponentEndpointId");
        connectionsClient.sendPayload(opponentEndpointId, Objects.requireNonNull(dataToPayload(position)));
    }

    private void stopClient() {
        d(TAG, "Stop advertising, discovering, all endpoints");
        connectionsClient.stopAdvertising();
        connectionsClient.stopDiscovery();
        connectionsClient.stopAllEndpoints();
        localPlayer = 0;
        opponentPlayer = 0;
        opponentEndpointId = "";
    }

    public void goToHome() {
        stopClient();
        //TODO NAVIGATE TO HOME SCREEN
    }

    //converts the data/gamestate into a Payload object
    private Payload dataToPayload(Object o) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(o);
            oos.flush();
            return Payload.fromBytes(bos.toByteArray());
        } catch (IOException e) {
            e(TAG, "Error in dataToPayload method.");
        }
        return Payload.fromBytes(new byte[0]);
    }

    @Override
    protected void onCleared() {
        stopClient();
        super.onCleared();
    }
}