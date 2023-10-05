package grossu.client;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для отправки сообщений от клиента на сервер
 */
public class ThreadOutput implements Runnable {
    /**
     * переменная хранящая сокет
     */
    private final Socket socket;
    /**
     * Переменная для получения сообщений из консоли клиента
     */
    private final Scanner scanner;
    /**
     * Переменная для создания потока на запись
     */
    private final OutputStream outputStream;
    /**
     * Переменная для отправки сообщения серверу
     */
    private final OutputStreamWriter writer;

    public ThreadOutput(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(System.in);
        outputStream = socket.getOutputStream();
        writer = new OutputStreamWriter(outputStream);
    }

    /**
     * Отправка сообщений серверу
     * Взаимодействия происходит до тех пор, пока клиент не отправит сообщение bye
     */
    @Override
    public void run() {
        try {
            String next;
            do {
                next = scanner.nextLine();
                writer.write(next);
                writer.flush();
            } while (!next.equals("bye"));
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
