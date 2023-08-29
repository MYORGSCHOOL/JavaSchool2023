package tsimmer;

import java.util.HashMap;
import java.util.Map;

public class Task6 {
    public static void main(String[] args) throws NoSuchFieldException {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        LoadConfigFromMap.loadConfigFromMap(config,configMap);
        System.out.println(config);
    }
}
