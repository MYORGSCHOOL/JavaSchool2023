package malakhova;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Серверная часть приложения
 */
public class Server {
    private static final int PORT = 1777;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket fromClientSocket = serverSocket.accept();
                new SocketThread(fromClientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
