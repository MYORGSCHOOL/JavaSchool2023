package pozdnyakova.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для ввода сообщения и отправки серверу
 */
public class OutputThread implements Runnable {
    /**
     * Сокет клиента
     */
    private final Socket socket;
    /**
     * Сканнер для чтения с консоли
     */
    private final Scanner scanner;
    /**
     * Значение введенной строки, после которого прекращается соединение
     */
    private static final String INPUT_TO_EXIT = "4";

    public OutputThread(Socket socket, Scanner scanner) {
        this.socket = socket;
        this.scanner = scanner;
    }

    /**
     * Выполнение потока ввода и отправки сообщения
     */
    @Override
    public void run() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String message = new String();
            while (!INPUT_TO_EXIT.equals(message)) {
                message = scanner.nextLine();
                bw.write(message);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("Соединение прервано");
        }

    }
}
