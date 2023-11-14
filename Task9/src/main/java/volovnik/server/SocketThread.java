package volovnik.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Класс потока для каждого пользователя
 */
public class SocketThread implements Runnable {

    /**
     * Сокет пользователя
     */
    private final Socket userSocket;

    /**
     * Хранилище пользователей
     */
    private final ClientBase users;

    /**
     * Имя пользователя
     */
    private String username;

    public SocketThread(Socket socket) {
        this.userSocket = socket;
        this.users = ClientBase.getInstance();
    }

    /**
     * Метод запуска обработчика для взаимодействия с клиентом
     */
    @Override
    public void run() {
        try (InputStream inputStream = userSocket.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             OutputStream outputStream = userSocket.getOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            System.out.println("Подключение успешно");
            writer.write("Здравствуйте!\n");
            loginUser(reader, writer);
            writer.write("Выберите желаемое действие:\n" +
                    "1: Просмотр пользователей онлайн\n" +
                    "2: Отправить пользователю сообщение\n" +
                    "Для выход введите: 'exit'");
            writer.flush();
            char[] buffer = new char[1024];
            int size;
            while ((size = reader.read(buffer, 0, buffer.length)) != -1) {
                String message = new String(buffer, 0, size);
                System.out.println("Клиент отправил: " + message);
                if (!message.equals("exit")) {
                    switch (message) {
                        case "1":
                            getOnlineUsers(writer);
                            break;
                        case "2":
                            sendMessageToUser(reader, writer);
                            break;
                    }
                } else {
                    users.deleteUser(username);
                    break;
                }
                writer.write("Выберите желаемое действие:\n" +
                        "1: Просмотр пользователей онлайн\n" +
                        "2: Отправить пользователю сообщение\n" +
                        "Для выход введите: 'exit'");
                writer.flush();
            }
            userSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод авторизации пользователя
     *
     * @param reader Reader для получения данных от клиента
     * @param writer Writer для отправки данных клиенту
     * @throws IOException исключение ввода-вывода
     */
    public void loginUser(InputStreamReader reader, OutputStreamWriter writer) throws IOException {
        writer.write("Введите Ваше имя:");
        writer.flush();
        char[] buffer = new char[1024];
        String name = new String(buffer, 0, reader.read(buffer, 0, buffer.length));
        while (users.findUser(name)) {
            writer.write("Пользователь с именем '" + name + "' уже онлайн!\nВведите другое имя:");
            writer.flush();
            name = new String(buffer, 0, reader.read(buffer, 0, buffer.length));
        }
        users.addLogin(name, userSocket);
        this.username = name;
        writer.write("Пользователь с именем '" + name + "' авторизован\n");
        writer.flush();
    }

    /**
     * Метод для получения всех пользователей онлайн
     *
     * @param writer Writer для отправки данных клиенту
     */
    public void getOnlineUsers(OutputStreamWriter writer) throws IOException {
        writer.write("Пользователи онлайн:\n");
        for (String user : users.getAll()) {
            writer.write(user + "\n");
        }
        writer.flush();
    }

    /**
     * Метод отправки сообщения от одного пользователя другому
     *
     * @param reader Reader для получения данных от клиента
     * @param writer Writer для отправки данных клиенту
     */
    public void sendMessageToUser(InputStreamReader reader, OutputStreamWriter writer) {
        try {
            writer.write("Для отправки сообщения введите: 'имя:сообщение'");
            writer.flush();
            char[] buffer = new char[1024];
            String message = new String(buffer, 0, reader.read(buffer, 0, buffer.length));
            String[] messageDetails = message.split(":");
            if (users.findUser(messageDetails[0])) {
                Socket destUserSocket = users.getUser(messageDetails[0]);
                OutputStreamWriter destUserWriter = new OutputStreamWriter(destUserSocket.getOutputStream());
                destUserWriter.write("Новое сообщение от '" + username + "', текст: " + messageDetails[1] + "\n");
                destUserWriter.flush();
                writer.write("Сообщение отправлено!\n");
            } else {
                writer.write("Пользователь с именем: '" + messageDetails[0] + "' не в сети!");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
