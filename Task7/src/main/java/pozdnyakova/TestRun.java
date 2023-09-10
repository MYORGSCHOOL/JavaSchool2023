package pozdnyakova;

import pozdnyakova.annotations.AfterAllMethod;
import pozdnyakova.annotations.BeforeAllMethod;
import pozdnyakova.annotations.TestMethod;
import pozdnyakova.exception.ConflictingAnnotationsException;
import pozdnyakova.exception.InvalidNumberMethodsException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;

import java.util.stream.Collectors;

/**
 * Класс для запуска выполнения тестов
 */
public class TestRun {
    private TestRun() {

    }

    /**
     * Метод для выполнения тестов
     *
     * @param testClass класс с методами-тестами
     */
    public static void start(Class testClass) throws ConflictingAnnotationsException, InvalidNumberMethodsException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object objectTest = testClass.getConstructor().newInstance();
        Method[] methods = testClass.getMethods();
        List<Method> beforeAllMethod = getBeforeAllAnnotated(methods);
        List<Method> testMethod = getTestAnnotated(methods);
        List<Method> afterAllMethod = getAfterAllAnnotated(methods);
        if (testMethod.isEmpty()) {
            throw new InvalidNumberMethodsException("Должен быть хотя бы один метод с аннотацией @TestMethod");
        }
        if (beforeAllMethod.size() > 1) {
            throw new InvalidNumberMethodsException("Метод с аннотацией @BeforeAllMethod должен быть один!");
        } else if (!beforeAllMethod.isEmpty()) {
            beforeAllMethod.get(0).setAccessible(true);
            beforeAllMethod.get(0).invoke(objectTest);
        }
        for (Method method : testMethod) {
            method.setAccessible(true);
            method.invoke(objectTest);
        }
        if (afterAllMethod.size() > 1) {
            throw new InvalidNumberMethodsException("Метод с аннотацией @AfterAllMethod должен быть один!");
        } else if (!afterAllMethod.isEmpty()) {
            afterAllMethod.get(0).setAccessible(true);
            afterAllMethod.get(0).invoke(objectTest);
        }

    }

    /**
     * Метод для получения списка методов с аннотацией @BeforeAllMethod
     *
     * @param methods массив методов
     * @return список методов с аннотацией @BeforeAllMethod
     */
    public static List<Method> getBeforeAllAnnotated(Method[] methods) throws ConflictingAnnotationsException {
        List<Method> beforeAllMethod = Arrays.stream(methods).
                filter(method -> method.isAnnotationPresent(BeforeAllMethod.class)).collect(Collectors.toList());
        if (beforeAllMethod.stream().filter(method -> method.isAnnotationPresent(TestMethod.class)).count() != 0) {
            throw new ConflictingAnnotationsException("Метод имеет противоречащие аннотации!");
        }
        return beforeAllMethod;
    }

    /**
     * Метод для получения списка методов с аннотацией @AfterAllMethod
     *
     * @param methods массив методов
     * @return список методов с аннотацией @AfterAllMethod
     */
    public static List<Method> getAfterAllAnnotated(Method[] methods) throws ConflictingAnnotationsException {
        List<Method> afterAllMethod = Arrays.stream(methods).
                filter(method -> method.isAnnotationPresent(AfterAllMethod.class)).collect(Collectors.toList());
        if (afterAllMethod.stream().filter(method -> method.isAnnotationPresent(TestMethod.class)).count() != 0) {
            throw new ConflictingAnnotationsException("Метод имеет противоречащие аннотации!");
        }
        return afterAllMethod;
    }

    /**
     * Метод для получения списка методов с аннотацией @ATestMethod
     *
     * @param methods массив методов
     * @return список методов с аннотацией @TestMethod
     */
    public static List<Method> getTestAnnotated(Method[] methods) {
        return Arrays.stream(methods).
                filter(method -> method.isAnnotationPresent(TestMethod.class))
                .sorted(new TestComparator()).collect(Collectors.toList());
    }

    /**
     * Метод для получения приоритета метода
     *
     * @param method метод, у которого получаем приоритет
     * @return приоритет метода
     */
    private static int getOrder(Method method) {
        int order = 0;
        Annotation[] annotationA = method.getAnnotations();
        for (Annotation annotation1 : annotationA) {
            if (annotation1 instanceof TestMethod) {
                order = ((TestMethod) annotation1).order();
            }
        }
        return order;
    }

    /**
     * Компаратор для сортировки тестовых методов по приоритету
     */
    static class TestComparator implements Comparator<Method> {

        public int compare(Method a, Method b) {
            int orderA = getOrder(a);
            int orderB = getOrder(b);
            return orderA > orderB ? 1 : orderA == orderB ? 0 : -1;
        }
    }
}
