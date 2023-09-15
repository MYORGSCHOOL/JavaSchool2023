package grossu.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс для реализации работы серверной части мессенджера
 */
public class Server {
    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(1777);
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println(" server wait");
            new Thread(new ThreadSocket(accept)).start();
        }
    }
}
