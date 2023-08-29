package kashuba;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigLoadTest {
    @Test
    @DisplayName("Проверка корректности заполнения Config")
    public void testCheckingTheCorrectnessOfFillingInConfig() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        ConfigLoad.loadConfigFromMap(configMap, config);
        Assertions.assertEquals(config.getApiKey(), "myApiKey");
        Assertions.assertEquals(config.getMaxConnections(), 10);
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("Проверка при пустой мапе")
    public void testCheckingForMapEmptiness() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        Assertions.assertThrows(ConfigLoadException.class,
                () -> ConfigLoad.loadConfigFromMap(configMap, config));
    }

    @Test
    @DisplayName("Проверка при Config null")
    public void testCheckingWithConfigNull() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "incorrectKey");
        Assertions.assertThrows(ConfigLoadException.class,
                () -> ConfigLoad.loadConfigFromMap(configMap, null));

    }

    @Test
    @DisplayName("Проверка при неправильном типе значении поля")
    public void testCheckingForAnIncorrectFieldTypeValue() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", new Object());
        Assertions.assertThrows(ConfigLoadException.class,
                () -> ConfigLoad.loadConfigFromMap(configMap, config));

    }

    @Test
    @DisplayName("Проверка при передаче несуществующего поля")
    public void testCheckingWhenPassingANonexistentField() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("field", new Object());
        Assertions.assertThrows(ConfigLoadException.class,
                () -> ConfigLoad.loadConfigFromMap(configMap, config));
    }
}
