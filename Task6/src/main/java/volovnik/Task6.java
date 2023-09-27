package volovnik;

import volovnik.exceptions.EmptyConfigMapException;
import volovnik.exceptions.IncorrectConfigFieldException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Task6 {

    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        Config config = loadConfigFromMap(configMap);
        System.out.println(config);
    }

    /**
     * Метод заполнения конфигурации
     *
     * @param configMap набор значений
     * @return экземпляр Config
     */
    public static Config loadConfigFromMap(Map<String, Object> configMap) {
        if (configMap.isEmpty()) {
            throw new EmptyConfigMapException("Набор значений пустой");
        }
        Config config = new Config();
        try {
            for (Map.Entry<String, Object> entry : configMap.entrySet()) {
                Field field = config.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(config, entry.getValue());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IncorrectConfigFieldException("Неверно заданы параметры конфигурации");
        }
        return config;
    }
}
