package at.moritzmusel.gwent.network.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.nearby.connection.ConnectionsClient;

public class GwentViewModelFactory implements ViewModelProvider.Factory {
    private ConnectionsClient connectionsClient;

    public GwentViewModelFactory(ConnectionsClient connectionsClient){
        this.connectionsClient = connectionsClient;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GwentViewModel.class)) {
            return (T) new GwentViewModel(this.connectionsClient);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}