package malakhova;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Класс, содержащий статический метод для добавления данных в объект Config
 */
public class LoadConfig {
    /**
     * Метод,заполняющий объект Config данными из передаваемой Map
     *
     * @param configMap - карта Map с данными, которыми нужно заполнить объект
     * @param config    - объект Config, который заполняется данными
     */
    public static void loadFromMap(Map<String, Object> configMap, Config config) throws NoSuchFieldException, IllegalAccessException {
        if (configMap.isEmpty() || config == null) {
            throw new IllegalArgumentException();
        }
        Set<String> mapKeyConfig = configMap.keySet();
        Object arrayKey[] = mapKeyConfig.toArray();
        for (Object array : arrayKey) {
            Field field = config.getClass().getDeclaredField(array.toString());
            field.setAccessible(true);
            field.set(config, configMap.get(array));
        }
    }
}
