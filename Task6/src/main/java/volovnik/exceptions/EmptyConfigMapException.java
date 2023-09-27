package volovnik.exceptions;

/**
 * Исключение, выбрасываемое при передаче пустой Map с конфигурацией
 */
public class EmptyConfigMapException extends RuntimeException {

    public EmptyConfigMapException(String message) {
        super(message);
    }
}
