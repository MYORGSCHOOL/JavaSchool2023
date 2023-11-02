package savinskiy.server;

import savinskiy.client.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static savinskiy.common.ChatConstants.CLIENTS_IN_THE_ROOM;
import static savinskiy.common.ChatConstants.PORT;
import static savinskiy.common.ChatConstants.RED_COLOR;
import static savinskiy.common.ChatConstants.RESET_COLOR;
import static savinskiy.common.ChatConstants.YELLOW_COLOR;

public class Server {
    public static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running. Port: " + PORT);

            Thread waitingThread = new Thread(Server::waitingForUsers);
            waitingThread.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void registerClient(String username, ClientHandler client) {
        for (ClientHandler clt : clients) {
            if (clt != client) {
                clt.sendMessage("Username " + RED_COLOR + username + RESET_COLOR + " in the room");
            }
        }
    }

    private static void startChat() {
        System.out.println(YELLOW_COLOR + CLIENTS_IN_THE_ROOM + RESET_COLOR);
    }

    public static synchronized void sendDirectMessage(String from, String to, String message) {
        for (ClientHandler clt : clients) {
            if (clt.getUsername() != null && clt.getUsername().equals(to)) {
                clt.sendMessage(RED_COLOR + from + RESET_COLOR + ": " + message);
                return;
            }
        }
    }

    public static synchronized List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();
        for (ClientHandler clt : clients) {
            if (clt.getUsername() != null) {
                onlineUsers.add(clt.getUsername());
            }
        }
        return onlineUsers;
    }

    private static void waitingForUsers() {
        int dotsCount = 0;
        while (clients.size() < 2) {
            System.out.print("Waiting for users to connect");
            for (int i = 0; i < dotsCount; i++) {
                System.out.print(".");
            }
            dotsCount = (dotsCount + 1) % 6;
            System.out.print("\r");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        startChat();
    }
}
