package at.moritzmusel.gwent.networking;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static String IP_ADDRESS;
    private static String PASSWORD;
    private static List<PlayerHandler> players;

    private static final String TAG = "GameServer";
    private static final int PORT = 8000;
    private static final StringBuilder logs = new StringBuilder();
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public String[] createLobby() {
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
            PlayerHandler playerHandlerOne = new PlayerHandler(serverSocket);
            PlayerHandler playerHandlerTwo = new PlayerHandler(serverSocket);

            players = new ArrayList<PlayerHandler>(2){{add(playerHandlerOne); add(playerHandlerTwo);}};

            executor.submit(playerHandlerOne);
            executor.submit(playerHandlerTwo);
        } catch (IOException exception) {
            Log.e(TAG, exception.getMessage());
        }
        return new String[]{IP_ADDRESS, PASSWORD};
    }

    private static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    public String getLogs(){
        return logs.toString();
    }

    private static class PlayerHandler extends Thread {
        private final ServerSocket serverSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private String name;
        private ObjectMessage message;

        public PlayerHandler(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            try {
                Log.i(TAG, "Waiting for player");
                Socket player = this.serverSocket.accept();

                ois = new ObjectInputStream(player.getInputStream());
                oos = new ObjectOutputStream(player.getOutputStream());

                // Prompt the player for a password
                message = (ObjectMessage) ois.readObject();
                if (message.getMessage().equals(Game_Commands.GET_PASSWORD) && message.getMessage() instanceof String) {
                    if(((String)message.getMessage()).equals(PASSWORD)){
                        logs.append("Player ").append(message.getPlayerInfo()).append("joined\n");
                    }
                }
            } catch (IOException ex) {
                Log.e(TAG, "Error handling player: " + ex.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e(TAG, e.getMessage());
            } finally {
                try {
                    this.serverSocket.close();
                } catch (IOException ex) {
                    Log.e(TAG, "Error closing player socket: " + ex.getMessage());
                }
                Log.d(TAG, name + " disconnected.");
                players.remove(this);
            }
        }
    }
}
