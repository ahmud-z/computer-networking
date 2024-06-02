package com.mycompany.socketcommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 6000);
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

        Scanner inputReader = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a message: ");
            String message = inputReader.nextLine();
            dos.writeUTF(message); // sends data to server socket

            String serverMess = dis.readUTF(); // receive data from server
            System.out.println("server said: " + serverMess);

            if (message.equals("close")) {
                dos.close();
                clientSocket.close();
                break;
            }
        }
    }
}
