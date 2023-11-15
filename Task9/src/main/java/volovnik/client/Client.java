package volovnik.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиента
 */
public class Client {

    /**
     * IP для подключения
     */
    private final static String IP = "127.0.0.1";

    /**
     * Порт для подключения
     */
    private final static int PORT = 4021;

    /**
     * Метод запуска клиента
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PORT);
            System.out.println("Клиент стартовал");
            Thread input = new Thread(() -> {
                try (InputStream inputStream = socket.getInputStream();
                     InputStreamReader reader = new InputStreamReader(inputStream)) {
                    char[] buffer = new char[1024];
                    int size;
                    while ((size = reader.read(buffer, 0, buffer.length)) != -1) {
                        System.out.println(new String(buffer, 0, size));
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            Thread output = new Thread(() -> {
                try (Scanner scanner = new Scanner(System.in);
                     OutputStream outputStream = socket.getOutputStream();
                     OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                    String message;
                    do {
                        message = scanner.nextLine();
                        writer.write(message);
                        writer.flush();
                    } while (!message.equals("exit"));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            input.start();
            output.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
