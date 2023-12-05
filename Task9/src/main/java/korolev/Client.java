package korolev;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * {@code Client} class, for connecting to the {@link Server}
 * @author Nikita Korolev
 * @version 1.0
 */
public class Client {
    private static String answer;
    private static String massage;
    private static Socket socket;
    private static BufferedReader reader;
    private static PrintWriter writer;
    private static Scanner in = new Scanner(System.in);
    private static String login;

    /**
     * The main method for launching the client.
     * @param args
     * @throws Exception Error connecting to the {@link Server}.
     */
    public static void main(String[] args) throws Exception {
        socket = new Socket("127.0.0.1", 1777);
        InputStream iStream = socket.getInputStream();
        OutputStream oStream = socket.getOutputStream();

        reader = new BufferedReader(new InputStreamReader(iStream));
        writer = new PrintWriter(oStream, true);

        try {
            register();
            go();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            socket.close();
            in.close();
            reader.close();
            writer.close();
        }
    }

    /**
     * Method for registering a client with the server.
     * @throws Exception Error connecting to the {@link Server}.
     */
    private static void register()
    throws Exception {
        System.out.println("Ожидание ответа...");
        answer = reader.readLine();
        System.out.print(answer);

        massage = in.nextLine();
        writer.println(massage);
    }

    /**
     * Messenger launch method.
     *
     * At the client's choice: either connect
     * to another client or wait for a message from him.
     * @throws Exception Error connecting to the {@link Server}.
     */
    private static void go() throws Exception {        
        System.out.println("Пользователей в сети: ");
            do {
                answer = reader.readLine();
                if (answer.equals("end")) {
                    break;
                }

                System.out.println(answer);
            } while (answer != "end");

        System.out.println("Ввыберите действие: ");
        System.out.println("1. Начать чат первым;");
        System.out.println("2. Ожидать сообщение от пользователя;");
        System.out.println("3. Выход.");
        
        System.out.print(":>> ");
        int choise = in.nextInt();
        switch (choise) {
            case 1:
                getUser();
                setMassage();
                break;

            case 2:
                getUser();
                waitMassage();
                break;

            default:
                break;
        }
    }

    @SuppressWarnings("resource")
    /**
     * When you select a method to send a message, 
     * runs this method, the method connects 
     * to another client and starts the chat first.
     * @throws Exception Error connecting to the {@link Server}.
     */
    private static void setMassage() throws Exception {
        writer.println(new String("start"));
        Scanner insert = new Scanner(System.in);

        while (!answer.equals("bye")) {
            System.out.print("Введите сообщение: ");
            massage = insert.nextLine();
            writer.println(massage);

            System.out.println("Ожидание сообщения...");
            answer = reader.readLine();
            System.out.println(answer);

            if (answer.equals("bye")) {
                break;
            }
        }
        socket.close();
    }
    
    @SuppressWarnings("resource")
    /**
     * When you choose to wait for a message, 
     * this method runs, the method waits for 
     * a connection and the first message from another client.
     * @throws Exception Error connecting to the {@link Server}.
     */
    private static void waitMassage() throws Exception {
        writer.println("wait");
        Scanner insert = new Scanner(System.in);

        while (!answer.equals("bye")) {
            System.out.println("Ожидание сообщения...");
            answer = reader.readLine();
            System.out.println(answer);

            if (answer.equals("bye")) {
                break;
            }

            System.out.print("Введите сообщение: ");
            massage = insert.nextLine();
            writer.println(massage);
        }
        socket.close();
    }

    @SuppressWarnings("resource")
    /**
     * The method receives a list of active users on the {@link Server}.
     */
    private static void getUser() {
        Scanner insert = new Scanner(System.in);
        System.out.print("Введите логин получателя: ");
        login = insert.nextLine();
        writer.println(login);
    }
}
