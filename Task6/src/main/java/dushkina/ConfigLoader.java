package dushkina;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс, заполняющий поля объекта Config значениями
 */
public class ConfigLoader {
    /**
     * Метод, заполняющий поля объекта Config значениями из переданной карты
     * В процессе циклического прохода по каждому элементу,
     * метод стремится получить доступ к полю класса config, используя ключ.
     * Если поле найдено, метод будет пытаться присвоить значение этому полю.
     * Операция будет успешной, если типы данных совпадают.
     *
     * @param config экземпляр класса Config, в поля которого будут устанавливаться значения
     * @param map    переданная карта со значениями для полей класса Config
     * @throws ConfigLoaderException - если передаваемые параметры null, если map пусой,
     * если в catch будет пойманы следующие исключения NoSuchFieldException,IllegalAccessException,
     * IllegalArgumentException
     */
    public static void loadConfigFromMap(Config config, Map<String, Object> map) {
        if (config == null || map == null) {
            throw new ConfigLoaderException("Переданое значение (config или map) пустое");
        }
        if (map.isEmpty()) {
            throw new ConfigLoaderException("Map пустая");
        }
        try {
            for (Map.Entry<String, Object> mapDop : map.entrySet()) {
                Field field = config.getClass().getDeclaredField(mapDop.getKey());
                field.setAccessible(true);
                field.set(config, mapDop.getValue());
            }
        } catch (NoSuchFieldException e) {
            throw new ConfigLoaderException("У класса нет поля с указанным именем");
        } catch (IllegalAccessException e) {
            throw new ConfigLoaderException("Выполняемый в данный момент метод не имеет доступа к определению указанного поля");
        } catch (IllegalArgumentException e) {
            throw new ConfigLoaderException("Методу был передан недопустимый аргумен");
        }
    }
}
