package piryazev;

import java.util.HashMap;

public class Task6 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Config config = new Config();
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        ConfigLoader.loadConfigFromMap(config, configMap);
        System.out.println(config);
    }
}
