package abdullaeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ConfigLoaderTest {
    @Test
    @DisplayName("Тест для проверки корректности метода заполнения объекта Config из карты")
    public void testLoadConfigFromMap() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        Config config = new Config();
        ConfigLoader.loadConfigFromMap(config, configMap);
        Assertions.assertEquals("myApiKey", config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("Тест для проверки метода заполнения объекта Config из пустой карты")
    public void testLoadConfigFromEmptyMap() {
        HashMap<String, Object> configMap = new HashMap<>();
        Config config = new Config();
        Assertions.assertThrows(NullPointerException.class, () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Тест для проверки метода заполнения объекта Config из карты, проинициализированных null")
    public void testLoadNullConfigFromNullMap() {
        HashMap<String, Object> configMap = null;
        Config config = null;
        Assertions.assertThrows(NullPointerException.class, () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Тест для проверки метода заполнения объекта Config из некорректной карты")
    public void testLoadConfigFromWrongMap() {
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put("apiKeys", "myApiKey");
        configMap.put("maxConnection", 10);
        configMap.put("debug", true);
        configMap.put("debugMode", true);
        Config config = new Config();
        Assertions.assertThrows(NoSuchFieldException.class, () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Тест для проверки метода заполнения объекта Config из карты со значением неверного типа")
    public void testLoadConfigFromMapWithWrongValue() {
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put("maxConnections", "123");
        Config config = new Config();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ConfigLoader.loadConfigFromMap(config, configMap));
    }
}
