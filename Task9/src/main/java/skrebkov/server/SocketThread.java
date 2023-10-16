package skrebkov.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Optional;

public class SocketThread implements Runnable {
    /**
     * Имя пользователя в потоке
     */
    private String userName = "";
    private final Chat chat;
    /**
     * Сокет для подключения к пользователю в потоке
     */
    private final Socket socket;


    public SocketThread(Socket socket) {
        this.socket = socket;
        this.chat = Chat.getChat();
    }

    /**
     * Метод для обработки взаимодействия пользователя и сервера
     */
    @Override
    public void run() {
        System.out.println("New user connected to chat");
        try (var outputStream = socket.getOutputStream();
             var streamWriter = new OutputStreamWriter(outputStream);
             var outputText = new BufferedWriter(streamWriter);
             var inputStream = socket.getInputStream();
             var streamReader = new InputStreamReader(inputStream);
             var inputText = new BufferedReader(streamReader);
        ) {
            Message.sendTextIntoSocket(outputText, "Hello new user\n" +
                    "Set your name use command \"name|{myName}\"" +
                    "Send \"help|?\", to get a list of commands ");
            do {
                Optional<String> messageFromUser = Optional.of(Message.catchTextFromSocket(inputText));
                if (messageFromUser.isPresent()) {
                    System.out.println(messageFromUser.get());
                    String[] partOfMessage = messageFromUser.get().split("\\|");
                    Commands commands = new Commands(chat, outputText, userName);
                    switch (partOfMessage[0]){
                        case "name":
                            if(commands.setUserName(partOfMessage[1], socket)){
                                userName = partOfMessage[1];
                            }
                            break;
                        case "allUsers":
                            commands.getAllUsers();
                            break;
                        case "message":
                            commands.sendMessageToUserInChat(partOfMessage[1]);
                            break;
                        case "stop":
                            try {
                                Message.sendTextIntoSocket(outputText, "Bye");
                                socket.close();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "help":
                        default:
                            commands.helpMessage();
                    }
                }
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
