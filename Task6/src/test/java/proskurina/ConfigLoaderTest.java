package proskurina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proskurina.exceptions.NoSuchFieldInConfigException;

import java.util.HashMap;
import java.util.Map;


/**
 * Класс, тестирующий {@link ConfigLoader}.
 */
class ConfigLoaderTest {
    private Config config;
    private Map<String, Object> configMap;
    // Названия полей в Config
    private static final String API_KEY = "apiKey";
    private static final String DEBUG_MODE = "debugMode";
    private static final String MAX_CONNECTIONS = "maxConnections";
    
    @BeforeEach
    void setUp() {
        this.config = new Config();
        this.configMap = new HashMap<>();
    }
    
    @Test
    @DisplayName("Тестируется IllegalArgumentException когда config равен null")
    void testIllegalArgumentExceptionOnNullConfig() {
        fillConfigMap();
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConfigLoader.fromMap(null, configMap));
    }
    
    @Test
    @DisplayName("Тестируется IllegalArgumentException когда configMap пуст")
    void testIllegalArgumentExceptionOnEmptyMap() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConfigLoader.fromMap(config, configMap));
    }
    
    @Test
    @DisplayName("Тестируется NoSuchFieldInConfigException при доступе к несуществующему полю")
    void testNoSuchFieldInConfigException() {
        configMap.put("no such field", new Object());
        Assertions.assertThrows(NoSuchFieldInConfigException.class, () -> ConfigLoader.fromMap(config, configMap));
    }
    
    @Test
    @DisplayName("Тестируется IllegalArgumentException при присваивании значения другого типа")
    void testIllegalArgumentExceptionOnWrongValueType() {
        configMap.put(DEBUG_MODE, new Object());
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConfigLoader.fromMap(config, configMap));
    }
    
    @Test
    @DisplayName("Тестируется корректность значений присвоенных экземпляру Config")
    void testCorrectConfigLoaderFromMap() {
        fillConfigMap();
        ConfigLoader.fromMap(config, configMap);
        Assertions.assertAll(
                () -> Assertions.assertEquals(configMap.get(API_KEY), config.getApiKey()),
                () -> Assertions.assertEquals(configMap.get(DEBUG_MODE), config.isDebugMode()),
                () -> Assertions.assertEquals(configMap.get(MAX_CONNECTIONS), config.getMaxConnections())
        );
    }
    
    /**
     * Заполняет {@code configMap}.
     */
    private void fillConfigMap() {
        configMap.put(API_KEY, API_KEY);
        configMap.put(DEBUG_MODE, true);
        configMap.put(MAX_CONNECTIONS, 0);
    }
}