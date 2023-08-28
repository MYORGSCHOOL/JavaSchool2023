package druzhinin;

import druzhinin.exceptions.ConfigMapIncorrectSizeException;
import druzhinin.exceptions.NoFieldCorrespondingToKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigLoaderTest {
    /**
     * Строковое представление названия поля {@code apiKey} класса {@link Config}.
     */
    private final String API_KEY_FIELD_NAME = "apiKey";

    /**
     * Строковое представление названия поля {@code maxConnections} класса {@link Config}.
     */
    private final String MAX_CONNECTIONS_FIELD_NAME = "maxConnections";

    /**
     * Строковое представление названия поля {@code debugMode} класса {@link Config}.
     */
    private final String DEBUG_MODE_FIELD_NAME = "debugMode";

    /**
     * Значение для поля {@code apiKey} класса {@link Config}.
     */
    private final String API_KEY_FIELD_VALUE = "abc123cba321";

    /**
     * Значение для поля {@code maxConnections} класса {@link Config}.
     */
    private final int MAX_CONNECTIONS_FIELD_VALUE = 10;

    /**
     * Значение для поля {@code debugMode} класса {@link Config}.
     */
    private final boolean DEBUG_MODE_FIELD_VALUE = true;

    @Test
    @DisplayName("Тест на получение исключения NullPointerException при передаче null в метод loadConfigFromMap")
    public void testNullPointerExceptionWhenGivingToLoadMethodNullMap() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ConfigLoader.loadConfigFromMap(null));
    }

    @Test
    @DisplayName("Тест на получение исключения ConfigMapIncorrectSizeException" +
            " при передаче Map с меньшим количеством элементов, чем полей в классе Config")
    public void testConfigMapIncorrectSizeExceptionWhenGivingToLoadMethodMapWithLessElementsThanFieldsInConfigClass() {
        Map<String, Object> incompleteMap = new HashMap<>();
        incompleteMap.put(API_KEY_FIELD_NAME, API_KEY_FIELD_VALUE);
        incompleteMap.put(MAX_CONNECTIONS_FIELD_NAME, MAX_CONNECTIONS_FIELD_VALUE);

        Assertions.assertThrows(ConfigMapIncorrectSizeException.class,
                () -> ConfigLoader.loadConfigFromMap(new HashMap<>()));
        Assertions.assertThrows(ConfigMapIncorrectSizeException.class,
                () -> ConfigLoader.loadConfigFromMap(incompleteMap));
    }

    @Test
    @DisplayName("Тест на получение NoFieldCorrespondingToKeyException при передаче" +
            " Map с ключом, которому не соответствует ни одного поля из класса Config")
    public void testNoFieldCorrespondingToKeyExceptionWhenGivingToLoadMethodMapWithWrongKey() {
        Map<String, Object> mapWithWrongKey = new HashMap<>();
        mapWithWrongKey.put("apiKey1", API_KEY_FIELD_VALUE);
        mapWithWrongKey.put(MAX_CONNECTIONS_FIELD_NAME, MAX_CONNECTIONS_FIELD_VALUE);
        mapWithWrongKey.put(DEBUG_MODE_FIELD_NAME, DEBUG_MODE_FIELD_VALUE);

        Assertions.assertThrows(NoFieldCorrespondingToKeyException.class,
                () -> ConfigLoader.loadConfigFromMap(mapWithWrongKey));
    }

    @Test
    @DisplayName("Тест на получение IllegalArgumentException при передаче Map с неверным типом value для ключа")
    public void testIllegalArgumentExceptionWhenGivingToLoadMethodMapWithWrongValueType() {
        Map<String, Object> mapWithWrongValue = new HashMap<>();
        mapWithWrongValue.put(API_KEY_FIELD_NAME, 55);
        mapWithWrongValue.put(MAX_CONNECTIONS_FIELD_NAME, MAX_CONNECTIONS_FIELD_VALUE);
        mapWithWrongValue.put(DEBUG_MODE_FIELD_NAME, DEBUG_MODE_FIELD_VALUE);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ConfigLoader.loadConfigFromMap(mapWithWrongValue));
    }

    @Test
    @DisplayName("Тест на успешное заполнение объекта класса" +
            " Config при передаче Map с корректными ключами и значениями")
    public void testSuccessfulLoadingConfigWhenGivingToLoadMethodMapWithCorrectKeysAndValues() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(API_KEY_FIELD_NAME, API_KEY_FIELD_VALUE);
        configMap.put(MAX_CONNECTIONS_FIELD_NAME, MAX_CONNECTIONS_FIELD_VALUE);
        configMap.put(DEBUG_MODE_FIELD_NAME, DEBUG_MODE_FIELD_VALUE);

        Config config = ConfigLoader.loadConfigFromMap(configMap);

        Assertions.assertEquals(configMap.get(API_KEY_FIELD_NAME), config.getApiKey());
        Assertions.assertEquals(configMap.get(MAX_CONNECTIONS_FIELD_NAME), config.getMaxConnections());
        Assertions.assertEquals(configMap.get(DEBUG_MODE_FIELD_NAME), config.isDebugMode());
    }
}
