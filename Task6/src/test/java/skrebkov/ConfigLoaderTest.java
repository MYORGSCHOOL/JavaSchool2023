package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ConfigLoaderTest {

    @Test
    @DisplayName("Удачная установка конфига")
    public void testSuccessSetConfig() {
        HashMap<String, Object> configValue = new HashMap<>();
        configValue.put("apiKey", "Ёжик");
        configValue.put("maxConnections", 100);
        configValue.put("debugMode", false);

        Config config = new Config();
        ConfigLoader.setConfig(configValue, config);

        Assertions.assertEquals(100, config.getMaxConnections());
        Assertions.assertFalse(config.isDebugMode());
        Assertions.assertEquals("Ёжик", config.getApiKey());
    }

    @Test
    @DisplayName("Ошибка если не найдено поле из HashMap с конфигом")
    public void testThrowNoSuchFieldException() {
        HashMap<String, Object> configValue = new HashMap<>();
        configValue.put("apiKeyyyy", "Ёжик");
        configValue.put("apiKey", "Ёжик");
        configValue.put("maxConnections", 100);
        configValue.put("debugMode", false);

        Config config = new Config();

        Assertions.assertThrows(RuntimeException.class,
                () -> ConfigLoader.setConfig(configValue, config));
    }

    @Test
    @DisplayName("Ошибка не верный аргумент")
    public void testThrowIllegalArgumentException() {
        HashMap<String, Object> configValue = new HashMap<>();
        configValue.put("apiKey", "Ёжик");
        configValue.put("apiKey", "Ёжик");
        configValue.put("maxConnections", "100");
        configValue.put("debugMode", false);

        Config config = new Config();

        Assertions.assertThrows(RuntimeException.class,
                () -> ConfigLoader.setConfig(configValue, config));
    }

    @Test
    @DisplayName("Ошибка пустой HashMap")
    public void testNullPointerExceptionWithEmptyHashMap() {
        HashMap<String, Object> configValue = new HashMap<>();
        Config config = new Config();

        Assertions.assertThrows(NullPointerException.class,
                () -> ConfigLoader.setConfig(configValue, config));
        Assertions.assertThrows(NullPointerException.class,
                () -> ConfigLoader.setConfig(null, config));
    }

    @Test
    @DisplayName("Тестирование ошибки пустой Config")
    public void testNullPointerExceptionWithEmptyConfig() {
        HashMap<String, Object> configValue = new HashMap<>();
        configValue.put("apiKey", "Ёжик");
        configValue.put("maxConnections", 100);
        configValue.put("debugMode", false);

        Assertions.assertThrows(NullPointerException.class,
                () -> ConfigLoader.setConfig(configValue, null));
    }
}
