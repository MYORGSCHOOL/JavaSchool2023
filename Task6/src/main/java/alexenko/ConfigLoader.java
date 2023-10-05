package alexenko;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;

/**
 * Класс загрузки конфигурации.
 */
public class ConfigLoader {

    /**
     * Метод реализует загрузку конфигурации из предоставленной config map.
     * Используя имя класса Config, создаётся экземпляр этого класса.
     * Получается список всех полей класса Config.
     * Имена полей выступают в роли ключей для словаря.
     * Полученные из словаря значения инициализируют соответствующие поля объекта Config.
     *
     * @param configMap содержит данные для инициализации конфига
     * @return Optional<Config> содержит инициализированный конфиг или null.
     */
    public Optional<Config> loadConfigFromMap(Map<String, Object> configMap) {
        if (configMap == null) {
            return Optional.empty();
        }
        Config config = null;
        try {
            Class configClass = Class.forName(Config.class.getName());
            Constructor<Config> configConstructor = configClass.getConstructor();
            config = configConstructor.newInstance();
            Field[] fields = configClass.getDeclaredFields();
            for (var field : fields) {
                String key = field.getName();
                Object value = configMap.get(key);
                field.setAccessible(true);
                field.set(config, value);
            }
            return Optional.of(config);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
            return Optional.ofNullable(config);
        }
    }
}
