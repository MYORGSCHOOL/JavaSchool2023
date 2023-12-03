package abdullaeva.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Класс, реализующий стратегию взаимодействий клиента с сервером
 */
public class InteractionThreads implements Runnable {

    /**
     * Сокет (точка коммуникации) клиента.
     */
    private final Socket socket;

    /**
     * Хранилище зарегистрированных онлайн-клиентов.
     */
    private final OnlineClientBase clients;

    /**
     * Имя текущего клиента сервера.
     */
    private String clientName;

    public InteractionThreads(Socket socket) {
        this.socket = socket;
        this.clients = OnlineClientBase.getInstance();
    }

    /**
     * Метод для запуска клинт-сервисного взаимодействия приложений
     * Когда метод вызывается, приложение-клиент предлагает пользователю
     * зарегистрироваться в качестве онлайн-клиента. Дальнейшие опции
     * клиент-серверного взаимодействия доступны после регистрации.
     * Зарегистрированный клиент может посмотреть список всех онлайн-клиентов
     * и написать сообщение любому из онлайн-клиентов.
     * Чтобы выйти с сервера клиенту достаточно ввести "exit".
     */
    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             var inputStreamReader = new InputStreamReader(inputStream);
             OutputStream outputStream = socket.getOutputStream();
             var outputStreamWriter = new OutputStreamWriter(outputStream)) {
            System.out.println("Connection success");
            outputStreamWriter.write("Hello! Please register to use more options \n");

            registerClient(inputStreamReader, outputStreamWriter);

            outputStreamWriter.write("\nOptions available for use:\n" +
                    "1: Get all online clients. \n" + "2: Send message. \n" + "Or write 'exit' to exit.\n");
            outputStreamWriter.flush();
            char[] batch = new char[512];
            int size;
            while ((size = inputStreamReader.read(batch, 0, batch.length)) != -1) {
                String result = new String(batch, 0, size);
                System.out.println("Client write: " + result);
                if (!result.equals("exit")) {
                    switch (result) {
                        case "1":
                            getOnlineClients(outputStreamWriter);
                            break;
                        case "2":
                            sendMessageToOtherOnlineClient(inputStreamReader, outputStreamWriter);
                            break;
                    }
                } else {
                    clients.deleteOnlineClientByName(clientName);
                    break;
                }
                outputStreamWriter.write("\nOptions available for use:\n" +
                        "1: Get all online clients. \n" + "2: Send message. \n" + "Or write 'exit' to exit.\n");
                outputStreamWriter.flush();
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("run: Error input/output threads", e);
        }
    }

    /**
     * Метод для отправки сообщения от одного клиента другому.
     * Клиенту нужно ввести сообщение согласно шаблону "имя клиента: сообщение".
     * Если клиент зарегистрирован как онлайн-клиент, ему будет отправлено сообщение.
     * Если клиент не зарегистрирован, об этом выведется сообщение.
     * @param reader - поток для чтения введённых клиентом данных
     * @param writer - поток для вывода данных клиенту.
     */
    private void sendMessageToOtherOnlineClient(InputStreamReader reader, OutputStreamWriter writer) {
        try {
            writer.write("Send a message using the template: 'client: message'\n");
            writer.flush();
            char[] batch = new char[512];
            String result = new String(batch, 0, reader.read(batch, 0, batch.length));
            String[] otherClient = result.split(": ");
            if (!clients.findIfClientIsOnlineByName(otherClient[0])) {
                writer.write("Client with name " + otherClient[0] + "isn't online registred");
            } else {
                Socket otherSocket = clients.getOnlineClientsSocketByName(otherClient[0]);
                OutputStreamWriter otherWriter = new OutputStreamWriter(otherSocket.getOutputStream());
                otherWriter.write("A new message has been sent to you from client: " + clientName + " message: "
                + otherClient[1] + "\n");
                otherWriter.flush();
                writer.write("Message sent to: " + otherClient[0] + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("sendMessageToOtherOnlineClient: Error input/output threads", e);
        }
    }

    /**
     * Метод для получения списка зарегистрированных онлайн-клиентов.
     * Метод выведет всех онлайн-клиентов, включая клиента, который его вызывает.
     * Клиент в списке будет отображён с припиской "- is you".
     * @param writer - поток для вывода данных пользователю.
     */
    private void getOnlineClients(OutputStreamWriter writer) {
        try {
            writer.write("\nClients in online:\n");
            for (String client : clients.getAllOnlineClients()) {
                if (!client.equals(clientName)) {
                    writer.write(client + "\n");
                } else {
                    writer.write(client + " - is you\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("getOnlineClients: Error input/output threads", e);
        }
    }

    /**
     * Метод для регистрации клиента как онлайн-клиента.
     * Пользователю необходимо ввести своё клиентское имя. Если клиент с таким
     * именем на сервере уже есть, выведется предупреждение, после которого нужно
     * повторить ввод другого имени.
     * @param reader - поток для чтения данных от клиента.
     * @param writer - поток для вывода данных клиенту.
     */
    public void registerClient(InputStreamReader reader, OutputStreamWriter writer) throws IOException {
        writer.write("Enter you name for registration:");
        writer.flush();
        char[] batch = new char[512];
        String clientName = new String(batch, 0, reader.read(batch, 0, batch.length));
        while (clients.findIfClientIsOnlineByName(clientName)) {
            writer.write("Client with name " + clientName + " already registered.\nPlease write another name");
            writer.flush();
            clientName = new String(batch, 0, reader.read(batch, 0, batch.length));
        }
        clients.clientRegistration(clientName, socket);
        this.clientName = clientName;
        writer.write("Client with name " + clientName + "registered");
        writer.flush();
    }
}
