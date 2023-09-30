package tsimmer.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Класс запускающий сервер
 */
public class Server {
    /**
     * Порт
     */
    public static final int PORT = 2100;
    /**
     * Список потоков
     */
    public static LinkedList<ServerHandler> serverList = new LinkedList<>();
    /**
     * история переписки
     */
    public static Story story;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            story = new Story();
            System.out.println("Server Started");
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerHandler(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}
