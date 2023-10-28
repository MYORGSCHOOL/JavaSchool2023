package koroliov;

/**
 * Configuration class.
 * @author Nikita Koroliov
 * @version 1.0
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
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
