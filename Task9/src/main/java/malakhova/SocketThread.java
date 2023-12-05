package malakhova;

import java.io.*;
import java.net.Socket;
import java.util.Collection;

/**
 * Поток для обработки запроса клиента.
 * В зависимости от выбранного клиентом действия вызываются методы для регистрации пользователя, получения списка пользователей и отправки сообщения
 */
public class SocketThread extends Thread {
    private final Socket fromClientSocket;
    private static final int EXIT = 4;
    private static final SynchronizedHashMapWithLock mapWithLock = SynchronizedHashMapWithLock.getInstance();

    public SocketThread(Socket fromClientSocket) {
        this.fromClientSocket = fromClientSocket;
    }

    @Override
    public void run() {
        try (Socket localSocket = fromClientSocket;
             PrintWriter pw = new PrintWriter(localSocket.getOutputStream(), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()))) {
            int number = 0;
            while (number != EXIT) {
                pw.println("Выберите действие:");
                pw.println("1-зарегистрироваться");
                pw.println("2-получить список пользователей");
                pw.println("3-отправить сообщение");
                pw.println("4-выход");
                String n = br.readLine();
                number = Integer.parseInt(n);
                if (number == 1) {
                    pw.println("Введите логин: ");
                    String login = br.readLine();
                    if (mapWithLock.writeToMap(fromClientSocket, login)) {
                        pw.println("Вы успешно зарегестрированы");
                    } else {
                        pw.println("Вы уже были зарагестрированы");
                    }
                } else if (number == 2) {
                    pw.println("Список пользователей: ");
                    Collection person = mapWithLock.readFromMap();
                    Object[] personArray = person.toArray();
                    for (int i = 0; i < person.size(); i++) {
                        pw.println(personArray[i]);
                    }
                } else if (number == 3) {
                    pw.println("Напишите логин пользователя, которому хотите отправить сообщение: ");
                    String login = br.readLine();
                    pw.println("Напишите сообщение: ");
                    String message = br.readLine();
                    message = login + ":" + message;
                    Socket socketClient = mapWithLock.getClient(login);
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream()), true);
                    printWriter.println(message);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
