package proskurina.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, который обрабатывает сообщения подключенного клиента.
 * Может зарегистрировать его в списке пользователей,
 * по запросу одного клиента отправить сообщение другому клиенту,
 * показать клиенту список пользователей мессенджера.
 */
public class ClientHandler implements Runnable {
    /**
     * Сокет клиента
     */
    private final Socket clientSocket;
    /**
     * Имя пользователя, которое будет ассоциироваться с клиентом.
     */
    private String clientName;
    /**
     * Поток для отправки сообщений.
     */
    private final PrintWriter clientWriter;
    /**
     * Поток для получения сообщений.
     */
    private final BufferedReader clientReader;
    /**
     * Карта со всеми пользователями и их потоками для отправки сообщений.
     */
    private final ConcurrentMap<String, PrintWriter> usernameToPrintWriters;
    /**
     * Логер обработчиков клиентов.
     */
    private final Logger logger;
    /**
     * Команда для отправки списка всех пользователей.
     */
    private static final String SEND_USERS_LIST = "/users";
    /**
     * Разделитель для сообщений
     * "Имя пользователя MESSAGE_SPLITERATOR сообщение"
     */
    private static final String MESSAGE_SPLITERATOR = ":";
    
    public ClientHandler(Socket clientSocket, Logger logger) throws IOException {
        this.logger = Objects.requireNonNull(logger);
        this.clientSocket = Objects.requireNonNull(clientSocket);
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.usernameToPrintWriters = UsernameToPrintWriter.getInstance().getConcurrentMap();
    }
    
    @Override
    public void run() {
        try (clientSocket; clientReader; clientWriter) {
            registerUser();
            sendInfoMessage();
            
            String message;
            while ((message = clientReader.readLine()) != null) {
                if (message.equals(SEND_USERS_LIST)) {
                    sendUsersList();
                } else {
                    String[] args = message.split(MESSAGE_SPLITERATOR, 2);
                    sendPrivateMessageTo(args[0], args[1]);
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Соединение потеряно " + clientSocket);
        } finally {
            if (clientName != null) {
                usernameToPrintWriters.remove(clientName);
            }
            logger.log(Level.INFO, "Клиент отключен " + clientSocket);
        }
    }
    
    /**
     * Отправляет правила использования мессенджера клиенту.
     */
    private void sendInfoMessage() {
        clientWriter.println("Отправить личное сообщение: <Имя пользователя>" + MESSAGE_SPLITERATOR + " <сообщение>\n"
                             + "Получить список пользователей: " + SEND_USERS_LIST);
    }
    
    
    /**
     * Регистрация клиента в списке пользователей.
     *
     * @throws IOException если {@link #clientReader} закрыт
     */
    private void registerUser() throws IOException {
        String newClientName;
        clientWriter.println("Чтобы присоединиться введите имя.");
        while ((newClientName = clientReader.readLine()) != null) {
            if (newClientName.contains(MESSAGE_SPLITERATOR)) {
                clientWriter.println("Имя не может содержать " + MESSAGE_SPLITERATOR);
            } else if (putUserIfNotExist(newClientName)) {
                this.clientName = newClientName;
                clientWriter.println("Вы присоединились под именем " + newClientName);
                break;
            } else {
                clientWriter.println("Пользователь с таким именем уже существует.");
            }
        }
    }
    
    /**
     * Отправляет {@code message} пользователю с именем {@code receiverName}.
     *
     * @param receiverName имя получателя
     * @param message      сообщение
     */
    private void sendPrivateMessageTo(String receiverName, String message) {
        PrintWriter receiverWriter = usernameToPrintWriters.get(receiverName);
        if (receiverWriter != null) {
            receiverWriter.println("От " + clientName + ": " + message);
        } else {
            clientWriter.println("Пользователя с именем " + receiverName + " не существует.");
        }
    }
    
    /**
     * Возвращает {@code true}, если удалось добавить клиента в карту пользователей с переданным {@code username},
     * возвращает {@code false}, если такой {@code username} уже существует.
     *
     * @param username имя пользователя
     * @return {@code true} если удалось добавить клиента с таким {@code username}
     */
    private boolean putUserIfNotExist(String username) {
        return usernameToPrintWriters.putIfAbsent(username, clientWriter) == null;
    }
    
    /**
     * Отправляет список всех пользователей клиенту.
     */
    private void sendUsersList() {
        String listOfUsers = "Пользователи: " + String.join(", ", usernameToPrintWriters.keySet());
        clientWriter.println(listOfUsers);
    }
}
