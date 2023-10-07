package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigTest {
    @Test
    @DisplayName("Проверка изменения данных поля apiKey")
    public void testSuccessLoadApiKeyToConfig() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "ChangedApiKey");
        Config config = new Config();
        LoadConfig.loadFromMap(configMap, config);
        Assertions.assertEquals(config.getApiKey(), configMap.get("apiKey"));
    }

    @Test
    @DisplayName("Проверка изменения данных поля maxConnections")
    public void testSuccessLoadMaxConnectionsToConfig() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("maxConnections", 5);
        Config config = new Config();
        LoadConfig.loadFromMap(configMap, config);
        Assertions.assertEquals(config.getMaxConnections(), configMap.get("maxConnections"));
    }

    @Test
    @DisplayName("Проверка изменения данных поля debugMode")
    public void testSuccessLoadDebugModeToConfig() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("debugMode", true);
        Config config = new Config();
        LoadConfig.loadFromMap(configMap, config);
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("Проверка что в loadFromMap не передаются пустые данные")
    public void testEmptyConfigOrConfigMap() {
        Map<String, Object> configMap = new HashMap<>();
        Config config = new Config();
        Assertions.assertThrows(IllegalArgumentException.class, () -> LoadConfig.loadFromMap(configMap, config));
        Assertions.assertThrows(IllegalArgumentException.class, () -> LoadConfig.loadFromMap(configMap, null));
    }
}
