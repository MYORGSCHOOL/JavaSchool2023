package karamanchikova;

import babadzhanov.DataLoader;
import karmanchikova.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigLoadTest {
    Config config = new Config();
    Map<String, Object> configMap = new HashMap<>();

    @Test
    @DisplayName("Проверка на успешное заполнение мапы")
    void testSuccessLoadFromMap() {
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        DataLoader.loadConfigFromMap(config, configMap);
        Assertions.assertEquals("myApiKey", config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("Проверка на заполнение некорректными настройками")
    void testCheckFillingIncorrectSettings() {
        configMap.put("debugMode", 10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> DataLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Проверка на заполнение несуществующими данными")
    void testCheckFillingNonExistentData() {
        configMap.put("non-existent data", 10);
        Assertions.assertThrows(RuntimeException.class, () -> DataLoader.loadConfigFromMap(config, configMap));
    }
}