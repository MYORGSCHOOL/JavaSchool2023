package grossu;

/**
 * Класс для выбрасывания исключений, получаемых при работе со DictionaryImpl
 */
public class DictionaryException extends RuntimeException {
    public DictionaryException(String message) {
        super(message);
    }
}
