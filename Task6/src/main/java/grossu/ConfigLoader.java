package grossu;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Класс для установки значений в классе {@link Config}
 */
public final class ConfigLoader {
    /**
     * Запрет на создание экземпляров класса
     */
    private ConfigLoader() {
    }

    /**
     * Метод для установки значений полям класса {@link Config}
     * Метод продолжает свою работу, если передаваемые параметры не null, иначе будет выброшено исключение ConfigLoadException
     * Метод продолжает свою работу, если {@param mapLoad} не пустой, иначе будет выброшено исключение ConfigLoadException
     * Проходя в цикле по каждому элементу {@param mapLoad}, метод пытается получить доступ к полю класса {@param configClass} по ключу,
     * если в catch будет поймано следующее исключение NoSuchFieldException
     * Если поле было найдено, метод попытается установить значение поля, в случае если типы данных совпадают операция пройдет успешно,
     * иначе в catch будет поймано следующее исключение IllegalArgumentException
     * В случае, если получит доступ к классу или полю не получится, в catch будет поймано следующее исключение IllegalAccessException
     *
     * @param configClass экземпляр класса, в поля которого будут устанавливаться значения
     * @param mapLoad     Map с набором значений для полей класса {@link Config}
     */
    public static void loadConfigFromMap(Config configClass, Map<String, Object> mapLoad) {
        if (configClass == null || mapLoad == null) {
            throw new ConfigLoadException("The passed value is null");
        }
        if (mapLoad.isEmpty()) {
            throw new ConfigLoadException("The passed map is empty");
        }
        for (var entry : mapLoad.entrySet()) {
            try {
                Field field = configClass.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(configClass, entry.getValue());
            } catch (NoSuchFieldException e) {
                throw new ConfigLoadException("Passed field not found");
            } catch (IllegalAccessException e) {
                throw new ConfigLoadException("Can't get access to class");
            } catch (IllegalArgumentException e) {
                throw new ConfigLoadException("The type of the field does not match the type");
            }
        }
    }

}
