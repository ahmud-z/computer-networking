package dev.ahmudz.twowaysocketprogramming;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TwoWayServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("server connected with: " + serverSocket.getLocalPort() + " port");
        Socket s = serverSocket.accept();

        DataInputStream inputStream = new DataInputStream(s.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
        BufferedReader messageReader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while (!str.equals("stop")) {
            str = inputStream.readUTF(); // Receiving client message
            System.out.println("client says: " + str);

            System.out.println("server-you: ");
            str = messageReader.readLine();
            outputStream.writeUTF(str); // Sending message from server
        }
        inputStream.close();
        outputStream.close();
        messageReader.close();
        serverSocket.close();
    }
}
