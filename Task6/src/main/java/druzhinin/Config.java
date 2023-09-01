package druzhinin;

/**
 * Класс, представляющий конфигурационные настройки приложения.
 *
 * @author Дружинин Артем.
 */
public class Config {
    /**
     * Ключ программного интерфейса приложения.
     */
    private String apiKey;

    /**
     * Максимальное число активных подключений.
     */
    private int maxConnections;

    /**
     * Включен ли режим отладки. true - включен, иначе - false.
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
