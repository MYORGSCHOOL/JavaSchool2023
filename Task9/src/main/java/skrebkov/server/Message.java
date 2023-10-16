package skrebkov.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Класс для получения и отправки сообщений
 */
public class Message {
    /**
     * Метод для написания и отправки текста сообщения
     * @param outputText - объект для отправки сообщения пользователю
     * @param message - текст сообщения
     * @throws IOException
     */
    public static void sendTextIntoSocket(BufferedWriter outputText, String message) throws IOException {
        outputText.write(message + "\n");
        outputText.flush();
    }

    /**
     * Метод для получения сообщений от пользователя
     * @param inputText - объект для чтения входящего символьного потока
     * @return - строку
     * @throws IOException
     */
    public static String catchTextFromSocket(BufferedReader inputText) throws IOException {
        return inputText.readLine();
    }
}
