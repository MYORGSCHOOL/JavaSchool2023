package tsimmer.Server;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс реализующий работу сервера
 */
public class ServerHandler extends Thread {
    /**
     * сокет, через который сервер общается с клиентом,
     */
    private Socket socket;
    /**
     * поток чтения из сокета
     */
    private BufferedReader in;
    /**
     * поток записи в сокет
     */
    private BufferedWriter out;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Server.story.printStory(out); // поток вывода - передаётся для передачи истории последних 10 сообщений
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            // первое сообщение отправленное сюда - это никнейм
            word = in.readLine();
            out.write("Hello " + word + "\n");
            out.flush(); // flush() нужен для выталкивания оставшихся данных
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (true) {
                word = in.readLine();
                if (word.equals("stop")) {
                    for (ServerHandler vr : Server.serverList) {
                        if (vr.equals(this)) {
                            vr.interrupt();
                        }
                    }
                    this.downService();
                    break;
                }
                System.out.println("Echoing: " + word);
                Server.story.addStoryMsg(word);
                for (ServerHandler vr : Server.serverList) {
                    vr.send(word); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
                }
            }
        } catch (IOException | NullPointerException ignored) {
            this.downService();
        }
    }

    /**
     * Метод для отправки одного сообщения клиенту по указанному потоку
     *
     * @param msg сообщение
     */
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    /**
     * закрытие сервера
     * прерывание потока и удаление из списка потоков
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerHandler vr : Server.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }
}