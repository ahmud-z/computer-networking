package com.mycompany.twowaysocketprogramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmud
 */
public class ClientHandler implements Runnable {

    Socket session;

    public ClientHandler(Socket s) {
        session = s;
    }

    @Override
    public void run() {
        DataInputStream reader = null;
        DataOutputStream writer = null;

        try {
            reader = new DataInputStream(session.getInputStream());
            writer = new DataOutputStream(session.getOutputStream());

            while (true) {
                String msg = reader.readUTF();
                System.out.println(msg);

                writer.writeUTF("MSG RCVD: " + msg);

                if (msg.equals("close")) {
                    reader.close();
                    session.close();
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
