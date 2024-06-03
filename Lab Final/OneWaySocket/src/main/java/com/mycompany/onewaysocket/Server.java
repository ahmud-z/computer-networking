package com.mycompany.onewaysocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ahmud
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Connecting...");

        Socket session = ss.accept();

        System.out.println("Connected");

        DataInputStream dis = new DataInputStream(session.getInputStream());

        while (true) {
            String result = dis.readUTF();
            System.out.println("Client says: " + result);

            if (result.equals("close")) {
                dis.close();
                session.close();
                System.out.println("Connection closed successfully.");
                break;
            }
        }

    }

}
