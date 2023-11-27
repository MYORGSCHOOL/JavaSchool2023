package karmanchikova.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс сервера
 */
public class Server {
    /**
     * Порт для подключения
     */
    private final static int PORT = 4021;

    /**
     * Метод для запуска сервера
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Ожидание...");
                Socket accept = serverSocket.accept();
                new Thread(new SocketThread(accept)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}