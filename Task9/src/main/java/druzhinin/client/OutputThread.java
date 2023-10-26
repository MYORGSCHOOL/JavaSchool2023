package druzhinin.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для реализации потока отправки сообщений от клиента на сервер.
 */
public class OutputThread implements Runnable {
    /**
     * Логер.
     */
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    /**
     * Серверный сокет.
     */
    private final Socket serverSocket;

    public OutputThread(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Метод для запуска на выполнение потока отправки сообщений от клиента на сервер.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String outputString;
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()))) {
            do {
                outputString = scanner.nextLine();
                writer.println(outputString);
                writer.flush();
            } while (!outputString.equals("0"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred when creating or" +
                    "working with output stream\nStack trace:\n" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }
}
