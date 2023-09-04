package piryazev;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, содержащий метод для загрузки данных конфигурации
 */
public final class ConfigLoader {
    /**
     * Загружает данные конфигурации в поля класса {@link Config} используя рефлексию.
     *
     * @param config объект класса {@link Config}
     * @param map    мапа содержащая настройки
     * @throws NoSuchFieldException   выбрасывается при неправильном указании названия поля класса
     * @throws IllegalAccessException выбрасывается при отказе доступа к полю класса
     */
    public static void loadConfigFromMap(Config config, HashMap<String, Object> map)
            throws NoSuchFieldException, IllegalAccessException {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = config.getClass().getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(config, entry.getValue());
        }
    }
}
