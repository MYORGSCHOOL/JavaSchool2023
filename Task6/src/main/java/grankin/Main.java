package grankin;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);

        Config config = Config.loadConfigFromMap(configMap);

        System.out.println("Заполнение полей с помощью рефлексии:");
        System.out.println("apiKey: " + config.getApiKey());
        System.out.println("maxConnections: " + config.getMaxConnections());
        System.out.println("debugMode: " + config.isDebugMode());
    }
}