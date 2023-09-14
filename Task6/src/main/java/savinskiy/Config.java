package savinskiy;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс Config
 */
public class Config {
    /**
     * ключ API
     */
    private String apiKey;
    /**
     * Максимальное кол-во соединений
     */
    private int maxConnections;
    /**
     * Режим дебага
     */
    private boolean debugMode;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * Метод позволяет загрузить конфиг из Map и применить значения к полям Config
     *
     * @param configMap словарь с данными
     */
    public void loadConfigFromMap(Map<String, Object> configMap) {
        if (configMap == null) {
            throw new IllegalArgumentException("Config can't be null");
        }

        Class<?> configClazz = getClass();

        for (var entry : configMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                Field field = configClazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(this, value);
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
