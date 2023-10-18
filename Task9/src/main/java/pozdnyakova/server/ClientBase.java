package pozdnyakova.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для хранения списка зарегистрированных клиентов
 */
public class ClientBase {
    /**
     * Одиночный объект-singleton
     */
    private static volatile ClientBase instance;
    /**
     * Мапа для хранения сокета клиента и его имени
     */
    private static ConcurrentHashMap<Socket, String> socketClient;

    private ClientBase() {
        this.socketClient = new ConcurrentHashMap();
    }

    public ConcurrentHashMap<Socket, String> getHashMap() {
        return socketClient;
    }

    /**
     * Получение сокета клиента по его имени
     *
     * @param name имя клиента
     * @return сокет клиента
     */
    public Socket getSocketClient(String name) {
        Socket socket = null;
        ArrayList<Socket> sockets = new ArrayList<Socket>(socketClient.keySet());
        for (Socket s : sockets) {
            String nameSocket = socketClient.get(s).toString();
            if (nameSocket.equals(name)) {
                socket = s;
            }
        }
        return socket;
    }

    /**
     * Добавление клиента в список зарегистрированных
     *
     * @param socket         сокет регистрируемого клиента
     * @param name           имя регистрируемого клиента
     * @param bufferedWriter буфер для записи в поток
     * @return true - клиент был добавлен в мапу, false - клиент не был добавлен
     * @throws IOException исключение при использовании BufferedWriter
     */
    public synchronized boolean addClient(Socket socket, String name, BufferedWriter bufferedWriter) throws IOException {
        if (!socketClient.containsValue(name)) {
            if (!("".equals(name))) {
                socketClient.put(socket, name);
                return true;
            } else {
                bufferedWriter.write("Введите имя!\n");
                bufferedWriter.flush();
            }
        } else {
            bufferedWriter.write("Такое имя уже используется! Введите другое имя:\n");
            bufferedWriter.flush();
        }
        return false;
    }

    /**
     * Метод для получения объекта-singleton
     *
     * @return объект ClientBase
     */
    public static ClientBase getInstance() {
        if (instance == null) {
            synchronized (ClientBase.class) {
                if (instance == null) {
                    instance = new ClientBase();
                }
            }
        }
        return instance;
    }
}
