package karmanchikova.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс Клиента
 */
public class Client {
    /**
     * Порт для подкючения
     */
    private final static int PORT = 4021;
    /**
     * IP-адрес для подкючения
     */
    private final static String IP = "127.0.0.1";

    /**
     * Метод для запуска Клиента
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PORT);
            System.out.println("Клиент подключился");
            Thread input = new Thread(() -> {
                try (InputStream inputStream = socket.getInputStream();
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
                    char[] buffer = new char[1024];
                    int size;
                    while ((size = inputStreamReader.read(buffer, 0, buffer.length)) != -1) {
                        System.out.println(new String(buffer, 0, size));
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            Thread output = new Thread(() -> {
                try (Scanner scanner = new Scanner(System.in);
                     OutputStream outputStream = socket.getOutputStream();
                     OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
                    String message;
                    do {
                        message = scanner.nextLine();
                        outputStreamWriter.write(message);
                        outputStreamWriter.flush();
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