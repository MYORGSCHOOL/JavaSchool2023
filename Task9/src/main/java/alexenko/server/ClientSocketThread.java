package alexenko.server;

import java.io.*;
import java.net.Socket;

/**
 * Поток реализующий взаимодействия с подключенным к серверу клиентом.
 */
public class ClientSocketThread extends Thread {

    /**
     * Прощальное для клиента сообщение.
     */
    private static final String FAREWELL_MESSAGE_TO_CLIENT = "Goodbye!";

    /**
     * Сообщение для клиента, который пытался отправить сообщение другому клиенту.
     */
    private static final String MESSAGE_FOR_OTHER_CLIENT_NOT_SENT = "Ваше сообщение другому пользователю не отправлено";

    /**
     * Шаблон сообщения, которое надо отправить другому клиенту.
     */
    private static final String TEMPLATE_MESSAGE = "ИМЯ_ПОЛУЧАТЕЛЯ:СООБЩЕНИЕ";

    /**
     * Флаг для пометки текущего пользователя, онлайн или не онлайн.
     */
    private boolean isOnline;

    /**
     * Флаг для пометки текущего потока, как взаимодействующего с пользователем.
     */
    private boolean runnable;

    /**
     * Переключатель между стадиями клиентского меню.
     */
    private ClientMenu menuStage;

    /**
     * Сокет клиента, с которым общается данный поток.
     */
    private final Socket socket;

    /**
     * Reader дял чтения сообщений от клиента.
     */
    private final BufferedReader reader;

    /**
     * Writer для отправки сообщений клиенту.
     */
    private final PrintWriter writer;

    /**
     * Имя того клиента, с которым взаимодействует поток.
     */
    private String clientName;

