package malakhova;

import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс, содержащий singleton HashMap для хранения информации о клиентах
 */
public class SynchronizedHashMapWithLock {
    private static SynchronizedHashMapWithLock INSTANCE;
    private static HashMap<Socket, String> person;

    private SynchronizedHashMapWithLock() {
        this.person = new HashMap<>();
    }

    public static SynchronizedHashMapWithLock getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SynchronizedHashMapWithLock();
        }
        return INSTANCE;
    }

    /**
     * Метод для регистрации клиента, сохраняет информацию в мапе
     *
     * @param socketName - сокет клиента
     * @param login      - логин сохраняемого клиента
     * @return - true если клиент зарегистрирован, иначе false
     */
    public static boolean writeToMap(Socket socketName, String login) {
        if (!person.containsKey(socketName)) {
            person.put(socketName, login);
            return true;
        }
        return false;
    }

    /**
     * Метод для получения зарегистрированных пользователей
     *
     * @return - коллекция всех логинов пользователей
     */
    public static Collection readFromMap() {
        Collection<String> persons = person.values();
        return persons;
    }

    /**
     * Метод для получения сокета клиента, которому отправляется сообщение
     *
     * @param login - логин клиента, сокет которого нужно найти
     * @return - сокет клиента
     */
    public static Socket getClient(String login) {
        Set<Map.Entry<Socket, String>> persons = person.entrySet();
        for (Map.Entry<Socket, String> personInMap : persons) {
            if (personInMap.getValue().equals(login)) {
                Socket socket = personInMap.getKey();
                return socket;
            }
        }
        throw new IllegalArgumentException();
    }
}
