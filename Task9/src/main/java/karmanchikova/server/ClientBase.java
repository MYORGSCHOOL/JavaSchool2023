package karmanchikova.server;

import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * База пользователей
 */
public class ClientBase {
    /**
     * Экземпляр класса
     */
    private static ClientBase instance;
    /**
     * Список пользователей
     */
    private static ConcurrentHashMap<String, Socket> users;

    private ClientBase() {
    }

    public static ClientBase getInstance() {
        if (instance == null) {
            synchronized (ClientBase.class) {
                users = new ConcurrentHashMap<>();
                instance = new ClientBase();
            }
        }
        return instance;
    }

    /**
     * Добавление нового пользователя
     *
     * @param userName имя пользователя
     * @param socket   сокет
     */
    public void addUser(String userName, Socket socket) {
        users.put(userName, socket);
    }

    /**
     * Поиск пользователя
     *
     * @param userName имя
     * @return true-найден, иначе false
     */
    public boolean findUser(String userName) {
        return users.containsKey(userName);
    }

    /**
     * Получение одного пользователя
     *
     * @param userName имя
     * @return socket пользователя
     */
    public Socket getUser(String userName) {
        return users.get(userName);
    }

    /**
     * Получение всех пользователей в сети
     *
     * @return пользователей в сети
     */
    public Set<String> getAll() {
        return users.keySet();
    }

    /**
     * Удаление пользователя
     *
     * @param name имя
     */
    public void delUser(String name) {
        users.remove(name, getUser(name));
    }
}