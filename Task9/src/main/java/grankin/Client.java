package grankin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент
 */
public class Client {

    /**
     * Запуск и работа клиента
     * @param args
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2023);  // Подключаемся по сокету с портом 2023
            System.out.println("Connected to server");

            // Инициализируем потоки ввода/вывода
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());
            Scanner consoleScanner = new Scanner(System.in);

            // Регистрируем клиента
            String serverMessage;
            while (true) {
                serverMessage = in.nextLine();
                if (serverMessage.equals("Please enter your name: ")) {
                    System.out.println(serverMessage);
                    String name = consoleScanner.nextLine().trim();
                    out.println(name);
                }
                else if (serverMessage.startsWith("Hello")) {
                    System.out.println(serverMessage);
                    break;
                }
            }

            // Цикл работы клиента
            Thread inputThread = new Thread(() -> {
                while (true) {
                    String message = consoleScanner.nextLine();
                    if (message.equals("get_online_clients")) {
                        out.println(message);
                    } else if (message.equals("quit")) {
                        out.println(message);
                        break;
                    } else if (message.contains(":")) {
                        out.println(message);
                    } else {
                        System.out.println("Invalid input format. Use 'recipient:message' format.");
                    }
                }
            });
            inputThread.start();

            String receivedMessage;
            while (inputThread.isAlive()) {
                receivedMessage = in.nextLine();
                System.out.println(receivedMessage);
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
