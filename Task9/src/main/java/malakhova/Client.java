package malakhova;

import java.io.IOException;
import java.net.Socket;

/**
 * Клиентская часть приложения
 */
public class Client {
    private static final int PORT = 1777;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        new Thread(new ThreadRead(socket)).start();
        new Thread(new ThreadWrite(socket)).start();
    }
}
