package grossu;

/**
 * Класс с конфигурационными настройками приложения
 */
public class Config {
    /**
     * Api ключ
     */
    private String apiKey;
    /**
     * Максимальное количество соединений
     */
    private int maxConnections;
    /**
     * Режим отладки
     */
    private boolean debugMode;

    public String getApiKey() {
        return apiKey;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Метод для вывода информации о классе на консоль
     *
     * @return строка с полями класса и их значениями
     */
    @Override
    public String toString() {
        return "Config{" +
                "apiKey='" + apiKey + '\'' +
                ", maxConnections=" + maxConnections +
                ", debugMode=" + debugMode +
                '}';
    }
}
