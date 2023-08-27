package pozdnyakova;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Класс для загрузки значений конфигурационных настроек
 */
public class ConfigLoad {
    /**
     * Метод для заполнения объекта Config значениями из мапы с помощью Java Reflection API
     *
     * @param map    мапа с значениями для объекта
     * @param config заполняемый объект
     */
    public static void loadConfigFromMap(Map<String, Object> map, Config config) throws NoSuchFieldException, IllegalAccessException {
        if (map.isEmpty()) {
            throw new NoSuchElementException("Мапа пустая!");
        }
        if (config == null) {
            throw new NullPointerException("Объект Config - null!");
        }
        Set<String> namesField = map.keySet();
        for (var nameField : namesField) {
            try {
                Field field = config.getClass().getDeclaredField(nameField);
                field.setAccessible(true);
                field.set(config, map.get(nameField));
            } catch (NoSuchFieldException e) {
                throw new NoSuchFieldException("Неправильное название поля!");
            } catch (IllegalAccessException e) {
                throw new IllegalAccessException("Нет доступа к полю!");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Неправильный тип значения!");
            }
        }
    }
}
