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
        return sortingByMethodInvokeOrder(methodList(clazz)).stream().map(m -> {
            try {
                return m.invoke(clazz.getDeclaredConstructor().newInstance());
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

        Arrays.stream(clazz.getMethods()).forEach(m -> {
            if (m.isAnnotationPresent(TestMethod.class)) {
                list.add(m);
            } else if (m.isAnnotationPresent(BeforeAllMethod.class)) {
                if (list.get(0) == null) {
                    list.set(0, m);
                } else throw new AnnotationException("Число аннотаций BeforeAllMethod больше 1");
            } else if (m.isAnnotationPresent(AfterAllMethod.class)) {
                if (list.get(1) == null) {
                    list.set(1, m);
                } else throw new AnnotationException("Число аннотаций AfterAllMethod больше 1.");
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

                    if (order < 1) {
                        throw new AnnotationException("Значение order в аннотации TestMethod должно быть натуральным числом");
                    }

                    return order;
                }))
                .collect(Collectors.toList());

        if (list.get(0) != null) {
            resultList.add(0, list.get(0));
        }

        if (list.get(1) != null) {
            resultList.add(list.get(1));
        }

        return resultList;
    }
}