package proskurina.server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс сервера мессенджера принимающего подключения клиентов.
 */
public class Server {
    /**
     * Пул для {@link ClientHandler}.
     */
    private final static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    /**
     * Порт серверного сокета.
     */
    private final static int PORT = 28101;
    /**
     * Логер сервера.
     */
    private final static Logger LOGGER = Logger.getLogger(Server.class.getName());
    
    public static void main(String[] args) {
        try (var serverSocket = new ServerSocket(PORT)) {
            LOGGER.log(Level.INFO, "Сервер начал работу");
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.log(Level.INFO, "Подключен клиент " + clientSocket);
                try {
                    EXECUTOR_SERVICE.execute(new ClientHandler(clientSocket, LOGGER));
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Не удалось создать ClientHandler " + clientSocket, e);
                }
            }
        } catch (IOException e) {
            EXECUTOR_SERVICE.shutdownNow();
            LOGGER.log(Level.SEVERE, "Не удалось открыть сокет", e);
        }
    }
}
