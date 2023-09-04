package savinskiy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigTest {

    private static Config config;

    @BeforeAll
    public static void init() {
        config = new Config();
    }

    @Test
    public void testLoadConfigFromMap() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);

        config.loadConfigFromMap(configMap);

        assertEquals("myApiKey", config.getApiKey());
        assertEquals(10, config.getMaxConnections());
        assertTrue(config.isDebugMode());
    }

    @Test
    public void testLoadConfigFromMapException() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("debugMode", 1.1);

        IllegalArgumentException exception = null;
        try {
            config.loadConfigFromMap(configMap);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(exception.getClass(), IllegalArgumentException.class);
    }

    @Test
    public void testLoadConfigFromMapFieldException() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("field", "val");

        RuntimeException exception = null;
        try {
            config.loadConfigFromMap(configMap);
        } catch (RuntimeException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals(exception.getClass(), RuntimeException.class);
    }

    @Test
    public void testLoadConfigFromMapNPE() {
        Config config1 = new Config();
        Map<String, Object> configMap = null;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> config1.loadConfigFromMap(configMap)
        );

        assertEquals("Config can't be null", exception.getMessage());
    }
}
