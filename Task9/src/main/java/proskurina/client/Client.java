package proskurina.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиента подключающегося к серверу мессенджера.
 * Может писать сообщения в консоль и отправлять их,
 * полученные сообщения выводит в консоль.
 */
public class Client {
    /**
     * Порт хоста.
     */
    private static final int PORT = 28101;
    /**
     * Адрес хоста.
     */
    private static final String HOST = "127.0.0.1";
    
    public static void main(String[] args) {
        try {
            var socket = new Socket(HOST, PORT);
            Thread reiceiverThread = createMessageReceiverThread(socket);
            Thread messageSenderThread = createMessageSenderThread(socket);
            
            messageSenderThread.start();
            reiceiverThread.start();
            
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу." + e.getMessage());
        }
    }
    
    /**
     * Создает и возвращает класс потока, который может читать строки из {@code InputStream}
     * переданного сокета и выводить их в консоль.
     *
     * @param source сокет, из {@code InputStream} которого будут читаться строки
     * @return созданный поток
     */
    private static Thread createMessageReceiverThread(Socket source) {
        return new Thread(() -> {
            try (var reader = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                disconnectMessage(e.getMessage());
            }
        });
    }
    
    /**
     * Создает и возвращает класс потока, который может считывать строки из консоли
     * и сразу отправлять в {@code OutputStream} переданного сокета.
     *
     * @param source сокет, в {@code OutputStream} которого будут переданы строки из консоли
     * @return созданный поток
     */
    private static Thread createMessageSenderThread(Socket source) {
        return new Thread(() -> {
            try (var consoleScanner = new Scanner(System.in); var writer = new PrintWriter(source.getOutputStream(), true)) {
                
                while (!source.isClosed()) {
                    String message = consoleScanner.nextLine().trim();
                    if (!message.isEmpty()) {
                        writer.println(message);
                    }
                }
            } catch (IOException e) {
                disconnectMessage(e.getMessage());
            }
        });
    }
    
    /**
     * Выводит сообщение об ошибке в консоль.
     *
     * @param cause причина ошибки
     */
    private static void disconnectMessage(String cause) {
        System.out.println("Соединение потеряно: " + cause);
    }
    
}