    /**
     * Конструктор инициализирующий начальное состояние потока.
     *
     * @param socket сокет пользователя, с которым будет реализовано общение.
     * @throws IOException исключение, которое может быть выброшено, если не удалось создать IO потоки с пользователем.
     */
    public ClientSocketThread(Socket socket) throws IOException {
        this.isOnline = false;
        this.runnable = true;
        this.menuStage = ClientMenu.NEW_CLIENT;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    /**
     * Пока поток активен, взаимодействует с пользователем в зависимости от условия.
     */
    @Override
    public void run() {
        while (this.runnable) {
            if (this.menuStage == ClientMenu.NEW_CLIENT) {
                this.runnable = stageNewClient();
            } else if (this.menuStage == ClientMenu.REGISTRATION_PROCESS) {
                this.runnable = stageRegistration();
            } else if (this.menuStage == ClientMenu.REGISTERED_CLIENT) {
                this.runnable = stageRegisteredClient();
            } else if (this.menuStage == ClientMenu.CHAT) {
                this.runnable = stageChat();
            }
        }
        clientDisconnection();
    }

    /**
     * Стадия нового клиента, для только что подключенного к серверу клиента.
     *
     * @return состояние потока, в зависимости от выбора клиента
     */
    private boolean stageNewClient() {
        String response = getResponseFromClient();
        if (response.equals(ClientMenu.getExit())) {
            return false;
        }
        if (response.equals(ClientMenu.NEW_CLIENT.getPointOne())) {
            this.menuStage = ClientMenu.REGISTRATION_PROCESS;
        }
        return true;
    }

    /**
     * Стадия регистрации нового клиента.
     *
     * @return true - пользователь зарегистрирован и переход к следующей стадии меню, или повторная попытка регистрации.
     */
    private boolean stageRegistration() {
        this.clientName = getResponseFromClient();
        try {
            ClientStorage.CLIENTS_ONLINE.add(this.clientName, this.socket);
            this.menuStage = ClientMenu.REGISTERED_CLIENT;
            this.isOnline = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Стадия зарегистрированного клиента, для тех клиентов, которые указали своё имя серверу.
     *
     * @return состояние потока, в зависимости от выбора клиента
     */
    private boolean stageRegisteredClient() {
        String response = getResponseFromClient();
        if (response.equals(ClientMenu.getExit())) {
            return false;
        }
        if (response.equals(ClientMenu.REGISTERED_CLIENT.getPointOne())) {
            this.menuStage = ClientMenu.CHAT;
        }
        return true;

    }

    /**
     * Стадия чата, пользователь может отправлять сообщения другим пользователям.
     *
     * @return состояние потока, в зависимости от выбора клиента
     */
    private boolean stageChat() {
        sendMenuStateToClient();
        String request = "";
        while (true) {
            request = getMessageFromClient();
            if (request.equals(ClientMenu.getExit())) {
                this.menuStage = ClientMenu.REGISTERED_CLIENT;
                return true;
            } else if (request.equals(ClientMenu.CHAT.getPointOne())) {
                showAllClientsOnline();
            } else {
                try {
                    trySendMessageOtherClient(request);
                } catch (IOException e) {
                    e.printStackTrace();
                    sendMessageToClient(MESSAGE_FOR_OTHER_CLIENT_NOT_SENT);
                }
            }
        }
    }

    /**
     * Просмотр всех клиентов онлайн.
     * (Онлайн - это те, которые указали своё имя)
     */
    private void showAllClientsOnline() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Клиентов онлайн: ");
        var names = ClientStorage.CLIENTS_ONLINE.getAllNames();
        if (names.isEmpty()) {
            buffer.append("0\n");
        } else {
            buffer.append(names.size()).append("\n");
            for (var name : names) {
                buffer.append(name).append("\n");
            }
        }
        writer.println(buffer);
    }

    /**
     * Попытка отправки сообщения другому клиенту.
     *
     * @param request запрос от клиента
     * @throws IOException ошибка отправки сообщения другому клиенту
     */
    private void trySendMessageOtherClient(String request) throws IOException {
        String[] parts = request.split(":");
        if (!requestIsValid(parts)) {
            return;
        }
        int nameOtherClient = 0;
        int message = 1;
        sendMessageOtherClient(parts[nameOtherClient], parts[message]);
    }

    /**
     * Проверка валидности полученного запроса от пользователя.
     * Запрос должен соответствовать TEMPLATE_MESSAGE.
     *
     * @param parts части полученного запроса
     * @return true - запрос от клиента валиден, false - запрос не валиден
     */
    private boolean requestIsValid(String[] parts) {
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            sendMessageToClient("Сервер: " + TEMPLATE_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Отправка сообщения другому клиенту.
     *
     * @param nameOtherClient имя другого клиента
     * @param message         сообщение для другого клиента
     * @throws IOException ошибка отправки сообщения другому клиенту
     */
    private void sendMessageOtherClient(String nameOtherClient, String message) throws IOException {
        Socket socketOtherClient = ClientStorage.CLIENTS_ONLINE.getSocket(nameOtherClient);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socketOtherClient.getOutputStream()), true);
        writer.println(this.clientName + ": " + message);
    }

    /**
     * Получить ответ от клиента на предоставленное меню действий.
     *
     * @return ответ клиента
     */
    private String getResponseFromClient() {
        sendMenuStateToClient();
        return getMessageFromClient();
    }

    /**
     * Отправка клиенту меню действий.
     */
    private void sendMenuStateToClient() {
        writer.println(this.menuStage.getMessage());

    }

    /**
     * Отправка клиенту сообщения.
     *
     * @param message сообщение для клиента
     */
    private void sendMessageToClient(String message) {
        writer.println(message);
    }

    /**
     * Получить сообщение от клиента.
     * Пока сообщение пустое, сервер его не принимает.
     *
     * @return
     */
    private String getMessageFromClient() {
        String message = "";
        while (message.equals("")) {
            try {
                message = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
                message = ClientMenu.getExit();
            }
        }
        return message;
    }

    /**
     * Отключение клиента от сервера.
     */
    private void clientDisconnection() {
        if (this.isOnline) {
            ClientStorage.CLIENTS_ONLINE.remove(this.clientName);
        }
        writer.println(FAREWELL_MESSAGE_TO_CLIENT);
    }
}