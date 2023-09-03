package tumbaev.exception;

import tumbaev.annotation.TestMethod;

/**
 * Thrown if there is no method annotated with {@link TestMethod} in test class
 */
public class NoTestMethodsException extends RuntimeException {
    public NoTestMethodsException(String message) {
        super(message);
    }
}
