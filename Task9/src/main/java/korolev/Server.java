package korolev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Server class, for working with clients.
 * @author Nikita Korolev
 * @version 1.0
 */
public class Server {
    private static ServerSocket server;
    private static List<Thread> list;
    static List<Users> users;
    static Map<Long, Socket> connection;
    private static Socket temp;

    /**
     * The main server method accepts clients, 
     * registers them and starts new threads({@code Massanger}) for them.
     * @param args
     * @throws Exception Error connecting to client.
     */
    public static void main(String[] args) throws Exception {
        server = new ServerSocket(1777);
        list = new ArrayList<>();
        users = new ArrayList<>();
        connection = new HashMap<>();
        int i = 0;

        try {
            while (true) {
                System.out.println("Ожидание подключения...");
                temp = server.accept();
                list.add(new Thread(new Massanger(temp, i + 1)));
                list.get(i).start();
                connection.put(list.get(i).getId(), temp);
                i++;
                System.out.printf("Пользователь %d подключён!\n", i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            server.close();
        }
    }
}

/**
 * Class Runnable for running a messenger in a thread.
 * @author Nikita Korolev
 * @version 1.0
 */
class Massanger implements Runnable { 
    private final Socket socket;
    private String name;
    private int numaber = 0;
    private InputStream iStream;
    private OutputStream oStream;
    private BufferedReader reader;
    private PrintWriter writer;
    private Users client;
    
    private PrintWriter writerEx;
    private Socket userSocket;

    /**
     * Constructor for client authorization.
     * @param s Connected client socket.
     * @param n Online client number.
     * @throws Exception Error connecting to client.
     */
    public Massanger(Socket s, int n) throws Exception {
        socket = s;
        numaber = n;
        client = new Users(true);
        client.setID(22 + numaber);

        iStream = socket.getInputStream();
        oStream = socket.getOutputStream();

        reader = new BufferedReader(new InputStreamReader(iStream));
        writer = new PrintWriter(oStream, true);
    }

    /**
     * User registration method, generates information 
     * for the {@link Users} class, changes the name of 
     * the current thread to the client login.
     * @throws Exception Error connecting to client.
     */
    private void getNameClient() throws Exception {
        writer.println(new String("Введите ваше имя: "));
        name = reader.readLine();
        client.setLogin(name);
        Server.users.add(client);

        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (client.getID() == t.getId()) {
                t.setName(name);
                System.out.println("Зарегистрирован пользователь: " + t.getName());
                break;
            }
        }
    }

    /**
     * The method sends the client a list 
     * of active users on the server.
     * @throws Exception Error connecting to client.
     */
    private void getRecipient() throws Exception {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getId() > 22) {
                writer.println(t.getName() + ":\t id(" + t.getId() + ")");
            }
        }
        writer.println(new String("end"));
    }

    /**
     * The method connects the threads of 2 clients.
     * @param log Client login to connect to it.
     * @throws Exception Error connecting to client.
     */
    private void getSockedUser(String log) throws Exception {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(log) && t.isAlive()) {
                userSocket = Server.connection.get(t.getId());
                break;
            }
        }

        writerEx = new PrintWriter(userSocket.getOutputStream(), true);
    }

    /**
     * The method starts a chat between 
     * clients in 2 states, waits - sends, sends - waits.
     * @param log Client login to connect to it.
     * @param b Waiting state.
     * @throws Exception Error connecting to client.
     */
    private void startMassage(String log, Boolean b) throws Exception {
        String answerUser;
        String massageUser;
        boolean flag = b;
        getSockedUser(log);

        synchronized(this) {
            while (true) {
                if (flag) {
                    answerUser = reader.readLine();
                    System.out.println("Полученно сообщение от " + name);
                    writerEx.println(new String(name + ": " + answerUser));
                    flag = false;
                } else {
                    massageUser = reader.readLine();
                    writer.println(massageUser);
                    flag = true;
                }   
            }
        }

    }

    /**
     * The method accepts the user's 
     * state selection for launching the messenger.
     * @throws Exception Error connecting to client.
     */
    private void goChat() throws Exception {
        String loginUser = reader.readLine();
        String answer = reader.readLine();

        switch (answer) {
            case "start":
                startMassage(loginUser, true);
                break;

            case "wait":
                startMassage(loginUser, false);
                break;

            default:
                break;
        }
    }

    @Override
    /**
     * Implementable method for starting a client thread, 
     * registering it and launching the messenger.
     */
    public void run() {
        try {
            getNameClient();
            getRecipient();
            goChat();
        } catch (Exception ignored) {
        } finally {
            System.out.printf("Клиент %d отключён!\n", numaber);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
