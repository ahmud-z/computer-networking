package com.mycompany.socketcommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(6000);
        System.out.println("Listening.....");
        Socket session = ss.accept();
        System.out.println("Connected.");

        DataInputStream dis = new DataInputStream(session.getInputStream()); // used to receive data
        DataOutputStream dos = new DataOutputStream(session.getOutputStream()); // used to send data

        while (true) {
            String receivedMessage = dis.readUTF(); //received message from client socket
            System.out.println("Client Message: " + receivedMessage);
            dos.writeUTF(receivedMessage + " : received from client"); // sends message to client

            if (receivedMessage.equals("close")) {
                dis.close();
                session.close();
                break;
            }
        }
    }
}
