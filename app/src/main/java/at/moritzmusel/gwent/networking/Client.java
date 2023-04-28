package at.moritzmusel.gwent.networking;

import  android.os.StrictMode;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8000;
    private static String IP_ADDRESS;
    private static String PASSWORD;
    private static String userInfo;

    public Client(String IP_ADDRESS, String PASSWORD, String userInfo){
        Client.IP_ADDRESS = IP_ADDRESS;
        Client.PASSWORD = PASSWORD;
        Client.userInfo = userInfo;
    }
    public void initConnection(){
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(IP_ADDRESS, PORT);
            socket.connect(socketAddress, 2000);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Prompt the user for the password
            oos.writeObject(new ObjectMessage(Game_Commands.GET_PASSWORD, Client.PASSWORD, userInfo));
            oos.writeObject(new ObjectMessage(Game_Commands.GET_PASSWORD, Client.PASSWORD, userInfo));

            // Clean up
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error connecting to server: " + ex.getMessage());
        }
    }
}
