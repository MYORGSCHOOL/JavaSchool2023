package grossu.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс для чтения сообщений от сервера
 * и вывода теста сообщений на консоль
 */
public class ThreadInput implements Runnable {
    /**
     * Переменная для создания потока на чтение
     */
    private final InputStream inputStream;
    /**
     * Переменная для чтения сообщений пользователя
     */
    private final InputStreamReader reader;

    public ThreadInput(Socket socket) throws IOException {
        inputStream = socket.getInputStream();
        reader = new InputStreamReader(inputStream);
    }

    /**
     * Запуск процесса чтения
     */
    @Override
    public void run() {
        char[] buf = new char[500];
        int size;
        try {
            while ((size = reader.read(buf, 0, buf.length)) != -1) {
                System.out.println("server sent " + new String(buf, 0, size));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}