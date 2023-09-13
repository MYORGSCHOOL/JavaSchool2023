package dushkina;

/**
 * Класс для выбрасывания исключений DictionaryImpl
 */
public class DictionaryException extends RuntimeException {
    public DictionaryException(String message) {
        super(message);
    }
}
