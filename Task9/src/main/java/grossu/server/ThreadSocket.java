package grossu.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Класс для работы с потоками клиентов
 */
public class ThreadSocket implements Runnable {
    /**
     * имя потока = имя пользователя
     */
    private String nameThread;
    /**
     * Сокет с которого пришел клиентский запрос
     */
    private final Socket threadSocket;
    /**
     * Список пользователей мессенджера
     */
    private final UserListSingleton usersList;

    /**
     * Удаляет отключившегося пользователя из списка онлайн пользователей
     * Т.е. тех чей сокет был закрыт
     */
    private synchronized void deleteUserFromOnlineMap() {
        usersList.deleteUser(nameThread);
    }

    /**
     * Для отправки пользователя меню с командами для взаимодействия
     *
     * @param writer - для отправки меню пользователю
     * @throws IOException
     */
    private synchronized void printMenu(OutputStreamWriter writer) throws IOException {
        writer.write("enter 1 to get list online users\n");
        writer.write("enter 2 to sent message online user\n");
        writer.write("enter 3 to get menu\n");
        writer.write("enter bye to end work with messenger\n");
        writer.flush();
    }

    /**
     * Метод для авторизации пользователей в мессенджере
     * Пользователю необходимо отправить свое имя для входа
     * Если пользователь с таким именем уже онлайн, то имя попросят ввести повторно
     *
     * @param reader - для получения имени пользователя
     * @param writer - для отправки пользователю сообщение
     * @param socket - для авторизации пользователя в сети
     *               (отправка сообщений от других пользователей будет проводиться по этому адресу)
     */
    private synchronized void loginUser(InputStreamReader reader, OutputStreamWriter writer, Socket socket) {
        try {
            char[] buf = new char[500];
            boolean flagSuccessLogin = false;
            while (!flagSuccessLogin) {
                writer.write("say your login\n");
                writer.flush();
                String name = new String(buf, 0, reader.read(buf, 0, buf.length));
                if (!usersList.checkUser(name) || usersList.checkUser(name) && !usersList.checkOnline(name)) {
                    usersList.login(name, socket);
                    nameThread = name;
                    flagSuccessLogin = true;
                    writer.write("successful login\n");
                } else if (usersList.checkUser(name) && usersList.checkOnline(name)) {
                    writer.write("user with this name already login\n");
                }
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Отправка списка пользователей, которые сейчас онлайн
     *
     * @param writer - для отправки сообщения клиенту
     */
    private synchronized void showOnlineUser(OutputStreamWriter writer) {
        try {
            for (String s : usersList.getOnlineUsers()) {
                writer.write(s + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Поиск пользователя, которому необходимо отправить сообщение
     *
     * @param name - имя пользователя для поиска
     * @return переменная OutputStreamWriter для отправки сообщений
     * @throws IOException
     */
    private synchronized OutputStreamWriter findUser(String name) throws IOException {
        OutputStream out = usersList.getUserSocket(name).getOutputStream();
        return new OutputStreamWriter(out);
    }

    /**
     * Метод для отправки сообщения от одного клиента другому
     * Сообщение может быть доставлено только онлайн пользователю
     * Сообщение для отправки требует определенного формата
     * При несоблюдении этих условий, сообщение от одного клиента другому отправлено не будет
     *
     * @param reader - для чтения сообщения
     * @param writer - для отправки клиенту статуса его сообщения (доставлен/не доставлен)
     */
    private synchronized void sentMessage(InputStreamReader reader, OutputStreamWriter writer) {
        try {
            char[] buf = new char[500];
            writer.write("say user and message to sent with format: username:text message");
            writer.flush();
            int size = reader.read(buf, 0, buf.length);
            String[] arrivedMessage;
            if ((arrivedMessage = new String(buf, 0, size).split(":")).length == 2) {
                String user = arrivedMessage[0];
                String message = arrivedMessage[1];
                if (usersList.checkUser(user)) {
                    if (usersList.checkOnline(user)) {
                            OutputStreamWriter writeMessage = findUser(user);
                            writeMessage.write(nameThread + " sent message: ");
                            writeMessage.write(message + "\n");
                            writeMessage.flush();
                        writer.write("your message delivered\n");
                    } else {
                        writer.write("can't sent message, user is offline\n");
                    }
                } else {
                    writer.write("can't sent message, user not exist\n");
                }
            } else {
                writer.write("can't sent message, incorrect format message\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ThreadSocket(Socket threadSocket) {
        this.threadSocket = threadSocket;
        nameThread = "no login user";
        usersList = UserListSingleton.getInstance();
    }

    /**
     * Реализация взаимодействия с потоком клиента
     * Пока от клиента не поступит сообщение bye.
     * Будут обрабатываться сообщения от клиента
     */
    @Override
    public void run() {
        try (
                InputStream inputStream = threadSocket.getInputStream();
                var reader = new InputStreamReader(inputStream);
                OutputStream outputStream = threadSocket.getOutputStream();
                var writer = new OutputStreamWriter(outputStream)) {
            System.out.println("connection\n");
            writer.write("Glad to see u in our messenger\n");
            writer.flush();
            char[] buf = new char[500];
            int size;
            loginUser(reader, writer, threadSocket);
            printMenu(writer);
            while ((size = reader.read(buf, 0, buf.length)) != -1) {
                String s = new String(buf, 0, size);
                System.out.println("client sent " + s);
                switch (s) {
                    case "1":
                        showOnlineUser(writer);
                        break;
                    case "2":
                        sentMessage(reader, writer);
                        break;
                    case "3":
                        printMenu(writer);
                        break;
                    default:
                        break;
                }
                if (s.equals("bye")) {
                    deleteUserFromOnlineMap();
                    break;
                }
                writer.write("choose next command\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}