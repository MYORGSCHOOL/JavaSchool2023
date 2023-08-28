package druzhinin;

import druzhinin.exceptions.BadAccessTypeException;
import druzhinin.exceptions.ConfigMapIncorrectSizeException;
import druzhinin.exceptions.NoFieldCorrespondingToKeyException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для загрузки конфигурационного класса приложения {@link Config}.
 *
 * @author Дружинин Артем.
 */
public final class ConfigLoader {
    /**
     * Метод для загрузки конфигурационного класса приложения {@link Config}.
     *
     * @param configMap Конфигурационная Map, содержащая в себе имена полей и их новые значения.
     * @throws NullPointerException если переданные {@code configMap} или {@code config} равны {@code null}.
     * @throws ConfigMapIncorrectSizeException если количество ключей в переданной {@code configMap} не соответствует
     *         фактическому количеству объявленных полей класса {@link Config} или равно нулю.
     * @throws NoFieldCorrespondingToKeyException если какому-либо ключу из {@code configMap}
     *         не соответствует ни одного поля из класса {@link Config}.
     * @throws BadAccessTypeException если у одного из полей класса {@link Config} установленные
     *         права доступа не позволяют записывать в него новые значения.
     * @throws IllegalArgumentException при попытке записи в поле объекта класса {@link Config} значения другого типа.
     */
    public static Config loadConfigFromMap(Map<String, Object> configMap) {
        Config config = new Config();

        checkIfMapIsCorrect(configMap, config);

        configMap.forEach((key, value) -> {
            try {
                Field declaredField = config.getClass().getDeclaredField(key);
                declaredField.setAccessible(true);
                declaredField.set(config, value);
            } catch (NoSuchFieldException e) {
                throw new NoFieldCorrespondingToKeyException(
                        String.format("Поле с именем %s в классе %s отсутствует", key, config.getClass().getName()), e);
            } catch (IllegalAccessException e) {
                throw new BadAccessTypeException(
                        String.format("Установленные права доступа поля с именем %s в классе %s" +
                                " не позволяют произвести в него запись значения", key, config.getClass().getName()), e);
            }
        });

        return config;
    }

    /**
     * Метод для проверки правильности переданной для конфигурации Map.</br>
     *
     * @param configMap Конфигурационная Map, содержащая в себе имена полей и их новые значения.
     * @param config Объект класса конфигурации приложения.
     * @throws NullPointerException если переданные {@code configMap} или {@code config} равны {@code null}.
     * @throws ConfigMapIncorrectSizeException если количество ключей в переданной {@code configMap} не соответствует
     *         фактическому количеству объявленных полей класса {@link Config} или равно нулю.
     * @throws NoFieldCorrespondingToKeyException если какому-либо ключу из {@code configMap}
     *         не соответствует ни одного поля из класса {@link Config}.
     */
    private static void checkIfMapIsCorrect(Map<String, Object> configMap, Config config) {
        if (configMap == null || config == null) {
            throw new NullPointerException("Конфигурационная Map или объект класса Config равен null");
        }

        Field[] declaredFields = config.getClass().getDeclaredFields();
        if (configMap.size() != declaredFields.length || configMap.size() == 0) {
            throw new ConfigMapIncorrectSizeException("Количество ключей конфигурационного объекта" +
                    " Map не соответствует количеству объявленных полей в классе " + config.getClass().getName());
        }

        List<String> fieldsNames = Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
        configMap.forEach((key, value) -> {
            if (!fieldsNames.contains(key)) {
                throw new NoFieldCorrespondingToKeyException(
                        String.format("Поля, соответствующего ключу %s не найдено в классе %s",
                                key, config.getClass().getName()));
            }
        });
    }
}


