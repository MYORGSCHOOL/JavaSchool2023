package pozdnyakova.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Класс работы сервера
 */
public class SocketThread implements Runnable {
    /**
     * Сокет клиента
     */
    private Socket socketFromClient;

    public SocketThread(Socket socketFromClient) {
        this.socketFromClient = socketFromClient;
    }

    /**
     * Выполнение потока работы сервера
     */
    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketFromClient.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketFromClient.getOutputStream()));
            String nameClient = clientRegistration(bufferedWriter, bufferedReader);
            int choice = 0;
            while (choice != 4) {
                bufferedWriter.write("1. Общий список пользователей\n2. Список онлайн-пользователей\n3. Написать сообщение\n" +
                        "4. Завершение работы\n");
                bufferedWriter.flush();
                try {
                    choice = Integer.parseInt(bufferedReader.readLine());
                    if (choice > 4 || choice < 1) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    bufferedWriter.write("Введите число от 1 до 4\n");
                    bufferedWriter.flush();
                    choice = 0;
                }
                switch (choice) {
                    case 1: {
                        outputClients(bufferedWriter);
                        break;
                    }
                    case 2: {
                        outputOnlineClients(bufferedWriter);
                        break;
                    }
                    case 3: {
                        sendMessage(bufferedWriter, bufferedReader, nameClient);
                        break;
                    }
                    case 4: {
                        bufferedWriter.write("exit");
                        bufferedWriter.flush();
                    }
                }

            }
            socketFromClient.close();
            bufferedWriter.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для регистрации клиента
     *
     * @param bufferedWriter буфер записи в поток
     * @param bufferedReader буфер чтения из потока
     * @return имя зарегистрированного клиента
     * @throws IOException исключения при использовании BufferedWriter и BufferedReader
     */
    public String clientRegistration(BufferedWriter bufferedWriter, BufferedReader bufferedReader) throws IOException {
        bufferedWriter.write("Зарегистрируйтесь в системе. Введите имя:\n");
        bufferedWriter.flush();
        String name;
        do {
            name = bufferedReader.readLine();
        } while (!ClientBase.getInstance().addClient(socketFromClient, name, bufferedWriter));
        return name;
    }

    /**
     * Метод для вывода пользователю списка всех клиентов
     *
     * @param bufferedWriter буфер записи в поток
     * @throws IOException исключение при использовании BufferedWriter
     */
    public void outputClients(BufferedWriter bufferedWriter) throws IOException {
        ArrayList<String> names = new ArrayList<String>(ClientBase.getInstance().getHashMap().values());
        for (String i : names) {
            bufferedWriter.write(i + "\n");
            bufferedWriter.flush();
        }
    }

    /**
     * Метод для вывода пользователю списка онлайн-клиентов
     *
     * @param bufferedWriter буфер записи в поток
     * @throws IOException исключение при использовании BufferedWriter
     */
    public void outputOnlineClients(BufferedWriter bufferedWriter) throws IOException {
        ArrayList<Socket> sockets = new ArrayList(ClientBase.getInstance().getHashMap().keySet());
        for (Socket j : sockets) {
            if (!j.isClosed()) {
                bufferedWriter.write(ClientBase.getInstance().getHashMap().get(j) + "\n");
                bufferedWriter.flush();
            }
        }
    }

    /**
     * Метод для отправки сообщения от одного клиента другому
     *
     * @param bufferedWriter буфер записи в поток
     * @param bufferedReader буфер чтения из потока
     * @param name           имя клиента, который отправляет сообщение
     * @throws IOException исключение при использовании BufferedWriter и BufferedReader
     */
    public void sendMessage(BufferedWriter bufferedWriter, BufferedReader bufferedReader, String name) throws IOException {
        bufferedWriter.write("Введите имя пользователя, которому хотите отправить сообщение\n");
        bufferedWriter.flush();
        String recipient = bufferedReader.readLine();
        if (!ClientBase.getInstance().getHashMap().containsValue(recipient)) {
            bufferedWriter.write("Такого пользователя нет!\n");
            bufferedWriter.flush();
        } else {
            Socket socketRecipient = ClientBase.getInstance().getSocketClient(recipient);
            if (socketRecipient.isClosed()) {
                bufferedWriter.write("Этот пользователь сейчас не в сети!\n");
                bufferedWriter.flush();
            } else if (socketRecipient.equals(socketFromClient)) {
                bufferedWriter.write("Это ваше имя!\n");
                bufferedWriter.flush();
            } else {
                bufferedWriter.write("Введите сообщение:\n");
                bufferedWriter.flush();
                String message = bufferedReader.readLine();
                BufferedWriter sendBufferedWriter = new BufferedWriter(new OutputStreamWriter(socketRecipient.getOutputStream()));
                sendBufferedWriter.write(name + " пишет: " + message + "\n");
                sendBufferedWriter.flush();
                bufferedWriter.write("Сообщение отправлено!\n");
                bufferedWriter.flush();
            }
        }
    }
}
