package babadzhanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DataLoaderTest {

    Config config = new Config();
    Map<String, Object> configMap = new HashMap<>();

    @Test
    @DisplayName("Проверка успешного заполнения конфигурационных настроек")
    void testSuccessLoadConfigFromMap() {
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        DataLoader.loadConfigFromMap(config, configMap);
        Assertions.assertEquals("myApiKey", config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertTrue(config.isDebugMode());
    }

    @Test
    @DisplayName("Проверка выброса исключения при заполнение настроек, некорректными значениями")
    void checkIllegalArgumentExceptionWhenLoadConfigFromMap() {
        configMap.put("debugMode", "test");
        Assertions.assertThrows(IllegalArgumentException.class, () -> DataLoader.loadConfigFromMap(config, configMap));
    }

    @Test
    @DisplayName("Проверка выброса исклчюения при заполнение настроек, несуществущими полями")
    void checkRuntimeExceptionWhenLoadConfigFromMap() {
        configMap.put("test", false);
        Assertions.assertThrows(RuntimeException.class, () -> DataLoader.loadConfigFromMap(config, configMap));
    }
}