package karmanchikova;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс для загрузки данных из Map
 */
public final class ConfigLoad {
    /**
     * Метод для заполнения Config из мапы
     *
     * @param config класс для заполнения значений
     * @param map    мапа значений для Config
     */
    public static void loadConfigFromMap(Config config, Map<String, Object> map) {
        if (map == null || config == null) {
            throw new RuntimeException("Переданы пустые значения");
        }
        if (map.isEmpty()) {
            throw new RuntimeException("Передана пустая мапа");
        }
        for (var entry : map.entrySet()) {
            try {
                Field field = config.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(config, entry.getValue());
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Поле не найдено");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Доступ к классу ограничен");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Тип пол не соответвует");
            }
        }
    }
}
