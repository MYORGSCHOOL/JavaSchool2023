package druzhinin;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для использования Config и ConfigLoader.
 *
 * @author Дружинин Артем.
 */
public class Task6 {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "abc123cba321");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);

        Config config = ConfigLoader.loadConfigFromMap(configMap);

        System.out.println("Конфиг:\n" + config);
    }
}
