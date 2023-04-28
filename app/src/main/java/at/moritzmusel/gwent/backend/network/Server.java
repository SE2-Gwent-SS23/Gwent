package at.moritzmusel.gwent.backend.network;

import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.backend.data.Utils;

public class Server extends Thread {
    private static final String TAG = "GameServer";
    private static final int PORT = 8000;
    private static final StringBuilder logs = new StringBuilder();

    private static String IP_ADDRESS;
    private static String PASSWORD;
    private static List<PlayerHandler> players;

    public Server() {

    }

    /**
     * @return Returns the ip address of the game server.
     */
    public static String getIpAddress() {
        return IP_ADDRESS;
    }

    /**
     * @return Returns the password for the current game server.
     */
    public static String getPASSWORD() {
        return PASSWORD;
    }

    /**
     * @return Returns the player handlers.
     */
    public static List<PlayerHandler> getPlayers() {
        return players;
    }

    /**
     * Run the game in a new thread.
     * The Thread currently has 3 states: lobby, playing game and finishing.
     */
    @Override
    public void run() {
        createAndWaitLobby();
        startGame();
        endGame();
    }

    /**
     * Creates a lobby using
     * @param textView Die TextView welche die aktuelle server ip addresse und passwort anzeigt. Wird genutzt um im UI Thread das anzuzeigen.
     */
    private void createAndWaitLobby(TextView textView) {
        try {
            // Create server socket on port 8000
            ServerSocket serverSocket = new ServerSocket(PORT);
            IP_ADDRESS = Utils.getIPAddress(true); // IPv4
            PASSWORD = generatePassword(7);
            logs.append(Utils.getIpAddressOfServer()).append('\n');
            logs.append("Started game server. Creating lobby...\n");
            logs.append("IP: ").append(IP_ADDRESS).append('\n').append("PW: ").append(PASSWORD).append('\n');
            logs.append("Waiting for players to connect...\n");

            // Accept two client connections
            at.moritzmusel.gwent.networking.Server.PlayerHandler playerHandlerOne = new at.moritzmusel.gwent.networking.Server.PlayerHandler(serverSocket);
            at.moritzmusel.gwent.networking.Server.PlayerHandler playerHandlerTwo = new at.moritzmusel.gwent.networking.Server.PlayerHandler(serverSocket);

            players = new ArrayList<at.moritzmusel.gwent.networking.Server.PlayerHandler>(2){{add(playerHandlerOne); add(playerHandlerTwo);}};

            executor.submit(playerHandlerOne);
            executor.submit(playerHandlerTwo);
        } catch (IOException exception) {
            Log.e(TAG, exception.getMessage());
        }
        return new String[]{IP_ADDRESS, PASSWORD};
    }

    /**
     *
     */
    private void startGame() {
    }

    /**
     *
     */
    private void endGame() {
    }



}
