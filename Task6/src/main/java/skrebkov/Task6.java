package skrebkov;

import java.util.HashMap;

public class Task6 {
    public static void main(String[] args) {
        HashMap<String, Object> configValue = new HashMap<>();
        configValue.put("apiKey", "Ёжик");
        configValue.put("maxConnections", 100);
        configValue.put("debugMode", false);

        Config config = new Config();
        ConfigLoader.setConfig(configValue, config);
    }
}
