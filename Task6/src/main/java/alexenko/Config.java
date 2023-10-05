package alexenko;

import java.util.Objects;


/**
 * Класс конфигурации.
 */
public class Config {

    private String apiKey;
    private int maxConnections;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return maxConnections == config.maxConnections && debugMode == config.debugMode && Objects.equals(apiKey, config.apiKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, maxConnections, debugMode);
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
