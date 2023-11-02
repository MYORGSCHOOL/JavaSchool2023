package savinskiy.client.handler;

import savinskiy.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static savinskiy.common.ChatConstants.RED_COLOR;
import static savinskiy.common.ChatConstants.RESET_COLOR;
import static savinskiy.common.ChatConstants.WRONG_FORMAT;
import static savinskiy.common.ChatConstants.YELLOW_COLOR;
import static savinskiy.server.Server.clients;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private String username;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            username = in.readLine();
            sendMessage("Welcome, " + RED_COLOR + username + RESET_COLOR);
            Server.registerClient(username, this);

            String input;
            while ((input = in.readLine()) != null) {
                if (input.startsWith("PRINT USERS")) {
                    List<String> onlineUsers = Server.getOnlineUsers();
                    sendMessage("Users online now: " + RED_COLOR + onlineUsers + RESET_COLOR);
                } else if (input.contains(":")) {
                    String[] parts = input.split(":");
                    String to = parts[0];
                    String message = parts[1].trim();
                    Server.sendDirectMessage(username, to, message);
                } else {
                    sendMessage(YELLOW_COLOR + WRONG_FORMAT + RESET_COLOR);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
                clients.remove(this);
                Server.registerClient(username, this);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
