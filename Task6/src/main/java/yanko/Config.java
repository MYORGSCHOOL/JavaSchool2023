package yanko;

/**
 * класс, хранящий параметры конфигурации
 */
public class Config {
    /**
     * ключ api
     */
    private String apiKey;
    /**
     * максимальное количество подключений
     */
    private int maxConnections;
    /**
     * возможность использования режима отладки
     */
    private boolean debugMode;

    /**
     * печать текущей конфигурации
     */
    private void print() {
        System.out.println("Config{" +
                "apiKey='" + apiKey + '\'' +
                ", maxConnections=" + maxConnections +
                ", debugMode=" + debugMode +
                '}');
    }
}
