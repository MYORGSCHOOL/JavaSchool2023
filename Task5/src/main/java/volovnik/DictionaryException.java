package volovnik;

/**
 * Тип исключения, выбрасываемого в реализации справочника
 */
public class DictionaryException extends RuntimeException {
    public DictionaryException(String message) {
        super(message);
    }
}
