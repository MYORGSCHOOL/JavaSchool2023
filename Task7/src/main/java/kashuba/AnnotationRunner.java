package kashuba;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Класс для фиксации аннотации над методами какого-либо класса
 */
public final class AnnotationRunner {

    private static final int BEFORE_ALL_METHOD_INDEX = 0;
    private static final int AFTER_ALL_METHOD_INDEX = 1;

    private AnnotationRunner() {
    }

    /**
     * Функция, анализирующая аннотации над методами и запускающая методы по определенным правилам
     *
     * @param clazz класс, который содержит аннотации @TestMethod, @BeforeAllMethod или @AfterAllMethod
     * @return список объектов, хранящий порядок выполнения методов
     * @see #methodList(Class) доп. функция, вытаскивающая методы с нужными аннотациями
     * @see #sortingByMethodInvokeOrder(List) доп. функция, сортирующая методы в порядке приоритета
     */
    public static List<Object> runningTestMethods(Class<?> clazz) {
        return sortingByMethodInvokeOrder(methodList(clazz)).stream().map(method -> {
            try {
                return method.invoke(clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    /**
     * Функция, читающая все методы переданного в качестве параметра класса и находящая необходимые аннотации
     *
     * @param clazz класс, который содержит аннотации @TestMethod, @BeforeAllMethod или @AfterAllMethod
     * @return список объектов Method, содержащих одну из аннотаций
     */
    private static List<Method> methodList(Class<?> clazz) {
        List<Method> list = new ArrayList<>(Arrays.asList(null, null));

        Arrays.stream(clazz.getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(TestMethod.class)) {
                list.add(method);
            } else if (method.isAnnotationPresent(BeforeAllMethod.class)) {
                if (list.get(BEFORE_ALL_METHOD_INDEX) == null) {
                    list.set(BEFORE_ALL_METHOD_INDEX, method);
                } else {
                    throw new AnnotationException("Число аннотаций BeforeAllMethod больше 1");
                }
            } else if (method.isAnnotationPresent(AfterAllMethod.class)) {
                if (list.get(AFTER_ALL_METHOD_INDEX) == null) {
                    list.set(AFTER_ALL_METHOD_INDEX, method);
                } else {
                    throw new AnnotationException("Число аннотаций AfterAllMethod больше 1.");
                }
            }
        });

        if (list.size() == 2) {
            throw new AnnotationException("В классе нет аннотаций TestMethod");
        }

        return list;
    }

    /**
     * Функция для выстраивания вызовов методов по приоритету
     *
     * @param list список, содержащий объекты Method, которые имеют аннотации @TestMethod, @BeforeAllMethod или @AfterAllMethod
     * @return список объектов Method, расположенных в порядке необходимого вызова
     */
    private static List<Method> sortingByMethodInvokeOrder(List<Method> list) {
        List<Method> resultList = list.subList(2, list.size()).stream()
                .sorted(Comparator.comparingInt(m -> {
                    int order = m.getAnnotation(TestMethod.class).order();

                    if (order < AFTER_ALL_METHOD_INDEX) {
                        throw new AnnotationException("Значение order в аннотации TestMethod должно быть натуральным числом");
                    }

                    return order;
                }))
                .collect(Collectors.toList());

        if (list.get(BEFORE_ALL_METHOD_INDEX) != null) {
            resultList.add(BEFORE_ALL_METHOD_INDEX, list.get(BEFORE_ALL_METHOD_INDEX));
        }

        if (list.get(AFTER_ALL_METHOD_INDEX) != null) {
            resultList.add(list.get(AFTER_ALL_METHOD_INDEX));
        }

        return resultList;
    }
}