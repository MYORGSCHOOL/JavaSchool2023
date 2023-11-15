package volovnik.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Хранилище пользователей
 */
public class ClientBase {

    /**
     * Экземпляр класса
     */
    private static ClientBase instance;

    /**
     * Список всех пользователей
     */
    private static Map<String, Socket> users;

    private ClientBase() {
    }

    public static ClientBase getInstance() {
        if (instance == null) {
            synchronized (ClientBase.class) {
                users = new HashMap<>();
                instance = new ClientBase();
            }
        }
        return instance;
    }

    /**
     * Добавление нового пользователя
     *
     * @param username имя
     * @param socket сокет
     */
    public void addLogin(String username, Socket socket) {
        users.put(username, socket);
    }

    /**
     * Поиск пользователя в словаре
     *
     * @param username имя
     * @return true - пользователь найден, иначе false
     */
    public boolean findUser(String username) {
        return users.containsKey(username);
    }

    /**
     * Получение пользователя
     *
     * @param username имя
     * @return сокет пользователя
     */
    public Socket getUser(String username) {
        return users.get(username);
    }

    /**
     * Получение всех пользователей онлайн
     *
     * @return все пользователи онлайн
     */
    public Set<String> getAll() {
        return users.keySet();
    }

    /**
     * Удаление пользователя
     *
     * @param name имя
     */
    public void deleteUser(String name) {
        users.remove(name, getUser(name));
    }
}
