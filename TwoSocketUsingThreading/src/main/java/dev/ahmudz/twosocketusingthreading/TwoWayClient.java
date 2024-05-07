package dev.ahmudz.twosocketusingthreading;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TwoWayClient {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 5000);
            Scanner input = new Scanner(System.in);

            int userCount = 0;
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            while (userCount < 5) {
                String inLine = dis.readUTF();
                System.out.println(inLine);
                String outLine = input.nextLine();
                dos.writeUTF(outLine);

                if (outLine.equals("exit")) {
                    System.out.println("Closing the connecting" + clientSocket);
                    clientSocket.close();
                    System.out.println("Connection Closed");
                    break;
                }
                String received = dis.readUTF();
                System.out.println("Result: " + received);
                userCount++;
            }
            dos.close();
            dis.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }
}
