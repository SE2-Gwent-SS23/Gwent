package at.moritzmusel.gwent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import at.moritzmusel.gwent.network.viewmodel.GwentViewModel;


public class GwentViewModelTest {
    @Mock
    static
    ConnectionsClient connectionsClient;
    @Mock
    static
    ConnectionLifecycleCallback connectionLifecycleCallback;
    @Mock
    AdvertisingOptions advertisingOptions;

    private static GwentViewModel gwentViewModel;
    private String userIdentifier = "user identifier";
    private String applicationIdentifier = "application identifier";

    @BeforeAll
    static void init(){
        gwentViewModel = new GwentViewModel(connectionsClient);
    }
    @Test
    void startHosting(){
        when(connectionsClient.startAdvertising(any(String.class),any(String.class), connectionLifecycleCallback, advertisingOptions)).thenReturn(mock());
        gwentViewModel.startHosting();
        then(connectionsClient).should().startAdvertising(any(String.class), any(String.class), connectionLifecycleCallback, advertisingOptions);
    }
}
