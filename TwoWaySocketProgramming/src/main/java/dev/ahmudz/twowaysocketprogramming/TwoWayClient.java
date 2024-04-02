package dev.ahmudz.twowaysocketprogramming;

import java.io.*;
import java.net.Socket;

public class TwoWayClient {

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 5000);
        System.out.println("Connected");

        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader messageReader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while (!str.equals("stop")) {
            System.out.println("client-me: ");
            str = messageReader.readLine();
            outputStream.writeUTF(str);  // Sending message from client

            str = inputStream.readUTF();  // Receive message from server
            System.out.println("server says: " + str);
        }
        inputStream.close();
        outputStream.close();
        messageReader.close();
        clientSocket.close();
    }
}
