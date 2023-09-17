package babadzhanov;

import babadzhanov.annotations.AfterAllMethod;
import babadzhanov.annotations.BeforeAllMethod;
import babadzhanov.annotations.DisplayName;
import babadzhanov.annotations.TestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Тестовый фреймворк
 */
public class TestFramework {

    /**
     * Метод для запуска тестов
     *
     * @param testClass Тестируемый класс
     */
    public void start(Class<?> testClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (testClass == null) {
            throw new RuntimeException("На вход переданы пустые параметры");
        }
        final Object testObject = testClass.getDeclaredConstructor().newInstance();

        Optional<Method> beforeAllMethod = getAdditionalMethods(testClass, BeforeAllMethod.class);
        if (beforeAllMethod.isPresent()) {
            beforeAllMethod.get().invoke(testObject);
        }

        List<Method> comparedTestMethods = getComparedTestMethods(testClass);
        List<String> results = new ArrayList<>(comparedTestMethods.size());
        for (Method testMethod : comparedTestMethods) {
            String testName;
            if (testMethod.isAnnotationPresent(DisplayName.class)) {
                testName = testMethod.getDeclaredAnnotation(DisplayName.class).value();
            } else {
                testName = testMethod.getName();
            }
            try {
                testMethod.invoke(testObject);
                results.add(String.format("Test %s is success", testName));
            } catch (InvocationTargetException | IllegalAccessException e) {
                results.add(String.format("Test: %s is failed with %s", testName, e.getCause()));
            }
        }

        Optional<Method> afterAllMethod = getAdditionalMethods(testClass, AfterAllMethod.class);
        if (afterAllMethod.isPresent()) {
            afterAllMethod.get().invoke(testObject);
        }

        System.out.println("\nОтчёт:");
        for (String result : results) {
            System.out.println(result);
        }
    }

    /**
     * Метод возвращает тестовый метод помеченный аннотацие @BeforeAllMethod или @AfterAllMethod
     * в зависимости от переданного параметра
     *
     * @param testClass  Тестируемый класс
     * @param annotation Аннотация
     * @return 1-ый элемент из списка или Optional.empty()
     */
    public static Optional<Method> getAdditionalMethods(Class<?> testClass, Class<? extends Annotation> annotation) {
        List<Method> additionalMethods = new ArrayList<>();
        for (Method annotatedMethod : testClass.getDeclaredMethods()) {
            if (annotatedMethod.isAnnotationPresent(annotation)) {
                additionalMethods.add(annotatedMethod);
            }
        }
        if (additionalMethods.size() > 1) {
            throw new RuntimeException("Аннотация может быть только в единичном экземпляре");
        } else if (additionalMethods.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(additionalMethods.get(0));
    }

    /**
     * Метод возвращает отсортиованные по порядку тестовые методы с аннотацией @TestMethod
     *
     * @param testClass Тестируемый класс
     * @return Список тестовых методов
     */
    public static List<Method> getComparedTestMethods(Class<?> testClass) {
        List<Method> methods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TestMethod.class)) {
                methods.add(method);
                methods.sort(Comparator.comparingInt(o -> o.getAnnotation(TestMethod.class).order()));
            }
        }
        return methods;
    }
}
