package malakhova;

import java.util.HashMap;
import java.util.Map;

public class Task6 {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        Config config = new Config();
        System.out.println("Данные Config до изменения");
        System.out.println("Первое поле: " + config.getApiKey());
        System.out.println("Второе поле: " + config.getMaxConnections());
        System.out.println("Третье поле: " + config.isDebugMode());
        try {
            LoadConfig.loadFromMap(configMap, config);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Неправильные данные");
        }
        System.out.println("Данные Config после изменения");
        System.out.println("Первое поле: " + config.getApiKey());
        System.out.println("Второе поле: " + config.getMaxConnections());
        System.out.println("Третье поле: " + config.isDebugMode());
    }
}
