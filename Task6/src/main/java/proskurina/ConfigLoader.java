package proskurina;

import proskurina.exceptions.ConfigFieldIsNotAccessibleException;
import proskurina.exceptions.NoSuchFieldInConfigException;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс, который заполняет {@link Config}.
 */
public class ConfigLoader {
    
    // Замена конструктора по-умолчанию.
    private ConfigLoader() {
    }
    
    /**
     * Заполняет поля {@code dest} значениями из  {@code src}.
     *
     * @param dest заполняемый экземпляр {@code Config}
     * @param src  источник
     * @throws IllegalArgumentException            если {@code dest} равен {@code null}
     *                                             или {@code src} пуст,
     *                                             а также если тип присваемого значения
     *                                             не соответствует типу поля в {@code Config}
     * @throws NoSuchFieldInConfigException        если поле не найдено в {@code Config}
     * @throws ConfigFieldIsNotAccessibleException если к полю нельзя получить доступ
     */
    public static void fromMap(Config dest, Map<? extends String, Object> src) {
        if (dest == null) {
            throw new IllegalArgumentException("dest равен null");
        }
        if (src.isEmpty()) {
            throw new IllegalArgumentException("src пуст");
        }
        
        for (var entry : src.entrySet()) {
            Field field;
            try {
                field = dest.getClass().getDeclaredField(entry.getKey());
            } catch (NoSuchFieldException e) {
                throw new NoSuchFieldInConfigException(e.getMessage());
            }
            try {
                field.setAccessible(true);
                field.set(dest, entry.getValue());
            } catch (IllegalAccessException e) {
                throw new ConfigFieldIsNotAccessibleException(e.getMessage());
            }
        }
    }
}