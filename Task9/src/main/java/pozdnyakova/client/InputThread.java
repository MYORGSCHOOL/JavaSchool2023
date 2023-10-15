package pozdnyakova.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс для чтения сообщений от сервера
 */
public class InputThread implements Runnable {
    /**
     * Сокет клиента
     */
    private final Socket socket;
    /**
     * Строка, получаемая от сервера, о завершении соединения
     */
    private static final String EXIT_MESSAGE = "exit";

    public InputThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Выполнение потока чтения
     */
    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String text;
            while (!EXIT_MESSAGE.equals(text = br.readLine())) {
                System.out.println(text);
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("Соединение прервано");
        }

    }
}
