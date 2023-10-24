package koroliov;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConfigLoaderTest {
    private HashMap<String, Object> configMap;
    private Config config;

    @BeforeEach
    @DisplayName("Method of adding items.")
    private void fillData() {
        this.configMap = new HashMap<>();
        this.config = new Config();

        configMap.put("apiKey", "myApiKey"); 
        configMap.put("maxConnections", 10); 
        configMap.put("debugMode", true);

        ConfigLoader cl = new ConfigLoader();
        cl.loadConfigFromMap(configMap, config);
    }

    @Test
    @DisplayName("Verify that information has been added.")
    void testLoadConfigFromMap() {
        Assertions.assertTrue(config.isDebugMode());
        Assertions.assertEquals(10, config.getMaxConnections());
        Assertions.assertEquals("myApiKey", config.getApiKey());
    }

}
