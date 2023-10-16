package druzhinin.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Клиентская часть приложения.
 */
public class Client {
    /**
     * Адрес хоста, на котором развернута серверная часть.
     */
    private static final String HOST_ADDRESS = "127.0.0.1";

    /**
     * Порт, на котором развернута серверная часть.
     */
    private static final int SERVER_PORT = 1777;

    /**
     * Логер.
     */
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST_ADDRESS, SERVER_PORT);
            new Thread(new InputThread(socket)).start();
            new Thread(new OutputThread(socket)).start();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred when connecting to server socket." +
                    "\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }
}
