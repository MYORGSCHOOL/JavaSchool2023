package dushkina;

/**
 * Класс для выбрасывания исключений ConfigLoader
 */
public class ConfigLoaderException extends RuntimeException {
    public ConfigLoaderException(String message) {
        super(message);
    }
}
