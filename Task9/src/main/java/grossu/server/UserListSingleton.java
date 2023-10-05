package grossu.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Список пользователей
 */
public class UserListSingleton {
    /**
     * Переменная для реализации паттерна синглетон
     */
    private static UserListSingleton instance;
    /**
     * Список пользователей
     */
    static private Map<String, Socket> usersMap;

    /**
     * запрет на создание экземпляра класса
     */
    private UserListSingleton() {
    }

    public static UserListSingleton getInstance() {
        if (instance == null) {
            synchronized (UserListSingleton.class) {
                usersMap = new HashMap<>();
                instance = new UserListSingleton();
            }
        }
        return instance;
    }

    /**
     * Добавление пользователя в список
     *
     * @param username - имя пользователя
     * @param socket   - сокет, по которому будет происходить взаимодействие с ним
     */
    public void login(String username, Socket socket) {
        usersMap.put(username, socket);
    }

    /**
     * Проверка наличия пользователя в списке
     *
     * @param username - имя пользователя
     * @return true - пользователь есть, false - пользователя нет
     */
    public boolean checkUser(String username) {
        return usersMap.containsKey(username);
    }

    /**
     * Проверка онлайн или офлайн пользователь
     *
     * @param username - имя пользователя
     * @return true - пользователь онлайн, false - в обратном случае
     */
    public boolean checkOnline(String username) {
        return usersMap.containsKey(username);
    }

    /**
     * Получения сокета пользователя
     *
     * @param username - имя искомого пользователя
     * @return сокет
     */
    public Socket getUserSocket(String username) {
        return usersMap.get(username);
    }

    /**
     * Получения списка пользователей, которые онлайн
     *
     * @return список онлайн пользователей
     */
    public Set<String> getOnlineUsers() {
        return usersMap.keySet();
    }

    /**
     * Удаление пользователей, которые отключились
     *
     * @param name - имя пользователя для удаления
     */
    public void deleteUser(String name) {
        usersMap.remove(name);
    }
}
