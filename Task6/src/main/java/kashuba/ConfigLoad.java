package kashuba;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс для установки значений в классе Config
 */
public final class ConfigLoad {

    /**
     * Метод для заполнения Config значениями мапы
     *
     * @param map    мапа со значениями для класса Config
     * @param config класс в который будут заполняться значения
     */
    public static void loadConfigFromMap(Map<String, Object> map, Config config) {
        if (map != null && map.isEmpty()) {
            throw new ConfigLoadException("Мапа пустая");
        }
        if (config == null || map == null) {
            throw new ConfigLoadException("Config - null or map - null");
        }
        for (var entry : map.entrySet()) {
            try {
                Field field = config.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(config, entry.getValue());
            } catch (NoSuchFieldException e) {
                throw new ConfigLoadException("Поле не найдено");
            } catch (IllegalAccessException e) {
                throw new ConfigLoadException("Нет доступа к полю");
            } catch (IllegalArgumentException e) {
                throw new ConfigLoadException("Тип поля не соответствует типу значения");
            }
        }
    }
}
