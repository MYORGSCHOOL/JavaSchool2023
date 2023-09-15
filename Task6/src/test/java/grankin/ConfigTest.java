package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigTest {

    @Test
    @DisplayName("Проверка изменения закрытых полей с помощью рефлексии")
    public void testCloseField() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("apiKey", "myApiKey");
        configMap.put("maxConnections", 10);
        configMap.put("debugMode", true);
        Config config = Config.loadConfigFromMap(configMap);
        Assertions.assertEquals("myApiKey",config.getApiKey());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertEquals(true, config.isDebugMode());
    }

    @Test
    @DisplayName("Передача пустого объекта")
    public void testCloseNullField() {
        Map<String, Object> configMap = null;
        Config config = Config.loadConfigFromMap(configMap);
        Assertions.assertEquals("",config.getApiKey());
        Assertions.assertEquals(0, config.getMaxConnections());
        Assertions.assertEquals(false, config.isDebugMode());
    }
}
