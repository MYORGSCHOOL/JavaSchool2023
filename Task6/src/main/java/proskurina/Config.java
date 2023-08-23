package proskurina;

/**
 * Класс конфигурационных настроек приложения.
 */
public class Config {
    private String apiKey;
    private int maxConnections;
    private boolean debugMode;
    
    public String getApiKey() {
        return this.apiKey;
    }
    
    public int getMaxConnections() {
        return this.maxConnections;
    }
    
    public boolean isDebugMode() {
        return this.debugMode;
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