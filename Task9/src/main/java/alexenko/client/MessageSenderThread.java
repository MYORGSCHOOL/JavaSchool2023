package alexenko.client;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Поток отправки сообщений на сервер.
 */
class MessageSenderThread extends Thread {

    /**
     * Lock для реализации блока синхронизации.
     */
    private final Lock lock;

    /**
     * Condition для реализации условия синхронизации с потоком MessageListenerThread.
     */
    private final Condition messageListener;

    /**
     * Writer для записи писем в выходной поток.
     */
    private static PrintWriter writer;

    /**
     * Сканер для чтения из консоли.
     */
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор делает начальную инициализацию потока.
     *
     * @param outputStream поток, в которой записывать письма
     * @param lock         замок, который реализует блок синхронизации
     * @param condition    условие, по которому будут синхронизированы потоки
     */
    public MessageSenderThread(OutputStream outputStream, Lock lock, Condition condition) {
        this.lock = lock;
        this.messageListener = condition;
        writer = new PrintWriter(new OutputStreamWriter(outputStream), true);
    }

    /**
     * Пока клиент активен слушает выходной поток, и выводит в него сообщения.
     */
    @Override
    public void run() {
        while (Client.getClientStatus() == Client.Status.ACTIVE) {
            lock.lock();
            try {
                while (!Client.messageIsGet()) {
                    messageListener.await();
                }
                if (Client.getClientStatus() == Client.Status.INACTIVE) {
                    return;
                }
                System.out.print("Ввод: ");
                writer.println(scanner.nextLine());
                Client.setMessageIsGet(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}