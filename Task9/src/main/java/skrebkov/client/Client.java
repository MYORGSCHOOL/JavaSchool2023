package skrebkov.client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 */
public class Client {
    /**
     * Порт на котором работает сервер
     */
    private final static int PORT = 50144;
    /**
     * Адрес сервера
     */
    private final static String HOST = "localhost";

    /**
     * Метод для запуска сервера
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Start");
        Socket socket = new Socket(HOST, PORT);
        new Thread(new ChatInput(socket)).start();
        new Thread(new ChatOutput(socket)).start();
    }
}
