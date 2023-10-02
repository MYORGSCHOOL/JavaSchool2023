package grossu.client;


import java.io.IOException;
import java.net.Socket;

/**
 * Класс для реализации клиентской части мессенджера
 */
public class Client {
    public static void main(String[] args) throws IOException {
        int port = 1777;
        System.out.println("Client start");
        Socket socket = new Socket("127.0.0.1", port);
        new Thread(new ThreadInput(socket)).start();
        new Thread(new ThreadOutput(socket)).start();
    }
}
