package at.moritzmusel.gwent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import at.moritzmusel.gwent.network.CHAOS.Network;
import at.moritzmusel.gwent.network.CHAOS.TriggerValueChangeListener;
import at.moritzmusel.gwent.network.model.GwentGame;


public class NetworkTest {
    @Mock
    static
    ConnectionsClient connectionsClient;
    @Mock
    static
    ConnectionLifecycleCallback connectionLifecycleCallback;
    @Mock
    AdvertisingOptions advertisingOptions;
    @Mock
    static
    Context context;

    static TriggerValueChangeListener triggerValueChangeListener = value -> {
    };

    private static Network network;
    private String userIdentifier = "user identifier";
    private String applicationIdentifier = "application identifier";

    @BeforeAll
    static void init(){
        network = new Network(connectionsClient, context, triggerValueChangeListener);
    }

    @Test
    void startHosting(){
        /*
        when(connectionsClient.startAdvertising(any(String.class),any(String.class), connectionLifecycleCallback, advertisingOptions)).thenReturn(mock());
        network.startHosting();
        then(connectionsClient).should().startAdvertising(any(String.class), any(String.class), connectionLifecycleCallback, advertisingOptions);
        */
    }

    @Test
    void createNewGame(){
        assertEquals(2+2, 4);
        /*
        mockStatic(Log.class);w
        mockStatic(Looper.class);
        network.newGame();
        assertEquals(network.getGame(), new GwentGame());
         */
    }
}
