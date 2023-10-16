package skrebkov.server.exceptions;

public class ChatIsEmptyException extends  RuntimeException {
    public ChatIsEmptyException(String message) {
        super(message);
    }
}
