package pozdnyakova.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс запуска работы клиента
 */
public class Client {
    /**
     * Номер порта для подключения
     */
    private static final int PORT_NUMBER = 1777;
    /**
     * Номер хоста
     */
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT_NUMBER);
            Scanner scanner = new Scanner(System.in);
            new Thread(new InputThread(socket)).start();
            new Thread(new OutputThread(socket, scanner)).start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
