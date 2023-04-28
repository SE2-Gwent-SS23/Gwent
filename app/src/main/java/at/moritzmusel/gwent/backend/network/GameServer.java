package at.moritzmusel.gwent.backend.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class GameServer  /*extends Service*/ {

    /**
     * Erstelle eine Server Instanz in einem neuem Thread.
     * Grund: Man sollte niemals im UI Thread Network related stuff machen!!!
     */
    public static void startGameServer() {
        Server server = new Server();
        server.start();
    }

    /* TODO Sollen wir das BE in einem Background service machen????
       TODO https://www.toptal.com/android/android-threading-all-you-need-to-know
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }
    */
}
