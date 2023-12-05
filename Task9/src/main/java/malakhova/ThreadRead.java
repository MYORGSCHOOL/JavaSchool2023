package malakhova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Поток, печатающий на экран информацию для клиента
 */
public class ThreadRead extends Thread {
    private final Socket fromClientSocket;
    private static final String EXIT = "4";

    public ThreadRead(Socket socket) {
        this.fromClientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()))) {
            String str;
            while (!(str = br.readLine()).equals(EXIT)) {
                System.out.println(str);
            }
            fromClientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
