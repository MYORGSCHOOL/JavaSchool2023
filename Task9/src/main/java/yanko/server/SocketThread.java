package yanko.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * класс, реализующий взаимодействие пользователя с сервером
 */
public class SocketThread implements Runnable {
    /**
     * сокет пользователя
     */
    private final Socket socket;
    /**
     * база пользователей
     */
    private final UserBase users;
    /**
     * имя текущего пользователя
     */
    private String nameUser;

    public SocketThread(Socket socket) {
        this.socket = socket;
        this.users = UserBase.getInstance();
    }

    /**
     * метод запуска обработчика команд пользователя-клиента
     * при подключении пользователю предлагается авторизоваться в системе
     * после авторизации пользователь может посмотреть список пользователей онлайн
     * или написать сообщение другому пользователю
     * для выхода из системы пользователю нужно ввести bye
     */
    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             OutputStream outputStream = socket.getOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            System.out.println("connected");
            writer.write("hello\n");
            authorizeUser(reader, writer);
            writer.write("\navailable options:\n" +
                    "1 get all users\n" +
                    "2 send message\n" +
                    "type 'bye' to exit\n");
            writer.flush();
            char[] buf = new char[500];
            int size;
            while ((size = reader.read(buf, 0, buf.length)) != -1) {
                String s = new String(buf, 0, size);
                System.out.println("client send " + s);
                if (!s.equals("bye")) {
                    switch (s) {
                        case "1":
                            getUsers(writer);
                            break;
                        case "2":
                            sendMessage(reader, writer);
                            break;
                    }
                } else {
                    users.removeUser(nameUser);
                    break;
                }
                writer.write("\navailable options:\n" +
                        "1 get all users\n" +
                        "2 send message\n" +
                        "type 'bye' to exit\n");
                writer.flush();
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * метод авторизации пользователя
     * если пользователь с выбранным именем уже онлайн, необходимо ввести другое имя
     *
     * @param reader для чтения данных от пользователя
     * @param writer для вывода данных пользователю
     * @throws IOException исключение ввода-вывода
     */
    public void authorizeUser(InputStreamReader reader, OutputStreamWriter writer) throws IOException {
        writer.write("enter your name");
        writer.flush();
        char[] buf = new char[500];
        String name = new String(buf, 0, reader.read(buf, 0, buf.length));
        while (users.checkUserByName(name)) {
            writer.write("user '" + name + "' already online\nchoose another name");
            writer.flush();
            name = new String(buf, 0, reader.read(buf, 0, buf.length));
        }
        users.registerUser(name, socket);
        this.nameUser = name;
        writer.write("user '" + name + "' is authorized");
        writer.flush();
    }

    /**
     * метод получения списка пользователей онлайн
     *
     * @param writer для вывода данных пользователю
     */
    public void getUsers(OutputStreamWriter writer) {
        try {
            writer.write("\navailable users\n");
            for (String user : users.getUsers()) {
                if (user.equals(nameUser)) {
                    writer.write(user + " <- you\n");
                } else {
                    writer.write(user + "\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * метод отправки сообщения указанному пользователю
     * пользователь должен ввести сообщение в соответствии с указанным шаблоном
     * если заданный пользователь существует, ему будет отправлено соответствующее сообщение
     * в противном случае будет выведено сообщение о том, что заданный пользователь не онлайн
     *
     * @param reader для чтения данных от пользователя
     * @param writer для вывода данных пользователю
     */
    public void sendMessage(InputStreamReader reader, OutputStreamWriter writer) {
        try {
            writer.write("use the pattern 'user: message'\n");
            writer.flush();
            char[] buf = new char[500];
            String s = new String(buf, 0, reader.read(buf, 0, buf.length));
            String[] target = s.split(": ");
            if (users.checkUserByName(target[0])) {
                Socket targetSocket = users.getUserSocket(target[0]);
                OutputStreamWriter targetWriter = new OutputStreamWriter(targetSocket.getOutputStream());
                targetWriter.write("new message from " + nameUser + ": " + target[1] + "\n");
                targetWriter.flush();
                writer.write("message sent\n");
            } else {
                writer.write("user '" + target[0] + "' is not online");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
