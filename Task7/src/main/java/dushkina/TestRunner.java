package dushkina;

import dushkina.exception.TestAnnotationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс тестировщик для запуска тестов, представляет собой аналогию junit
 */
public class TestRunner {
    /**
     * Запрет на создание экземпляра класса
     */
    private TestRunner() {
    }

    /**
     * Метод для запуска тестового класса, аннотированного пользовательскими аннотациями.
     * Создаётся массив методов
     * Затем в List<Method> afterMethods записываются методы с аннотацией AfterAllMethod
     * в List<Method> beforeMethods записываются методы с аннотацией BeforeAllMethod
     * в List<Method> testMethods записываются методы с аннотацией TestMethod
     * Затем Методы вызываются по порядку, сначала метод с аннотацией BeforeAllMethod, если такой есть
     * дальше сортируются методы с аннотацией TestMethod, в соответствие с приоритетом, если такие имеются
     * затем метод с аннотацией AfterAllMethod, если такой есть
     *
     * @param className - Класс, содержащий пользовательские аннотации и методы для тестирования.
     * @throws TestAnnotationException если класс == null, если нет ни одной аннотации, если аннотаций
     *                                 BeforeAllMethod/AfterAllMethod > 1
     */
    public static void start(Class<?> className) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        if (className == null) {
            throw new TestAnnotationException("Класс не может быть равным null");
        }
        Object object = className.getDeclaredConstructor().newInstance();
        Method[] methods = className.getDeclaredMethods();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterAllMethod.class)) {
                afterMethods.add(method);
            }
            if (method.isAnnotationPresent(BeforeAllMethod.class)) {
                beforeMethods.add(method);
            }
            if (method.isAnnotationPresent(TestMethod.class)) {
                testMethods.add(method);
            }
        }
        if (beforeMethods.size() > 1 || afterMethods.size() > 1) {
            throw new TestAnnotationException("В программе может быть только одна аннотация BeforeAllMethod/AfterAllMethod");
        }
        if (testMethods.isEmpty() && afterMethods.isEmpty() && beforeMethods.isEmpty()) {
            throw new TestAnnotationException("В программе должна быть хотя бы одна аннотация");
        }
        if (!beforeMethods.isEmpty()) {
            beforeMethods.get(0).invoke(object);
        }
        testMethods = sortTestMethods(testMethods);
        for (Method testMethod : testMethods) {
            testMethod.invoke(object);
        }
        if (!afterMethods.isEmpty()) {
            afterMethods.get(0).invoke(object);
        }

    }

    /**
     * Метод сортирует список методов testMethods в соответствии с приоритетами,
     * указанными в аннотациях TestMethod для каждого метода
     *
     * @param testMethods - список методов с аннотацией TestMethod
     */
    private static List<Method> sortTestMethods(List<Method> testMethods) {
        testMethods.sort(new Comparator<Method>() {
            @Override
            public int compare(Method method1, Method method2) {
                int order1 = method1.getAnnotation(TestMethod.class).order();
                int order2 = method2.getAnnotation(TestMethod.class).order();
                return Integer.compare(order1, order2);
            }
        });
        return testMethods;
    }
}
