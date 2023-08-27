package pozdnyakova;

import java.util.HashMap;
import java.util.Map;

public class Task6 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        ConfigLoad.loadConfigFromMap(configMap, config);
        System.out.println(config);
    }
}
