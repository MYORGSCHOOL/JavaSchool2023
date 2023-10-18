package pozdnyakova.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс запуска работы сервера
 */
public class Server {
    /**
     * Номер порта для подключения
     */
    private static final int PORT_NUMBER = 1777;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Начало работы сервера");
            while (true) {
                Socket accept = serverSocket.accept();
                new Thread(new SocketThread(accept)).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
