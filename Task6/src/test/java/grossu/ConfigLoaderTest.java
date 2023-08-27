package grossu;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс с тестами для класса {@link ConfigLoader}
 */
public class ConfigLoaderTest {
    /**
     * Наименование поля apiKey класса {@link Config}
     */
    private final static String NAME_FIELD_API_KEY = "apiKey";
    /**
     * Наименование поля maxConnections класса {@link Config}
     */
    private final static String NAME_FIELD_MAX_CONNECTIONS = "maxConnections";
    /**
     * Наименование поля debugMode класса {@link Config}
     */
    private final static String NAME_FIELD_DEBUG_MODE = "debugMode";
    /**
     * Экземпляр класса
     */
    private static Config config;
    /**
     * Map для заполнения полей класса {@link Config}
     */
    private static Map<String, Object> mapLoad;

    /**
     * Метод для инициализации {@code config} и {@code mapLoad}
     */
    @BeforeAll
    static void init() {
        config = new Config();
        mapLoad = new HashMap<>();
    }

    @Test
    @DisplayName("Check that method loadConfigFromMap works correctly when passed config is null and map is null")
    void testGetThrowWhenConfigIsNullOrAndMapIsNull() {
        Assertions.assertThrows(ConfigLoadException.class, () -> ConfigLoader.loadConfigFromMap(null, mapLoad));
        Assertions.assertThrows(ConfigLoadException.class, () -> ConfigLoader.loadConfigFromMap(config, null));
        Assertions.assertThrows(ConfigLoadException.class, () -> ConfigLoader.loadConfigFromMap(null, null));
    }

    @Test
    @DisplayName("Check that method loadConfigFromMap works correctly when passed map is empty")
    void testGetThrowWhenTryLoadConfigWithEmptyMap() {
        Assertions.assertThrows(ConfigLoadException.class, () -> ConfigLoader.loadConfigFromMap(config, mapLoad));
    }

    @Test
    @DisplayName("Check that method loadConfigFromMap works correctly when trying to load non-exist field")
    void testGetTrowWhenTryingLoadNonExistField() {
        mapLoad.put("nonExistField", "nonExistField");
        Assertions.assertThrows(ConfigLoadException.class, () -> ConfigLoader.loadConfigFromMap(config, mapLoad));
    }

    @Test
    @DisplayName("Check that method loadConfigFromMap works correctly when trying to load data with wrong type in field")
    void testGetTrowWhenTryingLoadDataWithWrongTypeToField() {
        mapLoad.put("maxConnections", "This field has type int not string");
        Assertions.assertThrows(ConfigLoadException.class, () -> ConfigLoader.loadConfigFromMap(config, mapLoad));
    }

    @Test
    @DisplayName("Check that load data from map to config works correctly")
    void testSuccessfulLoadConfigFromMap() {
        mapLoad.put(NAME_FIELD_API_KEY, "apikey");
        mapLoad.put(NAME_FIELD_MAX_CONNECTIONS, 10);
        mapLoad.put(NAME_FIELD_DEBUG_MODE, true);
        Assertions.assertDoesNotThrow(() -> ConfigLoader.loadConfigFromMap(config, mapLoad));
        Assertions.assertEquals("apikey", config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertTrue(config.isDebugMode());
    }

    /**
     * Метод для очистки {@code config} и {@code mapLoad}
     */
    @AfterEach
    void clean() {
        config = new Config();
        mapLoad.clear();
    }

}
