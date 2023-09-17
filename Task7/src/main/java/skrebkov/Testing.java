package skrebkov;

import skrebkov.annotation.AfterAllMethod;
import skrebkov.annotation.BeforeAllMethod;
import skrebkov.annotation.TestMethod;
import skrebkov.exception.TestAnnotationError;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс для проведения тестов
 */
public class Testing {
    /**
     * Метод для поиска методов для тестов в переданном объекте
     *
     * @param testClass класс
     * @throws TestAnnotationError если есть ошибки в формате аннотаций в тестируемом классе
     */
    public static void runTest(Object testClass) {
        Method beforeAllMethod = null;
        Method afterAllMethod = null;
        List<Method> testMethods = new ArrayList<>();
        Method[] methods = testClass.getClass().getDeclaredMethods();
        for (var method : methods) {
            if (method.isAnnotationPresent(BeforeAllMethod.class)) {
                if (beforeAllMethod != null) {
                    throw new TestAnnotationError("Не может быть больше одного BeforeAllMethod в классе");
                }
                beforeAllMethod = method;
            }
            if (method.isAnnotationPresent(AfterAllMethod.class)) {
                if (afterAllMethod != null) {
                    throw new TestAnnotationError("Не может быть больше одного AfterAllMethod в классе");
                }
                afterAllMethod = method;
            }
            if (method.isAnnotationPresent(TestMethod.class)) {
                testMethods.add(method);
            }
        }

        if (beforeAllMethod != null) {
            testMethod(beforeAllMethod, testClass);
        }

        testMethods.stream()
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(TestMethod.class).priority()))
                .forEach(method -> testMethod(method, testClass));

        if (afterAllMethod != null) {
            testMethod(afterAllMethod, testClass);
        }
    }

    /**
     * Метод для запуска и проверки работоспособности переданного метода
     *
     * @param method    - метод для запуска
     * @param testClass - тестируемый класс
     */
    private static void testMethod(Method method, Object testClass) {
        try {
            method.setAccessible(true);
            method.invoke(testClass);
            System.out.println("Тестируется метод: " + method.getName() +
                    ", в классе: " + testClass.getClass().getName() +
                    "\nМетод протестирован успешно");
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.err.println("Тестируется метод: " + method.getName() +
                    ", в классе: " + testClass.getClass().getName() +
                    "\nМетод провалил тестирование!!!");
        }
    }
}
