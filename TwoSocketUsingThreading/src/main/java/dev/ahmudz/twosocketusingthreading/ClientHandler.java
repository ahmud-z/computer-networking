package dev.ahmudz.twosocketusingthreading;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    final Socket com_tunnel;
    final DataInputStream dis_tunnel;
    final DataOutputStream dos_tunnel;
    String received = "";
    String toreturn = "";

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.com_tunnel = s;
        this.dis_tunnel = dis;
        this.dos_tunnel = dos;
    }

    @Override
    public void run() {
        while (true) {

            try {
                dos_tunnel.writeUTF("Enter your input string: ");
                received = dis_tunnel.readUTF();

                if (received.equals("exit")) {
                    System.out.println("Client " + this.com_tunnel + " sends exits");
                    System.out.println("Closing the connection");
                    this.com_tunnel.close();
                    break;
                }

                toreturn = received.toUpperCase();
                dos_tunnel.writeUTF(toreturn);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        try {
            this.dos_tunnel.close();
            this.dis_tunnel.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
