package druzhinin.server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

/**
 * Класс для реализации Thread Safe Singleton для хранения зарегистрированных пользователей.
 */
public class ClientHashMapSingleton {
    /**
     * Единственный экземпляр класса {@link ClientHashMapSingleton}
     */
    private static ClientHashMapSingleton instance;

    /**
     * Map для хранения имен пользователей и сокетов их клиентских частей.
     */
    private static HashMap<String, Socket> clients;

    /**
     * Объект, по которому проводится синхронизация в методах класса.
     */
    private static final Object mutex = new Object();

    /**
     * Приватный конструктор для запрета создания через него новых экземпляров класса.
     */
    private ClientHashMapSingleton() {

    }

    /**
     * Метод для получения экземпляра данного класса в соответствии с паттерном Singleton.
     *
     * @return Возвращает единственный экземпляр класса {@link ClientHashMapSingleton}
     */
    public static ClientHashMapSingleton getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                instance = new ClientHashMapSingleton();
                clients = new HashMap<>();
            }
        }

        return instance;
    }

    /**
     * Метод для получения имен всех зарегистрированных пользователей.
     *
     * @return Возвращает строковое множество, содержащее имена зарегистрированных пользователей.
     */
    public Set<String> getAllClientNames() {
        return clients.keySet();
    }

    /**
     * Метод для регистрации нового пользователя.
     *
     * @param clientName Имя нового пользователя.
     * @param socket     Сокет клиентской части пользователя.
     */
    public void registerClient(String clientName, Socket socket) {
        clients.put(clientName, socket);
    }

    /**
     * Метод для изменения имени пользователя.
     *
     * @param previousClientName Старое имя пользователя.
     * @param newClientName      Новое имя пользователя.
     */
    public void changeClientName(String previousClientName, String newClientName) {
        clients.put(newClientName, clients.remove(previousClientName));
    }

    /**
     * Метод для проверки существования пользователя по имени.
     *
     * @param clientName Имя, по которому нужно проверить существование пользователя.
     * @return Возвращает true, если пользователь с именем {@code clientName} существует, иначе - false.
     */
    public boolean isClientExist(String clientName) {
        return clients.containsKey(clientName);
    }

    /**
     * Метод для получения сокета клиентской части пользователя по его имени.
     *
     * @param clientName Имя пользователя.
     * @return Возвращает сокет клиентской части пользователя,
     * если пользователь с таким именем существует, иначе - null.
     */
    public Socket getClientSocketByName(String clientName) {
        return clients.get(clientName);
    }

    /**
     * Метод для удаления пользователя из множества зарегистрированных по его имени.
     *
     * @param clientName Имя пользователя, которого нужно удалить.
     */
    public void deleteClient(String clientName) throws IOException {
        clients.remove(clientName).close();
    }
}
