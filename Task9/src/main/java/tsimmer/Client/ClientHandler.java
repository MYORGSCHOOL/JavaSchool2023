package tsimmer.Client;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * класс, в котором обрабатывается подключение клиента к серверу
 */
public class ClientHandler {
    /**
     * Сокет
     */
    private final Socket socket;
    /**
     * Поток чтения из сокета
     */
    private BufferedReader in;
    /**
     * Поток записи в сокет
     */
    private BufferedWriter out;
    /**
     * Поток чтения с консоли
     */
    private BufferedReader inputUser;
    /**
     * логин пользователя
     */
    private String nickName;

    /**
     * Конструктор, который принимает клиентский сокет и сервер
     *
     * @param addr адрес порта
     * @param port номер порта
     */

    public ClientHandler(String addr, int port) {
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname(); // запрос имени пользователя
            new ReadMsg().start(); // поток читающий сообщения из сокета в бесконечном цикле
            new WriteMsg().start(); // поток пишущий сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            ClientHandler.this.downService();
        }
    }

    /**
     * Метод, передающий на сервер имя пользователя
     */
    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickName = inputUser.readLine();
            out.write(nickName + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    /**
     * Метод, закрывающий сокет
     */
    public void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Поток читающий сообщения с сервера
     */
    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine(); // ждем сообщения с сервера
                    if (str.equals("stop")) {
                        ClientHandler.this.downService();
                        break; // выходим из цикла если пришло "stop"
                    }
                    System.out.println(str); // пишем сообщение с сервера на консоль
                }
            } catch (IOException e) {
                ClientHandler.this.downService();
            }
        }
    }

    /**
     * Поток отправляющий сообщения с консоли на сервер
     */
    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    Date date = new Date(); // текущая дата
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                    String time = simpleDateFormat.format(date); // время
                    userWord = inputUser.readLine(); // сообщения с консоли
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        ClientHandler.this.downService();
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        out.write("(" + time + ") " + nickName + ": " + userWord + "\n"); // отправляем на сервер
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientHandler.this.downService();
                }
            }
        }
    }
}

