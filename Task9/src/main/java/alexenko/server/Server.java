package alexenko.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс server. Для каждого пользователя, который пытается подключиться, создаётся отдельный поток.
 */
public class Server {

    /**
     * Порт сервера.
     */
    public static final int PORT = 2100;

    /**
     * Создаётся сервер.
     * Ожидает подключение со стороны клиента.
     * Создаёт новый поток, в котором будет осуществлено общение с клиентом.
     * Ждёт нового клиента.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server created");
            while (true) {
                System.out.println("Waiting ... ");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                new ClientSocketThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}