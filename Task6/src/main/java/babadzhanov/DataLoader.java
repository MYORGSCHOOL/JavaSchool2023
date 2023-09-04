package babadzhanov;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Класс для заполнения данных.
 */
public class DataLoader {

    /**
     * Метод для заполнения объекта данными из мапы.
     * Тип значения в мапе, должен соответствовать типу поля объекта.
     *
     * @param object - объект, который необходимо заполнить
     * @param map    - Мапа с ключами и значениями
     * @param <K>    - Ключ неизвестного типа
     * @param <V>    - Значение неизвестного типа
     */
    public static <K, V> void loadConfigFromMap(Object object, Map<K, V> map) {
        Set<K> keySet = map.keySet();
        for (K k : keySet) {
            try {
                Field field = object.getClass().getDeclaredField(k.toString());
                field.setAccessible(true);
                field.set(object, map.get(k));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(k.toString());
            }
        }
    }
}
