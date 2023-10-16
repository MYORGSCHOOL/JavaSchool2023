package druzhinin.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для реализации потока для работы с пользователем.
 * Для каждого пользователя создаётся свой поток.
 */
public class ClientHandlerThread implements Runnable {
    /**
     * Логер.
     */
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    /**
     * Флаг для определения, авторизован ли пользователь.
     */
    private boolean isAuthorized = false;

    /**
     * Имя пользователя, для которого создан данный поток.
     */
    private String clientName;

    /**
     * Сокет клиентской части пользователя, для которого создан данный поток.
     */
    private final Socket clientSocket;

    public ClientHandlerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Метод для запуска потока работы с пользователем на выполнение.
     * Работа с пользователем завершается после того, как пользователь выбирает в меню пункт
     * завершения работы с программой.
     */
    @Override
    public void run() {
        try (var reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             var writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            writer.println("Добро пожаловать в наше приложение!");
            writer.println("Список доступных функций:");
            do {
                if (!isAuthorized) {
                    writer.println("1. Зарегистрировать имя пользователя");
                    writer.println("2. Получить список всех зарегистрированных пользователей");
                } else {
                    writer.println("1. Изменить имя пользователя");
                    writer.println("2. Получить список всех зарегистрированных пользователей");
                    writer.println("3. Написать сообщение пользователю");
                }
                writer.println("0. Закончить работу с программой");
                writer.println("Выберите нужный вариант (напишите цифру выбранной функции):");
                writer.flush();

            } while (handleUserChoice(writer, reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для обработки ответа пользователя при выборе пункта меню.
     *
     * @param writer Writer для отправки сообщений от сервера пользователю.
     * @param reader Reader для чтения сообщений от пользователя.
     * @return Возвращает true, если пользователь не решил закончить работу с приложением, иначе - false.
     * @throws IOException При неудачной попытке чтения сообщения от пользователя.
     */
    private boolean handleUserChoice(PrintWriter writer, BufferedReader reader) throws IOException {
        String userChoice = reader.readLine();
        if ("1".equals(userChoice) && !isAuthorized) {
            LOGGER.log(Level.INFO, "Client \"" + clientSocket + "\" chose to register new name.");
            registerNewClientName(writer, reader);
        } else if ("1".equals(userChoice)) {
            LOGGER.log(Level.INFO, "Client \"" + clientName + "\" chose to change his name.");
            changeClientName(writer, reader);
        } else if ("2".equals(userChoice)) {
            LOGGER.log(Level.INFO, "Client \"" + ((clientName != null) ? clientName : clientSocket) +
                    "\" chose to print all clients.");
            printAllClients(writer);
        } else if ("3".equals(userChoice) && isAuthorized) {
            LOGGER.log(Level.INFO, "Client \"" +
                    clientName + "\" chose to send message to another client.");
            sendMessageToClient(writer, reader);
        } else if ("0".equals(userChoice)) {
            LOGGER.log(Level.INFO, "Client \"" + ((clientName != null) ? clientName : clientSocket) +
                    "\" chose to disconnect from the server.");
            disconnectClient();
            return false;
        }
        return true;
    }

    /**
     * Метод для регистрации нового пользователя (то есть пользователя, для которого был создан данный поток).
     *
     * @param writer Writer для отправки сообщений от сервера пользователю.
     * @param reader Reader для чтения сообщений от пользователя.
     */
    private synchronized void registerNewClientName(PrintWriter writer, BufferedReader reader) {
        ClientHashMapSingleton instance = ClientHashMapSingleton.getInstance();
        boolean isClientExist;
        String clientName;
        do {
            try {
                writer.println("Введите новое имя пользователя: ");
                writer.flush();

                clientName = reader.readLine();
                isClientExist = instance.isClientExist(clientName);
                if (isClientExist) {
                    writer.println("\nПользователь с таким именем уже существует");
                    writer.flush();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred when writing to or reading from socket: \""
                        + clientSocket + "\".\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
                throw new RuntimeException(e);
            }
        } while (isClientExist);
        this.clientName = clientName;
        instance.registerClient(this.clientName, clientSocket);
        isAuthorized = true;
        writer.println("Имя пользователя успешно зарегистрировано!\n");
        LOGGER.log(Level.INFO, "Client \"" + clientSocket +
                "\" registered new name: \"" + this.clientName + "\".");
    }

    /**
     * Метод для изменения имени пользователя, для которого был создан данный поток.
     *
     * @param writer Writer для отправки сообщений от сервера пользователю.
     * @param reader Reader для чтения сообщений от пользователя.
     */
    private synchronized void changeClientName(PrintWriter writer, BufferedReader reader) {
        ClientHashMapSingleton instance = ClientHashMapSingleton.getInstance();
        boolean isClientExist;
        String newClientName;
        do {
            try {
                writer.println("Текущее имя пользователя: " + this.clientName);
                writer.println("Введите новое имя пользователя: ");
                writer.flush();

                newClientName = reader.readLine();
                isClientExist = instance.isClientExist(newClientName);
                if (isClientExist && !this.clientName.equals(newClientName)) {
                    writer.println("\nПользователь с таким именем уже существует");
                    writer.flush();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred when writing to or reading from socket: "
                        + clientSocket + ".\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
                throw new RuntimeException();
            }
        } while (isClientExist);
        instance.changeClientName(this.clientName, newClientName);
        LOGGER.log(Level.INFO, "Client \"" + this.clientName +
                "\" changed his name to: \"" + newClientName + "\".");
        this.clientName = newClientName;
        writer.println("Имя пользователя успешно изменено!\n");
    }

    /**
     * Метод для отправки пользователю, для которого был создан данный поток,
     * списка всех зарегистрированных и активных пользователей.
     *
     * @param writer Writer для отправки сообщений от сервера пользователю.
     */
    private synchronized void printAllClients(PrintWriter writer) {
        ClientHashMapSingleton instance = ClientHashMapSingleton.getInstance();
        Set<String> clientNames = instance.getAllClientNames();
        if (clientNames.isEmpty()) {
            writer.println("Список зарегистрированных пользователей пуст\n");
            writer.flush();
            return;
        }
        writer.println("Список всех пользователей:");
        writer.flush();
        int i = 1;
        for (String clientName : clientNames) {
            writer.println(i + ". " + clientName);
            i++;
        }
        writer.println();
        writer.flush();
    }

    /**
     * Метод для отправки сообщения от данного пользователя, другому пользователю.
     *
     * @param writer Writer для отправки сообщений от сервера пользователю.
     * @param reader Reader для чтения сообщений от пользователя.
     */
    private synchronized void sendMessageToClient(PrintWriter writer, BufferedReader reader) {
        ClientHashMapSingleton instance = ClientHashMapSingleton.getInstance();
        String destinationName, message;
        boolean isClientExist;
        try {
            writer.println("Выберите пользователя из списка:");
            printAllClients(writer);
            do {
                writer.println("Введите имя пользователя, которому Вы хотите отправить сообщение: ");
                writer.flush();

                destinationName = reader.readLine();
                isClientExist = instance.isClientExist(destinationName);
                if (!isClientExist) {
                    writer.println("\nПользователя с таким именем не существует");
                }
            } while (!isClientExist);

            Socket destinationSocket = instance.getClientSocketByName(destinationName);
            PrintWriter destinationWriter =
                    new PrintWriter(new OutputStreamWriter(destinationSocket.getOutputStream()));
            writer.println("Введите сообщение: ");
            writer.flush();
            message = reader.readLine();

            destinationWriter.println("Сообщение от пользователя \"" +
                    this.clientName + "\": \"" + message + "\".");
            destinationWriter.flush();

            writer.println("Сообщение успешно отправлено!\n");
            LOGGER.log(Level.INFO, "Message from \"" + this.clientName +
                    "\" to \"" + destinationName + "\": \"" + message + "\".");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred when writing to or reading from socket: \""
                    + clientSocket + "\".\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для отключения пользователя, для которого был создан
     * поток от сервера и удаления его из множества активных пользователей.
     */
    private synchronized void disconnectClient() {
        ClientHashMapSingleton instance = ClientHashMapSingleton.getInstance();
        try {
            instance.deleteClient(clientName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred when closing socket: \""
                    + clientSocket + "\".\nStack Trace:\n" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }
}
