package babadzhanov;

/**
 * Класс представляющий конфигурационные настройки
 */
public class Config {

    private String apiKey;
    private int maxConnections;
    private boolean debugMode;

    public String getApiKey() {
        return apiKey;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    @Override
    public String toString() {
        return "Config{" +
                "apiKey='" + apiKey + '\'' +
                ", maxConnections=" + maxConnections +
                ", debugMode=" + debugMode +
                '}';
    }
}
