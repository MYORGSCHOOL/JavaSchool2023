package abdullaeva.server;

import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Singleton-класс для хранения зарегистрированных онлайн-клиентов сервера
 */
public class OnlineClientBase {

    /**
     * Потокобезопасная версия мапы для хранения информации
     * о зарегистрированных клиентах.
     */
    private static ConcurrentMap<String, Socket> clients;

    /**
     * Одиночный экземпляр класса OnlineClientBase.
     */
    private static OnlineClientBase instance;

    /**
     * Закрытый конструктор класса по-умолчанию.
     */
    private OnlineClientBase() {
    }

    /**
     * Статический создающий метод для получения одиночного экземпляра класса OnlineClientBase().
     * Если объект еще не создан, он создаётся и возвращается этим методом.
     * Если объект существует - метод возвращает уже созданный объект.
     * @return - экземпляр класса OnlineClientBase().
     */
    public static OnlineClientBase getInstance() {
        if (instance == null) {
            synchronized (OnlineClientBase.class) {
                clients = new ConcurrentHashMap<>();
                instance = new OnlineClientBase();
            }
        }
        return instance;
    }

    /**
     * Метод для регистрации клиента в хранилице онлайн-клиентов сервера.
     * Если клиент уже существует, он не будет добавлен в мапу.
     * @param clientName - имя клиента, которого нужно зарегистрировать.
     * @param socket - сокет (точка коммуникации) клиента.
     */
    public void clientRegistration(String clientName, Socket socket) {
        clients.putIfAbsent(clientName, socket);
    }

    /**
     * Метод для получения всех зарегистрированных онлайн-клиентов сервера.
     * @return - список зарегистрированных клиентов.
     */
    public Set<String> getAllOnlineClients() {
        return clients.keySet();
    }

    /**
     * Метод для проверки наличия клиента в списке зарегистрированных онлайн-клиентов.
     * @param clientName
     * @return - false, если клиент не зарегистрирован; true, если клиент зарегистрирован.
     */
    public boolean findIfClientIsOnlineByName(String clientName) {
        return clients.containsKey(clientName);
    }

    /**
     * Метод для получения сокета онлайн-клиента с соответствующим именем.
     * @param clientName - имя онлайн-клиента, чей сокет необходимо получить.
     * @return - сокет онлайн-клиента. Если клиент не будет найден, вернётся дефолтное значение, равное null.
     */
    public Socket getOnlineClientsSocketByName(String clientName) {
        System.out.println("Attention: getOnlineClientsSocketByName will " +
                "return null if a client with the same name is not found");
        return clients.getOrDefault(clientName, null);
    }

    /**
     * Метод для удаления клиента из числа зарегистрированных онлайн-клиентов.
     * @param clientName - имя клиента, которого нужно удалить из числа зарегистрированных.
     */
    public void deleteOnlineClientByName(String clientName) {
        clients.remove(clientName, getOnlineClientsSocketByName(clientName));
    }

}
