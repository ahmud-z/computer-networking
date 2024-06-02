package com.mycompany.twowaysocketprogramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmud
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6000);

            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            DataInputStream reader = new DataInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Write MSG: ");
                String line = scanner.nextLine();
                writer.writeUTF(line);

                System.out.println(reader.readUTF());

                if (line.equals("close")) {
                    writer.close();
                    socket.close();
                    break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
