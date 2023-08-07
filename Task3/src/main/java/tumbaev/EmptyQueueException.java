package tumbaev;

/**
 * Thrown when trying to remove an element from an empty stack
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String message) {
        super(message);
    }
}
