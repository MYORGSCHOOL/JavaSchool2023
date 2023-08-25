package tumbaev;

import tumbaev.exception.ConfigFieldException;
import tumbaev.exception.ConfigMapSizeException;
import tumbaev.exception.NullConfigMapException;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Utility class for {@link Config} class
 */
public final class ConfigUtils {
    private ConfigUtils() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    /**
     * Creates config object from config map
     *
     * @param configMap config map, must not be null
     * @return config object, created form config map
     * @throws NullConfigMapException config map is null
     * @throws ConfigMapSizeException config map has different number of fields than the {@link Config} class
     * @throws ConfigFieldException   {@link Config} either doesn't have filed with the name provided in config map,
     *                                or this field can't be accessed,
     *                                or it has the wrong type
     * @throws NullPointerException   a key in config map is null
     */
    public static Config loadConfigFromMap(Map<String, Object> configMap) {
        checkConfigMapSize(configMap);

        Config config = new Config();
        configMap.forEach((k, v) -> {
            try {
                Field field = config.getClass().getDeclaredField(k);
                field.setAccessible(true);
                field.set(config, v);
            } catch (NoSuchFieldException e) {
                throw new ConfigFieldException(String.format("Config doesn't have field '%s'", k), e);
            } catch (IllegalAccessException e) {
                throw new ConfigFieldException(String.format("Can't access field '%s'", k), e);
            } catch (IllegalArgumentException e) {
                throw new ConfigFieldException(String.format("Field '%s' has the wrong type", k), e);
            }
        });
        return config;
    }

    /**
     * Checks if config map has the same number of fields as the {@link Config} class
     *
     * @param configMap config map, must not be null
     * @throws NullConfigMapException if config map is null
     * @throws ConfigMapSizeException if config map has different number of fields than the {@link Config} class
     */
    private static void checkConfigMapSize(Map<String, Object> configMap) {
        if (configMap == null) {
            throw new NullConfigMapException("Config map is null");
        }

        if (Config.class.getDeclaredFields().length != configMap.size()) {
            throw new ConfigMapSizeException(
                    String.format("Config has %d fields, but provided config map has %d fields",
                            Config.class.getDeclaredFields().length, configMap.size())
            );
        }
    }
}
