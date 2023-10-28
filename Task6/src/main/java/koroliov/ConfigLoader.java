package koroliov;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A class for adding information to {@link Config}.
 * @author Nikita Koroliov
 * @version 1.0
 */
public class ConfigLoader {

    /**
     * Implementation of the {@code loadConfigFromMap} method, 
     * which will populate the fields of the {@link Config} object
     * with values from the passed map {@code (Map)} using the Java Reflection API.
     * @param map A completed map of elements.
     * @param config  Implemented {@link Config} class.
     */
    public void loadConfigFromMap(HashMap<String, Object> map, Config config) {
        // Check map and config for null and fullness.
        if (map == null || config == null) {
            throw new NoSuchElementException("Map or Config is Empty!");
        }

        if (map.isEmpty()) {
            throw new NoSuchElementException("Map is Empty!");
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                throw new NoSuchElementException("Null detected in the map!");
            }
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            try {
                Field field = config.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(config, entry.getValue());
            } catch (NoSuchFieldException e) {
                System.out.println(e);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
        }
    }
}
