package proskurina;

import java.util.HashMap;
import java.util.Map;
public class Task6 {
    
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        
        var config = new Config();
        ConfigLoader.fromMap(config,configMap);
        System.out.println(config);
    }
}