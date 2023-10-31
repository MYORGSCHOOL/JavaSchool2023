package savinskiy.common;

public class ChatConstants {
    public static final String RESET_COLOR = "\u001B[0m";    // Сброс цвета
    public static final String RED_COLOR = "\u001B[31m";     // Красный
    public static final String YELLOW_COLOR = "\u001B[33m";  // Желтый

    public static final String HOST = "localhost";
    public static final int PORT = 1804;

    public static final String INFO_MESSAGE =
                    "╔════════════════════════════════════════════════╗\n" +
                    "║   What this chat can do? (Spoiler!!! Not much) ║\n" +
                    "╠════════════════════════════════════════════════╣\n" +
                    "║                                                ║\n" +
                    "║ 1. Print users online. Just print              ║\n" +
                    "║ «PRINT USERS»                                  ║\n" +
                    "║                                                ║\n" +
                    "║ 2. Send messages in format                     ║\n" +
                    "║ <USER_TO_WHOM_SEND_A_MESSAGE>:<MESSAGE>        ║\n" +
                    "║                                                ║\n" +
                    "║                    THAT’S IT                   ║\n" +
                    "╚════════════════════════════════════════════════╝";
    public static final String CLIENTS_IN_THE_ROOM = "TWO CLIENTS IN THE ROOM. NOW WE CAN CHAT";
    public static final String WRONG_FORMAT = "Wrong format! Example '<username_to_whom>:<message>'";
    public static final String CONNECTED = "Connected to server";
    public static final String ENTER_USERNAME = "ENTER YOUR USERNAME: ";
}
