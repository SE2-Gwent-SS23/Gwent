package at.moritzmusel.gwent.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static String PASSWORD;

    public static void createLobby(String password, String ip, int port){
        Thread t = new Thread(new GameInstance(password, ip, port));
        t.start();
    }
        public static void main(String[] args) {
        try {
            // Create server socket on port 8000
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Waiting for connections...");

            // Accept two client connections
            Socket playerOne = serverSocket.accept();
            System.out.println("Player 1 connected.");
            Socket playerTwo = serverSocket.accept();
            System.out.println("Player 2 connected.");

            // Create input/output streams for both clients
            DataInputStream in1 = new DataInputStream(playerOne.getInputStream());
            DataOutputStream out1 = new DataOutputStream(playerOne.getOutputStream());
            DataInputStream in2 = new DataInputStream(playerTwo.getInputStream());
            DataOutputStream out2 = new DataOutputStream(playerTwo.getOutputStream());

            // Start game loop
            while (true) {
                // Player 1 sends data to player 2
                String message1 = in1.readUTF();
                System.out.println("Player 1: " + message1);
                out2.writeUTF(message1);

                // Player 2 sends data to player 1
                String message2 = in2.readUTF();
                System.out.println("Player 2: " + message2);
                out1.writeUTF(message2);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
class GameInstance implements Runnable{
    private String PASSWORD;
    private String IP;
    private int PORT;
    private final int MAX_CLIENTS = 2; // maximum number of clients to accept

    GameInstance(String password, String ip, int port){
        PASSWORD = password;
        IP = ip;
        PORT = port;
    }

    @Override
    public void run() {
        int clientCount = 0; // count of connected clients

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (clientCount < MAX_CLIENTS) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(new DataInputStream(socket.getInputStream()));
                DataOutputStream dos = new DataOutputStream(new DataOutputStream(socket.getOutputStream()));
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                // Read the password from the client
                String password = dis.readUTF();

                // Check if the password is correct
                if (password.equals(PASSWORD)) {
                    // Send the OK message to the client
                    dos.writeUTF("OK");
                    // Increment the client count
                    clientCount++;
                    // Do something with the client connection...
                } else {
                    // Send the error message to the client
                    dos.writeUTF("ERROR");
                    // Close the client connection
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // If we get here, we've accepted the maximum number of clients
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

