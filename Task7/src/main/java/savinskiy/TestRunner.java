package savinskiy;

import savinskiy.annotations.AfterAllMethod;
import savinskiy.annotations.BeforeAllMethod;
import savinskiy.annotations.TestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс для тестирования методов с аннотациями
 */
public class TestRunner {
    /**
     * Метод запускает тесты в классе
     *
     * @param className класс с тестами
     */
    public static void start(Class<?> className) {
        try {
            List<Method> testMethods = new ArrayList<>();
            Method[] methods = className.getDeclaredMethods();
            for (var method : methods) {
                if (method.isAnnotationPresent(TestMethod.class)) {
                    testMethods.add(method);
                }
            }
            testMethods.sort(Comparator.comparingInt(method ->
                    method.getAnnotation(TestMethod.class).order()));

            Object testObject = className.getDeclaredConstructor().newInstance();

            beforeAndAfterExecutor(BeforeAllMethod.class, testObject, methods);

            for (var method : testMethods) {
                method.invoke(testObject);
            }

            beforeAndAfterExecutor(AfterAllMethod.class, testObject, methods);

        } catch (NoSuchMethodException |
                 InvocationTargetException |
                 IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void beforeAndAfterExecutor(Class<? extends Annotation> annotaionClass, Object object, Method[] methods) {
        int count = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotaionClass)) {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e.getMessage());
                }
                count++;
                if (count > 1) {
                    throw new RuntimeException("There must be only one '" + annotaionClass.getSimpleName() + "'");
                }
            }
        }
    }
}