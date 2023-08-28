package druzhinin.exceptions;

/**
 * Исключение для индикации неправильного размера Map.
 *
 * @author Дружинин Артем.
 */
public class ConfigMapIncorrectSizeException extends RuntimeException {
    public ConfigMapIncorrectSizeException(String message) {
        super(message);
    }
}
