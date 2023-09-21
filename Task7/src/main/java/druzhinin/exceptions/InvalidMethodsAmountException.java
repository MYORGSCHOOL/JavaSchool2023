package druzhinin.exceptions;

/**
 * Исключение для индикации неверного количества методов с определенными аннотациями.
 *
 * @author Дружинин Артем.
 */
public class InvalidMethodsAmountException extends RuntimeException {

    public InvalidMethodsAmountException() {

    }

    public InvalidMethodsAmountException(String message) {
        super(message);
    }
}
