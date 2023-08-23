package tumbaev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tumbaev.exception.ConfigFieldException;
import tumbaev.exception.ConfigMapSizeException;
import tumbaev.exception.NullConfigMapException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigUtilsTest {

    @Test
    @DisplayName("Load config map with correct fields should work fine")
    void testLoadConfigMapWithCorrectFieldsShouldWorkFine() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);

        Config config = ConfigUtils.loadConfigFromMap(configMap);

        assertEquals(configMap.get("apiKey"), config.getApiKey());
        assertEquals(configMap.get("maxConnections"), config.getMaxConnections());
        assertEquals(configMap.get("debugMode"), config.isDebugMode());
    }

    @Test
    @DisplayName("Should throw exception if config map is null")
    void testShouldThrowExceptionIfConfigMapIsNull() {
        assertThrows(NullConfigMapException.class, () -> ConfigUtils.loadConfigFromMap(null));
    }

    @Test
    @DisplayName("Should throw exception if config map has different number of fields than Config")
    void testShouldThrowExceptionIfConfigMapHasDifferentNumberOfFieldsThanConfig() {
        Map<String, Object> configMap = new HashMap<>();
        assertThrows(ConfigMapSizeException.class, () -> ConfigUtils.loadConfigFromMap(configMap));
    }

    @Test
    @DisplayName("Should throw exception if Config doesn't have field with the name provided in config map")
    void testShouldThrowExceptionIfConfigDoesntHaveFieldWithNameProvidedInConfigMap() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("UnknownField", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        assertThrows(ConfigFieldException.class, () -> ConfigUtils.loadConfigFromMap(configMap));
    }

    @Test
    @DisplayName("Should throw exception if field in config map has the wrong type")
    void testShouldThrowExceptionIfFieldInConfigMapHasWrongType() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", 10); //apiKey is String
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        assertThrows(ConfigFieldException.class, () -> ConfigUtils.loadConfigFromMap(configMap));
    }

    @Test
    @DisplayName("Should throw exception if a key in config map is null")
    void testShouldThrowExceptionIfAKeyInConfigMapIsNull() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(null, "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        assertThrows(NullPointerException.class, () -> ConfigUtils.loadConfigFromMap(configMap));
    }
}
