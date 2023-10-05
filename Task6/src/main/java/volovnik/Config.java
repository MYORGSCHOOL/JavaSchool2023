package volovnik;

/**
 * Класс конфигурации
 */
public class Config {

    /**
     * Ключ API
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

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
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
