package druzhinin.exceptions;

/**
 * Исключение для индикации ошибки вызова методов тестового класса.
 *
 * @author Дружинин Артем.
 */
public class FailedTestInvocationException extends RuntimeException {
    public FailedTestInvocationException() {
    }

    public FailedTestInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
