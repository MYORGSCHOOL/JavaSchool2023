package druzhinin.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для реализации потока получения клиентом сообщений с сервера.
 */
public class InputThread implements Runnable {
    /**
     * Логер.
     */
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    /**
     * Серверный сокет.
     */
    private final Socket serverSocket;

    public InputThread(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Метод для запуска на выполнение потока получения клиентом сообщений от сервера.
     */
    @Override
    public void run() {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))) {
            String serverMessage;
            System.out.println("Сообщение от сервера:");
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);
            }
        } catch (IOException e) {
            if (e.getMessage().equals("Socket closed")) {
                System.out.println("Сокет закрыт.");
            } else {
                LOGGER.log(Level.SEVERE, "An error occurred when working with reader of server socket "
                        + serverSocket + ".\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
                throw new RuntimeException(e);
            }
        }
    }
}
