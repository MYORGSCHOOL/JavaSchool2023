package yanko.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * класс клиента
 */
public class Client {
    /**
     * номер порта
     */
    private static final int PORT = 1777;
    /**
     * ip адрес хоста
     */
    private static final String HOST_IP = "127.0.0.1";

    /**
     * метод запуска клиента
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println("client is running");
            Socket socket = new Socket(HOST_IP, PORT);
            Thread inputThread = startInputThread(socket);
            Thread outputThread = startOutputThread(socket);
            inputThread.start();
            outputThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * метод, создающий поток для чтения
     *
     * @param socket сокет клиента
     * @return новый поток
     */
    public static Thread startInputThread(Socket socket) {
        return new Thread(() -> {
            try (InputStream inputStream = socket.getInputStream();
                 InputStreamReader reader = new InputStreamReader(inputStream)) {
                char[] buf = new char[500];
                int size;
                while ((size = reader.read(buf, 0, buf.length)) != -1) {
                    System.out.println(new String(buf, 0, size));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    /**
     * метод, создающий поток для записи
     *
     * @param socket сокет клиента
     * @return новый поток
     */
    public static Thread startOutputThread(Socket socket) {
        return new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in);
                 OutputStream outputStream = socket.getOutputStream();
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                String line;
                do {
                    line = scanner.nextLine();
                    writer.write(line);
                    writer.flush();
                } while (!line.equals("bye"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
