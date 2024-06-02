package com.mycompany.twowaysocketprogramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6000); // Bind port

            System.out.println("Listening...");

            while (true) {
                Socket session = serverSocket.accept(); // Waiting
                System.out.println("New client connected: " + session.toString());

                Thread t = new Thread(new ClientHandler(session));
                t.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
