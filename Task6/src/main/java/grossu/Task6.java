package grossu;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для взаимодействия с {@link ConfigLoader}
 */
public class Task6 {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", "1");
        configMap.put(null, true);
        Config config = new Config();
        ConfigLoader.loadConfigFromMap(config, configMap);
        System.out.println(config);
    }

}
