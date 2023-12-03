package abdullaeva.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Класс для запуска клиент-приложения
 */
public class ClientApp {

    /**
     * IP-адреса хоста клиента.
     */
    private static final String HOST = "127.0.0.1";

    /**
     * Порт клиента.
     */
    private static final int PORT = 8888;

    /**
     * Метод для запуска приложения-клиента.
     */
    public static void main(String[] args) throws RuntimeException {
        try {
            System.out.println("ClientApp is running, get starting to use ...");
            Socket socket = new Socket(HOST,PORT);
            Thread inputThread = inputThreadStarter(socket);
            Thread outputThread = outputThreadStarter(socket);
            inputThread.start();
            outputThread.start();
        } catch (IOException e) {
            throw new RuntimeException("ClientApp run: Error input/output threads", e);
        }

    }

    /**
     * Метод для создания клиенту потока для чтения.
     *
     * @param socket - сокет (точка коммуникации) клиента.
     * @return - новый поток для чтения.
     */
    public static Thread inputThreadStarter(Socket socket) {
        return new Thread(() -> {
            try (InputStream inputStream = socket.getInputStream();
                 var inputStreamReader = new InputStreamReader(inputStream)) {
                char[] batch = new char[512];
                int size;
                while ((size = inputStreamReader.read(batch, 0, batch.length)) != -1) {
                    System.out.println(new String(batch, 0, size));
                }
            } catch (IOException e) {
                throw new RuntimeException("inputThreadStarter: Error input/output threads", e);
            }
        });
    }

    /**
     * Метод для создания клиенту потока для записи.
     * @param socket - сокет (точка коммуникации) клиента.
     * @return - новый поток для записи.
     */
    public static Thread outputThreadStarter(Socket socket) {
        return new Thread(() -> {
            try (OutputStream outputStream = socket.getOutputStream();
                 var outputStreamWriter = new OutputStreamWriter(outputStream);
                 Scanner scanner = new Scanner(System.in)) {
                String line;
                do {
                    line = scanner.nextLine();
                    outputStreamWriter.write(line);
                    outputStreamWriter.flush();
                } while (!line.equals("exit"));
            } catch (IOException e) {
                throw new RuntimeException("outputThreadStarter: Error input/output threads", e);
            }
        });
    }
}
