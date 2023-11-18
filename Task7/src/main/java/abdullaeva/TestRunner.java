package abdullaeva;

import abdullaeva.annotations.AfterAllMethod;
import abdullaeva.annotations.BeforeAllMethod;
import abdullaeva.annotations.TestMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, запускающий выполнение тестирования
 */
public class TestRunner {
    /**
     * Метод для запуска выполнения тест-методов с кастомными аннотациями BeforeAllMethod, TestMethod и AfterAllMethod.
     * Создаются объект тестируемого класса и массив для хранения отсортированных по аннотациям
     * тест-методов этого класса. Затем происходит вызов тестовых методов
     * с помощью метода invoke() в порядке, зависимом от аннотаций, которыми помечены эти методы,
     * с учетом приоритета вызова тестов-методов с аннотацией TestMethod - order.
     *
     * Сначала в тестируемом классе находится и вызывается метод, помеченный аннотацией BeforeAllMethod.
     * Если методов в классе с данной аннотацией больше, чем один,
     * выкидывается исключение AnnotationUniquenessException. Затем находятся и сортируются по приоритету вызова order
     * все методы, помеченные аннотацией TestMethod. Если приоритеты методов совпадают, они вызываются
     * в зависимости от сортировки методов в массиве. Последним в тестируемом классе находится и вызывается метод,
     * помеченный аннотацией AfterAllMethod. Если методов в классе с данной аннотацией больше, чем один,
     * выкидывается исключение AnnotationUniquenessException.
     *
     * @param className - имя вызывающего тест-методы класса.
     * @throws NoSuchMethodException - исключение выбрасывается для класса, в котором нет методов, помеченных
     *                               аннотацией TestMethod.
     */
    public static void start(Class<?> className)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object objectClass = className.getConstructor().newInstance();
        Method[] methods = className.getDeclaredMethods();

        List<Method> methodWithBeforeAll = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(BeforeAllMethod.class))
                .collect(Collectors.toList());
        if (methodWithBeforeAll.size() > 1) {
            throw new AnnotationUniquenessException("Error: more than one method with @BeforeAllMethod found");
        }
        if (!methodWithBeforeAll.isEmpty()) {
            methodWithBeforeAll.get(0).setAccessible(true);
            methodWithBeforeAll.get(0).invoke(objectClass);
        }

        List<Method> methodsWithTestMethodAndOrder = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(TestMethod.class))
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(TestMethod.class).order()))
                .collect(Collectors.toList());
        if (methodsWithTestMethodAndOrder.isEmpty()) {
            throw new NoSuchMethodException("Error: there must be at least one method with @TestMethod");
        }
        for (Method method : methodsWithTestMethodAndOrder) {
            method.setAccessible(true);
            method.invoke(objectClass);
        }

        List<Method> methodWithAfterAll = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AfterAllMethod.class))
                .collect(Collectors.toList());
        if (methodWithAfterAll.size() > 1) {
            throw new AnnotationUniquenessException("Error: more than one method with @AfterAllMethod found");
        }
        if (!methodWithAfterAll.isEmpty()) {
            methodWithAfterAll.get(0).setAccessible(true);
            methodWithAfterAll.get(0).invoke(objectClass);
        }
    }
}
