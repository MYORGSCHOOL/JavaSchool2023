package alexenko.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.*;

/***
 * Класс Client. Подключается к серверу, и запускает потоки для чтения и отправки сообщений на сервер.
 */
public class Client {

    /**
     * Enum класс Status. Состоит из возможных состояний пользователя.
     */
    enum Status {
        /**
         * Состояние ACTIVE - пользователь общается с сервером.
         */
        ACTIVE,
        /**
         * Состояние INACTIVE - пользователь отключен от сервера.
         */
        INACTIVE
    }

    /**
     * Флаг, показывающий получил ли пользователь новое сообщение.
     */
    private static volatile boolean messageIsGet = false;

    /**
     * Lock для реализации блока синхронизации.
     */
    private static final Lock lock = new ReentrantLock();

    /**
     * Condition для реализации условия синхронизации.
     */
    private static final Condition condition = lock.newCondition();

    /**
     * Порт, к которому будет подключаться клиент.
     */
    private static final int PORT = 2100;

    /**
     * Статус активности клиента.
     */
    private static volatile Status clientStatus;

    /**
     * Создаётся подключение к серверу.
     * Создаются потоки для общения с сервером.
     * Пока клиент активен, держим сокет клиента открытым.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", PORT)) {
            clientStatus = Status.ACTIVE;
            new MessageListenerThread(socket.getInputStream(), lock, condition).start();
            new MessageSenderThread(socket.getOutputStream(), lock, condition).start();
            while (clientStatus == Status.ACTIVE) {
            }
        }
    }

    /**
     * Метод возвращает статус активности клиента.
     *
     * @return статус активности клиента
     */
    public static Status getClientStatus() {
        return clientStatus;
    }

    /**
     * Метод меняет статус активности клиента на новый.
     *
     * @param clientStatus новый статус активности клиента
     */
    public static void setClientStatus(Status clientStatus) {
        Client.clientStatus = clientStatus;
    }

    /**
     * Метод возвращает информацию о том, получено ли сообщение.
     *
     * @return true - сообщение получено, false - сообщение не получено
     */
    public static boolean messageIsGet() {
        return messageIsGet;
    }

    /**
     * Меняет статус полученного сообщения, на получен или нет.
     *
     * @param messageIsGet новый статус полученного сообщения
     */
    public static void setMessageIsGet(boolean messageIsGet) {
        Client.messageIsGet = messageIsGet;
    }
}
