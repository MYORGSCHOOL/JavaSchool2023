package tumbaev.exception;

/**
 * Thrown when a test fails
 */
public class AssertionFailError extends AssertionError {

    public AssertionFailError(String message) {
        this(message, null);
    }

    public AssertionFailError(String message, Throwable cause) {
        super(message, cause);
    }
}
