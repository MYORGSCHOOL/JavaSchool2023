package tsimmer;

import java.lang.reflect.Field;
import java.util.Map;

public class LoadConfigFromMap {
    /**
     * Метод, заполняющий поля объекта Config значениями из переданной карты
     *
     * @param config    заполняемый объект
     * @param configMap значения для объекта
     * @throws NoSuchFieldException когда поле указано неверно
     */
    public static void loadConfigFromMap(Config config, Map<String, Object> configMap) throws NoSuchFieldException {

        for (var entry : configMap.entrySet()) {
            try {
                Field field = config.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(config, entry.getValue());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
