package tumbaev.exception;

/**
 * Thrown when test fails to run not because of assertion failure, but because of other reason
 */
public class TestRunningException extends RuntimeException {
    public TestRunningException(String message, Throwable cause) {
        super(message, cause);
    }
}
