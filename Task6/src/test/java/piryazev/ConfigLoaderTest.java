package piryazev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ConfigLoaderTest {

    /**
     * Объект класса {@link Config}
     */
    static Config config;
    /**
     * Мапа с хранящимися настройками для Config
     */
    static HashMap<String, Object> map;

    @BeforeAll
    @DisplayName("initialize map and config object")
    static void initializeConfigAndMap() {
        config = new Config();
        map = new HashMap<>();
    }

    @Test
    @DisplayName("checking correct values after loading values")
    void testCorrectValues() throws NoSuchFieldException, IllegalAccessException {
        map.put("apiKey", "myApiKey");
        map.put("maxConnections", 10);
        map.put("debugMode", true);

        ConfigLoader.loadConfigFromMap(config, map);

        Assertions.assertEquals("myApiKey", config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("checking corrective after loading empty values")
    void testCorrectEmptyValues() {
        Assertions.assertNull(config.getApiKey());
        Assertions.assertEquals(0, config.getMaxConnections());
        Assertions.assertFalse(config.isDebugMode());
    }

    @Test
    @DisplayName("checking that loadConfigMap throws new NoSuchFieldException")
    void testThrowsNoSuchFieldException() {
        map.put("maxConnec", 10);
        Assertions.assertThrows(NoSuchFieldException.class, () -> ConfigLoader.loadConfigFromMap(config, map));
    }

    @Test
    @DisplayName("checking that loadConfigMap throws new IllegalArgumentException")
    void testThrowsIllegalArgumentExceptionAfterSetStringToInt() {
        map.put("maxConnections", "a");
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConfigLoader.loadConfigFromMap(config, map));
    }
}
