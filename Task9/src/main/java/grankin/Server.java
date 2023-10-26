package grankin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс сервер
 * Обрабатывает клиентов
 */
public class Server {

    /**
     * Список всех текущих клиентов
     */
    private static List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    /**
     * Запуск сервера
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2023); // Создаем серверный сокет с портом 2023
            System.out.println("Server started and listening on port 2023");

            while (true) {
                Socket clientSocket = serverSocket.accept();    // Принимаем подключение клиентов
                System.out.println("Client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);  // Создаем класс для общения с клиентом
                clients.add(clientHandler);     // Добавляем клиента в список текущих клиентов
                new Thread(clientHandler).start();  // Запускаем обработку клиента в новом потоке
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправить сообщение клиенту
     * @param nameClientGetter    - имя клиента получаемого сообщения
     * @param message             - сообщение
     */
    public static void sendMessageToClient(String nameClientGetter, String message) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(nameClientGetter)) {
                client.sendMessage(message);
                break;
            }
        }
    }

    /**
     * Получить клиентов в сети
     * @return  - строка с клиентами
     */
    public static String getOnlineClients() {
        StringBuilder onlineClients = new StringBuilder();
        for (ClientHandler client : clients) {
            onlineClients.append(client.getClientName()).append("\n");
        }
        return onlineClients.toString();
    }

    /**
     * Внутренний класс для обработки клиентов
     */
    static class ClientHandler implements Runnable {

        /**
         * Сокет, выделяемый для клиента
         */
        private Socket clientSocket;

        /**
         * Поток вывода
         */
        private PrintWriter out;

        /**
         * Поток ввода
         */
        private Scanner in;

        /**
         * Имя клиента
         */
        private String clientName;

        /**
         * Конструктор с параметром
         * @param socket    - сокет
         */
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        /**
         * Вернуть имя клиента
         * @return  - имя
         */
        public String getClientName() {
            return clientName;
        }

        /**
         * Отправить сообщение клиенту
         * @param message   - сообщение
         */
        public void sendMessage(String message) {
            out.println(message);
        }

        /**
         * Переопределение метода run для работы нового потока
         * (Вся работа с клиентом)
         */
        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new Scanner(clientSocket.getInputStream());

                out.println("Please enter your name: ");
                clientName = in.nextLine();
                out.println("Hello, " + clientName + "! You are now registered.");

                String clientMessage;
                do {
                    clientMessage = in.nextLine();

                    if (clientMessage.equals("get_online_clients")) {
                        out.println("Online clients:\n" + getOnlineClients());
                    } else if (clientMessage.contains(":")) {
                        String[] parts = clientMessage.split(":", 2);
                        String client = parts[0];
                        String message = parts[1];
                        Server.sendMessageToClient(client, clientName + ": " + message);
                    }
                } while (!clientMessage.equalsIgnoreCase("quit"));

                clients.remove(this);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
