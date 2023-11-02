package savinskiy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static savinskiy.common.ChatConstants.CONNECTED;
import static savinskiy.common.ChatConstants.ENTER_USERNAME;
import static savinskiy.common.ChatConstants.HOST;
import static savinskiy.common.ChatConstants.INFO_MESSAGE;
import static savinskiy.common.ChatConstants.PORT;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println(CONNECTED);
            System.out.println(INFO_MESSAGE);
            System.out.println(ENTER_USERNAME);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader terminalInput = new BufferedReader(new InputStreamReader(System.in));

            String username = terminalInput.readLine();
            out.println(username);

            Thread serverListener = new Thread(() -> {
                String serverRs;
                try {
                    while ((serverRs = in.readLine()) != null) {
                        System.out.println(serverRs);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            serverListener.start();

            String input;
            while (true) {
                input = terminalInput.readLine();
                if (input != null) {
                    out.println(input);
                }
            }
        } catch (IOException e) {
            System.err.println("Error connecting to " + HOST + ":" + PORT);
            throw new RuntimeException(e.getMessage());
        }
    }
}