package grossu;

/**
 * Класс с исключениями для класса {@link ConfigLoader}
 */
public class ConfigLoadException extends RuntimeException {
    public ConfigLoadException(String message) {
        super(message);
    }
}
