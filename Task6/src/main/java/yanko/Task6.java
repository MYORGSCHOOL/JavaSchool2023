package yanko;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Task6 {
    /**
     * метод загрузки параметров конфигурации из мапы и вызова метода вывода конфигурации на экран
     * @param config объект конфигурации
     * @param map объект мапы, хранящий параметры конфигурации
     */
    public static void loadConfigFromMap(Config config, Map<String, Object> map) {
         for (String key: map.keySet()) {
             setConfigFieldValue(config, map, key);
         }
        invokeConfigMethod(config, "print");
    }

    /**
     * метод установки значения поля для объекта конфигурации из мапы по имени поля
     * @param config объект конфигурации
     * @param map объект мапы, хранящий параметры конфигурации
     * @param fieldName название поля
     */
    public static void setConfigFieldValue(Config config, Map map, String fieldName) {
        try {
            Field apiKeyField = config.getClass().getDeclaredField(fieldName);
            apiKeyField.setAccessible(true);
            apiKeyField.set(config, map.get(fieldName));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод запуска метода класса конфигурации по имени метода
     * @param config объект конфигурации
     * @param methodName название метода
     */
    public static void invokeConfigMethod(Config config, String methodName) {
        try {
            Method printMethod = config.getClass().getDeclaredMethod(methodName);
            printMethod.setAccessible(true);
            printMethod.invoke(config);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);

        Config config = new Config();
        loadConfigFromMap(config, configMap);
    }
}
