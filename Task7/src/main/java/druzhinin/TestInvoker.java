package druzhinin;

import druzhinin.annotations.AfterAllMethod;
import druzhinin.annotations.BeforeAllMethod;
import druzhinin.annotations.TestMethod;
import druzhinin.exceptions.FailedTestClassInstantiatingException;
import druzhinin.exceptions.FailedTestInvocationException;
import druzhinin.exceptions.InvalidMethodsAmountException;
import druzhinin.exceptions.NoTestMethodsException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс для анализа тестовых классов и вызова из них тестовых методов.
 * Поддерживается обработка тестовых методов с аннотациями
 * {@link TestMethod} (с приоритетами), {@link BeforeAllMethod} и {@link AfterAllMethod}.
 *
 * @author Дружинин Артем.
 */
public class TestInvoker {

    /**
     * Метод для вызова из класса {@code testClass} тестовых методов, помеченных аннотациями
     * {@link TestMethod}, {@link BeforeAllMethod} и {@link AfterAllMethod}
     *
     * @param testClass Класс, содержащий в себе тестовые методы.
     * @throws FailedTestClassInstantiatingException в случае,
     * если была получена ошибка создания объекта {@code testClass}.
     * @throws InvalidMethodsAmountException в случае,
     * если методов с аннотациями {@link BeforeAllMethod} или {@link AfterAllMethod} было найдено более одного.
     * @throws NoTestMethodsException в случае, если методов с аннотацией {@link TestMethod} не найдено.
     * @throws FailedTestInvocationException в случае, если была получена ошибка вызова метода {@code method}.
     */
    public static void start(Class<?> testClass) {
        Object testClassObject = getTestClassInstance(testClass);

        Method[] methods = testClass.getMethods();

        Optional<Method> beforeAllMethod = getZeroOrOneMethodWithAnnotation(methods, BeforeAllMethod.class);
        Optional<Method> afterAllMethod = getZeroOrOneMethodWithAnnotation(methods, AfterAllMethod.class);

        List<Method> sortedByOrderTestMethods = getTestMethodsSortedByOrder(methods);

        beforeAllMethod.ifPresent((beforeAll) -> runTest(testClassObject, beforeAll));

        sortedByOrderTestMethods.forEach((method) -> runTest(testClassObject, method));

        afterAllMethod.ifPresent((afterAll) -> runTest(testClassObject, afterAll));

    }

    /**
     * Метод для вызова тестового метода из класса {@code testClassObject}.
     *
     * @param testClassObject Экземпляр тестового класса.
     * @param method Метод, который необходимо вызывать из класса {@code testClassObject}.
     * @throws FailedTestInvocationException в случае, если была получена ошибка вызова метода {@code method}.
     */
    private static void runTest(Object testClassObject, Method method) {
        try {
            method.setAccessible(true);
            method.invoke(testClassObject);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new FailedTestInvocationException("Ошибка запуска метода " + method.getName(), e);
        }
    }

    /**
     * Метод для получения экземпляра класса {@code testClass}.
     *
     * @param testClass Ссылка на класс, экземпляр которого необходимо получить.
     * @return Возвращает экземпляр класса {@code testClass}.
     * @throws FailedTestClassInstantiatingException в случае,
     * если была получена ошибка создания объекта {@code testClass}.
     */
    private static Object getTestClassInstance(Class<?> testClass) {
        Object testClassObject;
        try {
            testClassObject = testClass.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new FailedTestClassInstantiatingException(
                    "Ошибка создания экземпляра класса " + testClass.getName(), e);
        }
        return testClassObject;
    }

    /**
     * Метод для получения тестовых методов (помеченных аннотацией {@link TestMethod}),
     * отсортированных по приоритету (чем меньше число, тем выше приоритет)
     * из списка всех методов тестового класса.
     *
     * @param allMethods Методы, которые необходимо отфильтровать и отсортировать.
     * @return Возвращает список отсортированных тестовых методов.
     * @throws NoTestMethodsException в случае, если методов с аннотацией {@link TestMethod} не найдено.
     */
    private static List<Method> getTestMethodsSortedByOrder(Method[] allMethods) {
        List<Method> sortedByOrderTestMethods = Arrays.stream(allMethods)
                .filter((method) -> method.isAnnotationPresent(TestMethod.class))
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(TestMethod.class).order()))
                .collect(Collectors.toList());

        if (sortedByOrderTestMethods.isEmpty()) {
            throw new NoTestMethodsException();
        }

        return sortedByOrderTestMethods;
    }

    /**
     * Метод для получения 0 или 1 тестового метода
     * с аннотацией класса {@code annotationClass} из массива методов {@code methods}.
     *
     * @param methods Методы, которые будут отфильтрованы.
     * @param annotationClass Класс аннотаций, по которому методы будут отфильтрованы.
     * @return Объект класса {@link Optional}, представляющий собой 0 или 1 тестовый метод
     * с аннотацией класса {@code annotationClass}
     * @throws InvalidMethodsAmountException в случае,
     * если методов с аннотацией класса {@code annotationClass} было найдено более одного.
     */
    private static Optional<Method> getZeroOrOneMethodWithAnnotation(Method[] methods,
                                                                     Class<? extends Annotation> annotationClass) {
        List<Method> methodsWithAnnotationList = Arrays.stream(methods)
                .filter((method) -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());

        if (methodsWithAnnotationList.size() > 1) {
            throw new InvalidMethodsAmountException("В классе было найдено более " +
                    "одного метода с аннотацией " + annotationClass.getName());
        }

        if (methodsWithAnnotationList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(methodsWithAnnotationList.get(0));
        }
    }
}
