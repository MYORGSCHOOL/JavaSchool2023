package skrebkov.server;

import skrebkov.server.exceptions.NoSuchUserException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * Класс исполняющий команды из чата
 */
public class Commands {
    private final Chat chat;
    private final String userName;
    private final BufferedWriter bufferedWriter;

    public Commands(Chat chat, BufferedWriter bufferedWriter, String userName) {
        this.chat = chat;
        this.bufferedWriter = bufferedWriter;
        this.userName = userName;
    }

    /**
     * Записать имя пользователя в чат
     *
     * @param userName   - имя пользователя
     * @param userSocket - сокет пользователя
     * @return true если пользователь успешно зарегистрирован, false если нет
     */
    public boolean setUserName(String userName, Socket userSocket) throws IOException {
        if (!this.userName.isEmpty()) {
            Message.sendTextIntoSocket(bufferedWriter, "You already registered");
            return false;
        }
        try {
            chat.addUser(userName, userSocket);
            Message.sendTextIntoSocket(bufferedWriter, "Registered user");
        } catch (RuntimeException e) {
            System.err.println(e);
            Message.sendTextIntoSocket(bufferedWriter, e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Отправить список имен пользователей
     */
    public void getAllUsers() throws IOException {
        String answer;
        try {
            answer = chat.getAllNames();
        } catch (RuntimeException e) {
            System.err.println(e);
            Message.sendTextIntoSocket(bufferedWriter, e.getMessage());
            return;
        }
        Message.sendTextIntoSocket(bufferedWriter, answer);
    }

    /**
     * Отправить сообщение пользователю в чат, структура сообщения должна быть такой message|{userName}:{messageText}
     */
    public void sendMessageToUserInChat(String message) throws IOException {
        String[] splitMessage = message.split(":");
        if (splitMessage.length == 2) {
            try {
                BufferedWriter acceptorWriter = chat.getUserWriter(splitMessage[0]);
                Message.sendTextIntoSocket(acceptorWriter, userName + ":" + splitMessage[1]);
                Message.sendTextIntoSocket(bufferedWriter, "Message send to user: " + splitMessage[0]);
            } catch (RuntimeException e){
                Message.sendTextIntoSocket(bufferedWriter, e.getMessage());
            }
            return;
        }
        Message.sendTextIntoSocket(bufferedWriter, "Something went wrong");
    }

    /**
     * Сообщение со справочной информацией
     * @throws IOException
     */
    public void helpMessage() throws IOException {
        Message.sendTextIntoSocket(bufferedWriter, "List of commands:\n" +
                "name|{youName} - set your name in chat\n" +
                "allUsers - show all users in chat\n" +
                "message|{userName}:{messageText} - send message to user\n" +
                "stop - for close chat");
    }
}
