package druzhinin.exceptions;

/**
 * Исключение для индикации ошибки создания экземпляра тестового класса.
 *
 * @author Дружинин Артем.
 */
public class FailedTestClassInstantiatingException extends RuntimeException {
    public FailedTestClassInstantiatingException() {
    }

    public FailedTestClassInstantiatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
