package yanko.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * класс сервера
 */
public class Server {
    /**
     * номер порта
     */
    private static final int PORT = 1777;

    /**
     * метод запуска сервера
     *
     * @param args
     * @throws IOException исключение ввода-вывода
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("waiting");
            Socket accept = serverSocket.accept();
            new Thread(new SocketThread(accept)).start();
        }
    }
}
