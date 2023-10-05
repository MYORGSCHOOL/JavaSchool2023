package volovnik.exceptions;

/**
 * Исключение, выбрасываемое при некорректном имени поля
 */
public class IncorrectConfigFieldException extends RuntimeException {

    public IncorrectConfigFieldException(String message) {
        super(message);
    }
}
