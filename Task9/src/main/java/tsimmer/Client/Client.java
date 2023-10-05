package tsimmer.Client;

/**
 * класс для запуска клиента
 */
public class Client {
    /**
     * ip адрес
     */
    private final static String ipAddr = "localhost";
    /**
     * порт
     */
    private final static int PORT = 2100;

    public static void main(String[] args) {
        new ClientHandler(ipAddr, PORT);
    }
}


