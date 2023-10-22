package dushkina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс - тестирование методов класса ConfigLoad
 */
public class ConfigLoadTest {
    @Test
    @DisplayName("Проверка корректного заполнения полей объекта Config значениями из переданной карты")
    public void testCheckCorrectnessFilling() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "DushkinaApiKey");
        configMap.put("maxConnections", 5);
        configMap.put("debugMode", true);
        Config config = new Config();
        ConfigLoader.loadConfigFromMap(config, configMap);
        Assertions.assertEquals(config.getApiKey(), "DushkinaApiKey");
        Assertions.assertEquals(config.getMaxConnections(), 5);
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("Проверка поведения при передачи config null")
    public void testTransmittingNullConfig() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "DushkinaApiKey");
        configMap.put("maxConnections", 5);
        configMap.put("debugMode", true);
        Assertions.assertThrows(ConfigLoaderException.class, () -> ConfigLoader.loadConfigFromMap(null, configMap));
    }

    @Test
    @DisplayName("Проверка поведения при передачи пустой мапы")
    public void testTransmittingEmptyMap() {
        Map<String, Object> configMap = new HashMap<>();
        Config config = new Config();
        Assertions.assertThrows(ConfigLoaderException.class, () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Проверка поведения, когда нет поля с указанным именем")
    public void testNoFieldWithSpecifiedName() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKeyKey", "DushkinaApiKey");
        Config config = new Config();
        Assertions.assertThrows(ConfigLoaderException.class, () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Проверка поведения, когда методу был передан недопустимый аргумент")
    public void testInvalidArgumentPassed() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKeyKey", new Config());
        Config config = new Config();
        Assertions.assertThrows(ConfigLoaderException.class, () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }
}