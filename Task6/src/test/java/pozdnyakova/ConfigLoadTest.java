package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Класс тестирования метода заполнения объкта Config данными из мапы
 */
public class ConfigLoadTest {
    @Test
    @DisplayName("Тест успешного заполнения объекта Config")
    public void testSuccessLoadingConfig() throws NoSuchFieldException, IllegalAccessException {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "ApiKey");
        configMap.put("maxConnections", 20);
        configMap.put("debugMode", false);
        ConfigLoad.loadConfigFromMap(configMap, config);
        Assertions.assertEquals(config.getApiKey(), "ApiKey");
        Assertions.assertEquals(config.getMaxConnections(), 20);
        Assertions.assertEquals(config.isDebugMode(), false);

    }

    @Test
    @DisplayName("Тест неудачного запуска метода заполнения объекта - мапа пустая")
    public void testFailLoadingMapIsEmpty() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> ConfigLoad.loadConfigFromMap(configMap, config));
    }

    @Test
    @DisplayName("Тест неудачного запуска метода заполнения объекта - объект Config null")
    public void testFailLoadingConfigIsNull() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        Assertions.assertThrows(NullPointerException.class, () -> ConfigLoad.loadConfigFromMap(configMap, null));
    }

    @Test
    @DisplayName("Тест неудачного запуска метода заполнения объекта - передано несуществующее поле")
    public void testFailLoadingDefunctField() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("defunctField", "value");
        Assertions.assertThrows(NoSuchFieldException.class, () -> ConfigLoad.loadConfigFromMap(configMap, config));
    }

    @Test
    @DisplayName("Тест неудачного запуска метода заполнения объекта - неправильный тип значения для поля")
    public void testFailLoadingWrongTypeValue() {
        Config config = new Config();
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "ApiKey");
        configMap.put("maxConnections", "string");
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConfigLoad.loadConfigFromMap(configMap, config));
    }
}
