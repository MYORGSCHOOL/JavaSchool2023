package druzhinin.exceptions;

/**
 * Исключение для индикации отсутствия соотношения поля класса для некоторого ключа.
 *
 * @author Дружинин Артем.
 */
public class NoFieldCorrespondingToKeyException extends RuntimeException {
    public NoFieldCorrespondingToKeyException(String message) {
        super(message);
    }

    public NoFieldCorrespondingToKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
