package skrebkov;

/**
 * Клас для выбрасывания исключений в DictionaryImpl
 */
public class DictionaryException extends RuntimeException {
    public DictionaryException(String message) {
        super(message);
    }
}
