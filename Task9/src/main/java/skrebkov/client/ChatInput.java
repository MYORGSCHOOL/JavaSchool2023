package skrebkov.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс для работы с входящим потоком с сервера чата
 */
public class ChatInput implements Runnable {
    private Socket socket;

    public ChatInput(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод для работы с входящим потоком с сервера чата
     */
    @Override
    public void run() {
        try (var inputStream = socket.getInputStream();
             var streamReader = new InputStreamReader(inputStream);
             var bufferedReader = new BufferedReader(streamReader)
        ) {

            String messageFromServer;
            do {
                messageFromServer = bufferedReader.readLine();
                if (messageFromServer != null) {
                    System.out.println(messageFromServer);
                }
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
