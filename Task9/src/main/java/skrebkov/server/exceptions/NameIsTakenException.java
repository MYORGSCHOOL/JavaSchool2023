package skrebkov.server.exceptions;

public class NameIsTakenException extends RuntimeException{
    public NameIsTakenException(String message) {
        super(message);
    }
}
