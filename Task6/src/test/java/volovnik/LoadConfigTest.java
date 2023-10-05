package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import volovnik.exceptions.EmptyConfigMapException;
import volovnik.exceptions.IncorrectConfigFieldException;

import java.util.HashMap;
import java.util.Map;

public class LoadConfigTest {

    @Test
    @DisplayName("Тест на заполнение поля значением")
    public void testSuccessSetValueForField() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "test");
        Config config = Task6.loadConfigFromMap(configMap);
        Assertions.assertEquals("test", config.getApiKey());
    }

    @Test
    @DisplayName("Тест на выброс исключения, если поле не существует")
    public void testThrowsExceptionWhenFieldIsIncorrect() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey1", "test");
        Assertions.assertThrows(IncorrectConfigFieldException.class, () -> Task6.loadConfigFromMap(configMap));
    }

    @Test
    @DisplayName("Тест на выброс исключения, если передана пустая конфигурация")
    public void testThrowsExceptionWhenMapIsEmpty() {
        Map<String, Object> configMap = new HashMap<>();
        Assertions.assertThrows(EmptyConfigMapException.class, () -> Task6.loadConfigFromMap(configMap));
    }
}
