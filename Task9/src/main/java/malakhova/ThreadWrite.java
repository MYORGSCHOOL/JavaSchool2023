package malakhova;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Поток, читающий введенную клиентом информацию
 */
public class ThreadWrite extends Thread {
    private final Socket fromClientSocket;
    private final Scanner input;
    private static final String EXIT = "4";

    public ThreadWrite(Socket socket) {
        this.fromClientSocket = socket;
        this.input = new Scanner(System.in);
    }

    @Override
    public void run() {
        try (PrintWriter pw = new PrintWriter(fromClientSocket.getOutputStream(), true)) {
            String str = new String();
            while (!str.equals(EXIT)) {
                str = input.nextLine();
                pw.println(str);
            }
            fromClientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
