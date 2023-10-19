package malakhova;

/**
 * Класс Config, представляющий конфигурационные настройки приложения.
 * Класс содержит 3 приватные переменные, а также геттеры и сеттеры на все переменные
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

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setMaxConnections(int maxConnection) {
        this.maxConnections = maxConnection;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
