package skrebkov;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ConfigLoader {
    /**
     * Метод для установки значения Config при помощи рефлексии
     *
     * @param configValues
     * @param config
     */
    public static void setConfig(HashMap<String, Object> configValues, Config config) {
        if (configValues != null && configValues.isEmpty()) {
            throw new NullPointerException("Empty config map");
        }

        if (config == null) {
            throw new NullPointerException("Empty config");
        }

        configValues.forEach((fieldName, value) -> {
            try {
                Field field = config.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(config, value);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Class does`t hava field");
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new RuntimeException("Failed to change value", e);
            }
        });
    }
}
