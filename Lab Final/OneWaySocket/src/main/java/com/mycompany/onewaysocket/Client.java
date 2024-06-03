package com.mycompany.onewaysocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ahmud
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 5000);

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        Scanner input = new Scanner(System.in);

        String message;

        while (true) {
            System.out.println("Enter a message: ");
            message = input.nextLine();
            dos.writeUTF(message);

            if (message.equals("close")) {
                dos.close();
                client.close();
                break;
            }
        }

    }

}
