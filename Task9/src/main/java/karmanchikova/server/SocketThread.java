package karmanchikova.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketThread implements Runnable {

    /**
     * Сокет пользователя
     */
    private final Socket socket;
    /**
     * База пользователей
     */
    private final ClientBase users;
    /**
     * Имя пользователя
     */
    private String userName;

    public SocketThread(Socket socket) {
        this.socket = socket;
        this.users = ClientBase.getInstance();
    }

    /**
     * Метод для взаимодейсвтия с клиентом
     */
    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             OutputStream outputStream = socket.getOutputStream();
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
            System.out.println("Подключение успешно");
            outputStreamWriter.write("Здравствуйте!\n");
            loginUser(inputStreamReader, outputStreamWriter);
            outputStreamWriter.write("Выберите действие:\n" +
                    "1: Просмотр пользователей\n" +
                    "2: Отправить сообщение\n" +
                    "Для выход введите: 'exit'");
            outputStreamWriter.flush();
            char[] buffer = new char[1024];
            int size;
            while ((size = inputStreamReader.read(buffer, 0, buffer.length)) != -1) {
                String message = new String(buffer, 0, size);
                System.out.println("Клиент отправил: " + message);
                if (!message.equals("exit")) {
                    switch (message) {
                        case "1":
                            getUsersOnline(outputStreamWriter);
                            break;
                        case "2":
                            messageToUser(inputStreamReader, outputStreamWriter);
                            break;
                    }
                } else {
                    users.delUser(userName);
                    break;
                }
                outputStreamWriter.write("Выберите действие:\n" +
                        "1: Просмотр пользователей\n" +
                        "2: Отправить сообщение\n" +
                        "Для выход введите: 'exit'");
                outputStreamWriter.flush();
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Авторизация пользователя
     *
     * @param inputStreamReader  получениеданных от пользователя
     * @param outputStreamWriter отправка данных пользователю
     * @throws IOException исключение ввода-вывода
     */
    public void loginUser(InputStreamReader inputStreamReader, OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.write("Введите имя:");
        outputStreamWriter.flush();
        char[] buffer = new char[1024];
        String name = new String(buffer, 0, inputStreamReader.read(buffer, 0, buffer.length));
        while (users.findUser(name)) {
            outputStreamWriter.write("Пользователь '" + name + "' уже онлайн!\nВведите другое имя:");
            outputStreamWriter.flush();
            name = new String(buffer, 0, inputStreamReader.read(buffer, 0, buffer.length));
        }
        users.addUser(name, socket);
        this.userName = name;
        outputStreamWriter.write("Пользователь '" + name + "' авторизован\n");
        outputStreamWriter.flush();
    }

    /**
     * Получение данных о пользователях онлайн
     *
     * @param outputStreamWriter отправка данных пользователю
     * @throws IOException исключение ввода-вывода
     */
    public void getUsersOnline(OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.write("Пользователи онлайн:\n");
        for (String user : users.getAll()) {
            outputStreamWriter.write(user + "\n");
        }
        outputStreamWriter.flush();
    }

    /**
     * Отправка сообщения о одного пользователю другому
     *
     * @param inputStreamReader  получение данных от пользователя
     * @param outputStreamWriter отправка данных пользователю
     */
    public void messageToUser(InputStreamReader inputStreamReader, OutputStreamWriter outputStreamWriter) {
        try {
            outputStreamWriter.write("Для отправки сообщения введите: 'имя:сообщение'");
            outputStreamWriter.flush();
            char[] buffer = new char[1024];
            String message = new String(buffer, 0, inputStreamReader.read(buffer, 0, buffer.length));
            String[] messageDetails = message.split(":");
            if (users.findUser(messageDetails[0])) {
                Socket destUserSocket = users.getUser(messageDetails[0]);
                OutputStreamWriter destUserWriter = new OutputStreamWriter(destUserSocket.getOutputStream());
                destUserWriter.write("Новое сообщение от '" + userName + "', текст: " + messageDetails[1] + "\n");
                destUserWriter.flush();
                outputStreamWriter.write("Сообщение отправлено!\n");
            } else {
                outputStreamWriter.write("Пользователь с именем: '" + messageDetails[0] + "' не в сети!");
            }
            outputStreamWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}