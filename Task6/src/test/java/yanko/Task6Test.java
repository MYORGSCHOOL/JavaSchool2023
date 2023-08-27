package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Task6Test {
    @Test
    @DisplayName("установка значения конфигурации для существующего поля")
    public void testSuccessSetFieldValueForExistingField() {
        Config config = new Config();
        Map<String, Object> map = new HashMap<>();
        map.put("apiKey", "correctKey");
        Task6.setConfigFieldValue(config, map, "apiKey");
        try {
            Field apiKeyField = config.getClass().getDeclaredField("apiKey");
            apiKeyField.setAccessible(true);
            Assertions.assertEquals("correctKey", apiKeyField.get(config));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("установка значения конфигурации для несуществующего поля")
    public void testFailSetFieldValueForAbsentField() {
        Config config = new Config();
        Map<String, Object> map = new HashMap<>();
        map.put("apiKey1", "incorrectKey");
        Assertions.assertThrows(RuntimeException.class, () -> Task6.setConfigFieldValue(config, map, "apiKey1"));
    }

    @Test
    @DisplayName("вызов несуществующего метода конфигурации")
    public void testFailInvokeAbsentMethod() {
        Config config = new Config();
        Assertions.assertThrows(RuntimeException.class, () -> Task6.invokeConfigMethod(config, "print1"));
    }
}
