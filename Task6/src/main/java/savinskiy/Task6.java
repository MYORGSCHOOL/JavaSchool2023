package savinskiy;

import java.util.Map;
import java.util.HashMap;

public class Task6 {
    public static void main(String[] args) {
        Config config = new Config();

        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);

        config.loadConfigFromMap(configMap);

        System.out.println("apiKey - " + config.getApiKey());
        System.out.println("maxConnections - " + config.getMaxConnections());
        System.out.println("debugMode - " + config.isDebugMode());
    }
}
