package skrebkov.server;

import skrebkov.server.exceptions.ChatIsEmptyException;
import skrebkov.server.exceptions.NameIsTakenException;
import skrebkov.server.exceptions.NoSuchUserException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Класс для хранения информации о пользователях чата
 */
public class Chat {
    /**
     * Поле для хранения экземпляра чата для реализации паттерна singleton
     */
    private static Chat chat;
    /**
     * Справочник хранящий имена пользователей как ключ,
     * а socket как значения для возможности связи с пользователем
     */
    private static final HashMap<String, Socket> users = new HashMap<>();

    /**
     * Приватный конструктор, что бы нельзя было создать экземпляр класс из вне
     */
    private Chat() {

    }

    /**
     * Статический метод для получения единственного экземпляра класса
     * @return чат
     */
    public static synchronized Chat getChat() {
        if (chat == null) {
            chat = new Chat();
        }
        return chat;
    }

    /**
     * Метод для добавления пользователя в справочник
     * @param userName имя пользователя
     * @param socket сокет пользователя
     */
    public synchronized void addUser(String userName, Socket socket) {
        if (users.containsKey(userName)) {
            throw new NameIsTakenException("This name has already been taken");
        }
        users.put(userName, socket);
    }

    /**
     * Получить список всех имён в чате одной строкой
     * @return список имен в чате
     */
    public synchronized String getAllNames() {
        if(users.isEmpty()){
            throw new ChatIsEmptyException("Chat is empty");
        }
        StringBuilder allNames = new StringBuilder();
        for (String userName : users.keySet()) {
            allNames.append(userName).append('\n');
        }
        return allNames.toString();
    }

    /**
     * Получить объект BufferedWriter для отправки сообщения пользователю
     * @param userName имя пользователя
     * @return BufferedWriter - для написания сообщения пользователю
     * @throws IOException
     */
    public synchronized BufferedWriter getUserWriter(String userName) throws IOException {
        Socket userSocket = getUserSocket(userName);
        System.out.println(userSocket);
        return new BufferedWriter(new OutputStreamWriter(userSocket.getOutputStream()));

    }

    /**
     * Метод для получения сокета пользователя по имени пользователя
     * @param userName имя пользователя
     * @return сокет пользователя
     */
    public synchronized Socket getUserSocket(String userName) {
        if (!users.containsKey(userName)) {
            throw new NoSuchUserException("Can't search user in chat");
        }
        return users.get(userName);
    }
}
