package skrebkov.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для отправки сообщений из терминала на сервер
 */
public class ChatOutput implements Runnable {
    private Socket socket;

    public ChatOutput(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод для отправки сообщений из терминала на сервер
     */
    @Override
    public void run() {
        String userMessage = null;
        try(var scanner = new Scanner(System.in);
            var outputStream = socket.getOutputStream();
            var outputStreamWriter = new OutputStreamWriter(outputStream);
            var bufferedWriter = new BufferedWriter(outputStreamWriter)
        ) {
            do {
                userMessage = scanner.nextLine() + "\n";
                bufferedWriter.write(userMessage);
                bufferedWriter.flush();
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
