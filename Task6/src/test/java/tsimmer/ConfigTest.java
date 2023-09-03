package tsimmer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigTest {
    Config config = new Config();
    Map<String, Object> configMap = new HashMap<>();

    @Test
    @DisplayName("Тест успешного выполнения метода loadConfigFromMap")
    void testSuccessLoadConfigFromMap() throws NoSuchFieldException {
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        LoadConfigFromMap.loadConfigFromMap(config, configMap);
        Assertions.assertEquals("myApiKey", config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertTrue(config.isDebugMode());

    }

    @Test
    @DisplayName("Тест отлавливающий ошибку IllegalArgumentException")
    void testLoadConfigFromMapIllegalArgumentException() {
        configMap.put("maxConnections", "value");
        Assertions.assertThrows(IllegalArgumentException.class, () -> LoadConfigFromMap.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Тест отлавливающий ошибку NoSuchFieldException")
    void testLoadConfigFromMapNoSuchFieldException() {
        configMap.put("fail", 1);
        Assertions.assertThrows(NoSuchFieldException.class, () -> LoadConfigFromMap.loadConfigFromMap(config, configMap));
    }
}
