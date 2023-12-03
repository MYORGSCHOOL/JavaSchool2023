package abdullaeva.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс для запуска сервера
 */
public class ServerApp {
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("Waiting for connect...");
            Socket acceptSocket = serverSocket.accept();
            new Thread(new InteractionThreads(acceptSocket)).start();
        }

    }
}
