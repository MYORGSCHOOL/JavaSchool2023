package abdullaeva;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс - загрузчик конфигурационных настроек приложения
 */
public class ConfigLoader {
    /**
     * Метод заполняет поля объекта класса {@Link Config} значениями из переданной карты с помощью Reflection API.
     * Сначала происходит проверка переданной в метод карты на пустоту. Если карта пуста,
     * значит нет конфигурационных данных для загрузки, выкидывается исключение и выводится сообщение об ошибке.
     * Затем в цикле по переданной карте с помощью Reflection API в полученные
     * из ключей карты поля класса {@Link Config} открывается доступ для заполнения
     * этих полей соответствующими значениями карты.
     * Если поле будет не найдено или доступ к полям класса не удастся получить, или в карте по нужному
     * ключу будет добавлено значение неверного типа, выкидывается соответствующее исключение
     * и выводится сообщение об ошибке.
     *
     * @param config - объект класса {@Link Config}.
     * @param map    - карта, содержащая конфигурационные настройки.
     */
    public static void loadConfigFromMap(Config config, HashMap<String, Object> map)
            throws NoSuchFieldException, IllegalAccessException {
        if (config == null || map == null) {
            throw new NullPointerException("Error: there's no data in map for configuration");
        }
        if (map.isEmpty()) {
            throw new NullPointerException("Error: there's no data in map for configuration");
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = config.getClass().getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(config, entry.getValue());
        }
    }
}
