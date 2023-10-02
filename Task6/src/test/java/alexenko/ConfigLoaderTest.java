package alexenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConfigLoaderTest {

    /**
     * Тестовые данные для инициализации конфига.
     */
    private final Map<String, Object> TEST_CONFIG_MAP;
    {
        TEST_CONFIG_MAP = new HashMap<>();
        TEST_CONFIG_MAP.put("apiKey", "myApiKey");
        TEST_CONFIG_MAP.put("maxConnections", 10);
        TEST_CONFIG_MAP.put("debugMode", true);
    }

    @Test
    @DisplayName("Проверка, что созданный конфиг не null.")
    public void testLoadConfigFromMapNotNull() {
        ConfigLoader configLoader = new ConfigLoader();
        Optional<Config> configOptional = configLoader.loadConfigFromMap(TEST_CONFIG_MAP);
        Config config = null;
        if (configOptional.isPresent()){
            config = configOptional.get();
        }
        Assertions.assertNotNull(config);
    }

    @Test
    @DisplayName("Проверка, что созданный конфиг имеет правильный apiKey.")
    public void testLoadConfigFromMapHaveTrueApiKey() {
        ConfigLoader configLoader = new ConfigLoader();
        Optional<Config> configOptional = configLoader.loadConfigFromMap(TEST_CONFIG_MAP);
        Config config;
        if (configOptional.isPresent()){
            config = configOptional.get();
            Assertions.assertEquals(TEST_CONFIG_MAP.get("apiKey"),config.getApiKey());
        }else{
            Assertions.fail("Config is null.");
        }
    }

    @Test
    @DisplayName("Проверка, что созданный конфиг имеет правильный maxConnections.")
    public void testLoadConfigFromMapHaveTrueMaxConnections() {
        ConfigLoader configLoader = new ConfigLoader();
        Optional<Config> configOptional = configLoader.loadConfigFromMap(TEST_CONFIG_MAP);
        Config config;
        if (configOptional.isPresent()){
            config = configOptional.get();
            Assertions.assertEquals(TEST_CONFIG_MAP.get("maxConnections"),config.getMaxConnections());
        }else{
            Assertions.fail("Config is null.");
        }
    }

    @Test
    @DisplayName("Проверка, что созданный конфиг имеет правильный debugMode.")
    public void testLoadConfigFromMapHaveTrueDebugMode() {
        ConfigLoader configLoader = new ConfigLoader();
        Optional<Config> configOptional = configLoader.loadConfigFromMap(TEST_CONFIG_MAP);
        Config config = null;
        if (configOptional.isPresent()){
            config = configOptional.get();
            Assertions.assertEquals(TEST_CONFIG_MAP.get("debugMode"),config.isDebugMode());
        }else{
            Assertions.fail("Config is null.");
        }
    }
    @Test
    @DisplayName("Проверка, что загрузка из пустой map вернёт пустой Optional")
    public void testLoadConfigFromNullMapReturnNullOptional() {
        ConfigLoader configLoader = new ConfigLoader();
        Map<String, Object> configMap = null;
        Optional<Config> configOptional = configLoader.loadConfigFromMap(configMap);
        Assertions.assertTrue(configOptional.isEmpty());
    }
}
