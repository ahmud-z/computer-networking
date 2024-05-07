package dev.ahmudz.twosocketusingthreading;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TwoWayServer {

    public static void main(String[] args) {
        try {
            ServerSocket handshake = new ServerSocket(5000);
            System.out.println("Server connected at: " + handshake.getLocalPort());
            System.out.println("Waiting for client...");
            while (true) {
                Socket com_socket = handshake.accept();
                System.out.println("A new client is connected " + com_socket);

                DataInputStream dis = new DataInputStream(com_socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(com_socket.getOutputStream());

                System.out.println("A new thread is assigning");
                Thread new_tunnel = new ClientHandler(com_socket, dis, dos);
                new_tunnel.start();
            }

        } catch (Exception e) {
        }
    }
}
