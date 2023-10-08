package karmanchikova;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для взаимодейсвия с ConfigLoad
 */
public class Task6 {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        Config config = new Config();
        ConfigLoad.loadConfigFromMap(config, configMap);
        System.out.println(config);
    }
}