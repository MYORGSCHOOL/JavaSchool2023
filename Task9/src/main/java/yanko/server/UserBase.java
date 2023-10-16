package yanko.server;


import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * класс база пользователей, реализующий паттерн синглтон
 */
public class UserBase {
    /**
     * экземпляр класса
     */
    private static UserBase instance;
    /**
     * мапа, хранящая сокеты пользователей с соответствующими им именами-ключами
     */
    private static ConcurrentMap<String, Socket> users;

    /**
     * приватный конструктор, запрещающий создание нового экземпляра класса извне
     */
    private UserBase() {
    }

    /**
     * метод получения экземпляра класса (нового или существующего)
     *
     * @return
     */
    public static UserBase getInstance() {
        if (instance == null) {
            synchronized (UserBase.class) {
                users = new ConcurrentHashMap<>();
                instance = new UserBase();
            }
        }
        return instance;
    }

    /**
     * метод добавления пользователя в базу
     *
     * @param name   имя пользователя
     * @param socket сокет пользователя
     */
    public void registerUser(String name, Socket socket) {
        users.putIfAbsent(name, socket);
    }

    /**
     * метод поиска пользователя в базе по его имени
     *
     * @param name имя пользователя
     * @return значение true или false в зависимости от результата поиска
     */
    public boolean checkUserByName(String name) {
        return users.containsKey(name);
    }

    /**
     * метод, возвращающий сокет пользователя по соответствующему имени
     *
     * @param name имя пользователя
     * @return сокет пользователя
     */
    public Socket getUserSocket(String name) {
        return users.getOrDefault(name, null);
    }

    /**
     * метод, возвращающий список всех пользователей, хранящихся в базе
     *
     * @return список пользователей базы
     */
    public Set<String> getUsers() {
        return users.keySet();
    }

    /**
     * метод, удаляющий пользователя из базы по соответствующему имени
     *
     * @param name имя пользователя
     */
    public void removeUser(String name) {
        users.remove(name, getUserSocket(name));
    }
}
