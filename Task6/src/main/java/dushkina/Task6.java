package dushkina;

import java.util.HashMap;
import java.util.Map;

public class Task6 {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        Config config = new Config();
        ConfigLoader.loadConfigFromMap(config, configMap);
        System.out.println(config);
    }
}
