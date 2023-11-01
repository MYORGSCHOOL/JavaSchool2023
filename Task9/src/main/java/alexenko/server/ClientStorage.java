package alexenko.server;

import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Thread safe singleton класс, который является БД, хранящей клиентов онлайн.
 */
enum ClientStorage {

    /**
     * Экземпляр БД.
     */
    CLIENTS_ONLINE;

    /**
     * Thread safe HashMap, хранящая имя клиента и его сокет.
     */
    private static final ConcurrentMap<String, Socket> clientsOnline = new ConcurrentHashMap<>();

    /**
     * Добавление клиента в БД.
     *
     * @param clientName имя клиента
     * @param socket     сокет клиента
     */
    public void add(String clientName, Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("Client socket can't be null.");
        }
        clientsOnline.put(clientName, socket);
    }

    /**
     * Получить сокет клиента, по ему имени.
     *
     * @param clientName имя клиента
     * @return сокет клиента
     */
    public Socket getSocket(String clientName) {
        if (!clientsOnline.containsKey(clientName)) {
            throw new IllegalArgumentException("Client with that name: " + clientName + ", does not exist.");
        }
        return clientsOnline.get(clientName);
    }

    /**
     * Получить имена всех пользователей онлайн.
     *
     * @return множество имён пользователей онлайн
     */
    public Set<String> getAllNames() {
        return clientsOnline.keySet();
    }

    /**
     * Удаляет пользователя из БД по его имени.
     *
     * @param clientName имя клиента
     */
    public void remove(String clientName) {
        if (!clientsOnline.containsKey(clientName)) {
            throw new IllegalArgumentException("Client with that name: " + clientName + ", does not exist.");
        }
        clientsOnline.remove(clientName);
    }

    /**
     * Метод проверяет, имеется ли клиент в БД с заявленным именем.
     *
     * @param clientName заявленное имя
     * @return true - имя занято, false - имя свободно
     */
    public boolean clientNameExists(String clientName) {
        return clientsOnline.containsKey(clientName);
    }
}