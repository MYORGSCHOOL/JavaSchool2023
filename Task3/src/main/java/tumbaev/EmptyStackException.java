package tumbaev;

/**
 * Thrown when trying to remove an element from an empty stack
 */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException(String message) {
        super(message);
    }
}
