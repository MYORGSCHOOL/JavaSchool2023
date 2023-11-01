package alexenko.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Поток реализующий получение писем от сервера.
 */
class MessageListenerThread extends Thread {

    /**
     * Прощальное сообщение из сервера.
     */
    private static final String FAREWELL_MESSAGE_FROM_SERVER = "Goodbye!";

    /**
     * Сообщение об ошибке чтения входящего сообщения.
     */
    private static final String ERROR_READ_INPUT_MESSAGE = "Ошибка чтения входящего сообщения.";

    /**
     * Lock для реализации блока синхронизации.
     */
    private final Lock lock;

    /**
     * Condition для реализации условия синхронизации с потоком MessageSenderThread.
     */
    private final Condition messageSender;

    /**
     * Reader для чтения писем из входного потока.
     */
    private static BufferedReader reader;

    /**
     * Конструктор, инициализирует начальное состояние потока.
     *
     * @param inputStream поток, которого читать письма
     * @param lock        замок, который реализует блок синхронизации
     * @param condition   условие, по которому будут синхронизированы потоки
     */
    public MessageListenerThread(InputStream inputStream, Lock lock, Condition condition) {
        this.lock = lock;
        this.messageSender = condition;
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Пока клиент активен слушает входной поток, и выводит из него сообщения.
     */
    @Override
    public void run() {
        while (Client.getClientStatus() == Client.Status.ACTIVE) {
            String message = getMessage();
            lock.lock();
            try {
                System.out.print(message);
                Client.setMessageIsGet(true);
                messageSender.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Метод получения того сообщения, которое было отправлено с сервера.
     *
     * @return сообщение
     */
    private static String getMessage() {
        try {
            String message = awaitMessage();
            if (serverSayGoodbye(message)) {
                return message;
            }
            return buildFullMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR_READ_INPUT_MESSAGE;
        }
    }

    /**
     * Метод слушает входной поток, пока не придёт сообщение.
     *
     * @return полученное сообщение
     * @throws IOException исключение чтения из входного потока
     */
    private static String awaitMessage() throws IOException {
        while (!reader.ready()) {
        }
        return reader.readLine();
    }

    /**
     * Метод проверяет, что сервер попрощался с клиентом.
     *
     * @param message прощальное сообщение из сервера
     * @return true - сервер попрощался, false - сервер попрощался
     */
    private static boolean serverSayGoodbye(String message) {
        if (message.equals(FAREWELL_MESSAGE_FROM_SERVER)) {
            Client.setClientStatus(Client.Status.INACTIVE);
            return true;
        }
        return false;
    }

    /**
     * Сборка полного сообщения, которое было отправлено сервером.
     *
     * @param message первая считанная часть сообщения
     * @return полное сообщение
     * @throws IOException исключение чтения из входного потока
     */
    private static String buildFullMessage(String message) throws IOException {
        StringBuilder builder = new StringBuilder().append(message).append("\n");
        while (reader.ready()) {
            message = reader.readLine();
            builder.append(message).append("\n");
        }
        return builder.toString();
    }
}
