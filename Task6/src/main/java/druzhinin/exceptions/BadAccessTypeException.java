package druzhinin.exceptions;

/**
 * Исключение для индикации неправильных прав доступа.
 *
 * @author Дружинин Артем.
 */
public class BadAccessTypeException extends RuntimeException {
    public BadAccessTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
