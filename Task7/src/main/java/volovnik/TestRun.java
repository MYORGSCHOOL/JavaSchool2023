package volovnik;

import volovnik.annotations.AfterAllMethod;
import volovnik.annotations.BeforeAllMethod;
import volovnik.annotations.TestMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс запуска тестов
 */
public class TestRun {

    /**
     * Метод запуска тестов
     *
     * @param clazz тестируемый класс
     */
    public static void start(Class<?> clazz) {
        try {
            Object instance = clazz.getConstructor().newInstance();
            Method[] methods = instance.getClass().getMethods();

            List<Method> beforeAll = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(BeforeAllMethod.class))
                    .collect(Collectors.toList());
            if (beforeAll.size() > 1) {
                throw new RuntimeException("Метод BeforeAllMethod может быть только 1");
            }
            if (!beforeAll.isEmpty()) {
                beforeAll.get(0).invoke(instance);
            }

            List<Method> testMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(TestMethod.class))
                    .sorted(Comparator.comparingInt(method -> method.getAnnotation(TestMethod.class).order()))
                    .collect(Collectors.toList());
            for (Method method : testMethods) {
                method.invoke(instance);
            }

            List<Method> afterAll = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(AfterAllMethod.class))
                    .collect(Collectors.toList());
            if (afterAll.size() > 1) {
                throw new RuntimeException("Метод AfterAllMethod может быть только 1");
            }
            if (!afterAll.isEmpty()) {
                afterAll.get(0).invoke(instance);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
