package tumbaev;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckBracketsServer {
    private static final Logger logger = Logger.getLogger(CheckBracketsServer.class.getName());

    private static final int PORT = 8080;
    private static final String PATH = "/api/checkBrackets";

    /**
     * Starts the server on port {@link #PORT} and with path {@link #PATH}
     */
    public CheckBracketsServer() {
        HttpServer httpServer;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
            httpServer.createContext(PATH, new CheckBracketsHandler());
            httpServer.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to start the server");
            System.exit(-1);
        }
    }
}
