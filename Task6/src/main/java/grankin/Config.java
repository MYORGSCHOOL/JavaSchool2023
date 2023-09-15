package grankin;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс с нстройками
 */
public class Config {
    /**
     * Значение ключа
     */
    private String apiKey;

    /**
     * Макситмальное количество подключений
     */
    private int maxConnections;

    /**
     * Режим отладки
     */
    private boolean debugMode;

    /**
     * Конструктор по-умолчанию
     */
    public Config() {
        this.apiKey = "";
        this.maxConnections = 0;
        this.debugMode = false;
    }

    /**
     * Статический метод заполнения полей из значений Map с помощью рефлексии
     * @param map   - параметры для заполнения полей класса Config
     * @return      - новый объект с заполненными полями из Map
     */
    public static Config loadConfigFromMap(Map<String, Object> map) {
        Config result = new Config();

        if (map != null) {
            try {
                Field[] fields = result.getClass().getDeclaredFields();

                if (fields != null) {
                    for (int i = 0; i < fields.length; i++) {
                        String nameField = fields[i].getName();
                        if (map.containsKey(nameField)) {
                            fields[i].setAccessible(true);
                            fields[i].set(result, map.get(nameField));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}
