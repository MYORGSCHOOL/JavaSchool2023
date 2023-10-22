package dushkina;

/**
 * Класс Config, представляющий конфигурационные настройки приложения
 */
public class Config {
    /**
     * Ключ Api
     */
    private String apiKey;
    /**
     * Максимальное количество подключений
     */
    private int maxConnections;
    /**
     * Режим отладки
     */
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
