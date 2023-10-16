package druzhinin.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для реализации серверной части приложения.
 */
public class Server {
    /**
     * Порт, на котором развернута серверная часть.
     */
    private static final int SERVER_PORT = 1777;

    /**
     * Логер.
     */
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            LOGGER.log(Level.INFO, "Server is started.");
            while (true) {
                Socket accept = serverSocket.accept();
                LOGGER.log(Level.INFO, "New connection with client: \"" + accept + "\".");
                new Thread(new ClientHandlerThread(accept)).start();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred when creating server socket or " +
                    "working with it.\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }
}
