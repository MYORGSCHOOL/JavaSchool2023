package proskurina;

import proskurina.annotations.AfterAllMethod;
import proskurina.annotations.BeforeAllMethod;
import proskurina.annotations.Order;
import proskurina.annotations.TestMethod;
import proskurina.exceptions.IllegalTestClassException;
import proskurina.exceptions.MethodFailedRunException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс, который запускает тестовые методы.
 * Для запуска тестирования в метод {@link #start(Class)} нужно
 * передать класс с методами помеченными {@code @TestMethod}.
 *
 * <p> Тестовый класс должен выполнять следующие условия:
 * <li>Методов с аннотацией {@link TestMethod} >= 1</li>
 * <li>Методов с аннотацией {@link BeforeAllMethod} 0 или 1</li>
 * <li>Методов с аннотацией {@link AfterAllMethod} 0 или 1</li>
 * </p>
 *
 * @see Order
 */
public class TestRunner {
    
    private TestRunner() {
    
    }
    
    /**
     * Запускает на выполнения тестовые методы из переданного класса
     * с учетом {@link Order}, если он указан.
     *
     * @param testClass класс с тестами
     * @throws NullPointerException      если {@code testClass} равен {@code null}
     * @throws MethodFailedRunException  если не удалось выполнить тест
     * @throws IllegalTestClassException если не удалось инициализировать {@code testClass} или
     *                                   нет методов с {@link TestMethod}, или методов с {@link BeforeAllMethod}
     *                                   и {@link AfterAllMethod} больше 1
     */
    public static void start(Class<?> testClass) {
        Objects.requireNonNull(testClass);
        var instance = getInstance(testClass);
        
        List<Method> tests = getAnnotatedMethods(testClass, TestMethod.class);
        if (tests.isEmpty()) {
            throw new IllegalTestClassException(testClass.getSimpleName()
                    + ": не найдено методов с @TestMethod");
        }
        
        Optional<Method> beforeAll = requireSingleOrNoneMethod(testClass, BeforeAllMethod.class);
        Optional<Method> afterAll = requireSingleOrNoneMethod(testClass, AfterAllMethod.class);
        
        sortByOrder(tests);
        
        beforeAll.ifPresent(method -> runMethod(method, instance));
        tests.forEach(test -> runMethod(test, instance));
        afterAll.ifPresent(method -> runMethod(method, instance));
    }
    
    /**
     * Возвращает список методов с аннотацией  {@code annotation}.
     *
     * @param testClass  класс с методами
     * @param annotation аннотация
     * @return список методов с аннотацией
     */
    private static List<Method> getAnnotatedMethods(Class<?> testClass,
                                                    Class<? extends Annotation> annotation) {
        return Arrays.stream(testClass.getDeclaredMethods())
                     .filter(m -> m.isAnnotationPresent(annotation))
                     .collect(Collectors.toList());
    }
    
    /**
     * Сортирует методы по возрастанию {@link Order#value()}, если аннотация отсутствует,
     * то значение будет считаться равным {@link Integer#MAX_VALUE}.
     *
     * @param tests список методов
     */
    private static void sortByOrder(List<Method> tests) {
        tests.sort((test1, test2) -> {
            Order firstAnno = test1.getAnnotation(Order.class);
            Order secondAnno = test2.getAnnotation(Order.class);
            
            int firstValue = firstAnno != null ? firstAnno.value() : Integer.MAX_VALUE;
            int secondValue = secondAnno != null ? secondAnno.value() : Integer.MAX_VALUE;
            
            return Integer.compare(firstValue, secondValue);
        });
    }
    
    /**
     * Создает и возвращает экземпляр переданного класса.
     *
     * @param testClass класс, экземпляр которого нужно создать
     * @return экземпляр класса
     * @throws IllegalTestClassException если не удалось создать экземпляр
     */
    private static Object getInstance(Class<?> testClass) {
        try {
            var ctor = testClass.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (NoSuchMethodException     |
                 InstantiationException    |
                 InvocationTargetException |
                 IllegalAccessException e) {
            throw new IllegalTestClassException("Не удалось инициализировать " + testClass.getSimpleName(), e);
        }
    }
    
    /**
     * Запускает на выполнение переданный метод.
     *
     * @param method выполняемый метод
     * @param obj    объект класса метода
     * @throws MethodFailedRunException если не удалось выполнить метод
     */
    private static void runMethod(Method method, Object obj) {
        try {
            method.setAccessible(true);
            method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MethodFailedRunException(e);
        }
    }
    
    /**
     * Проверяет, что метода с аннотацией {@code annotation} либо нет, либо он один,
     * возвращает {@code Optional} со ссылкой на метод, если он существует.
     *
     * @param testClass  где проверять
     * @param annotation с какой аннотацией
     * @return ссылку на найденный метод
     * @throws IllegalTestClassException если методов с аннотацией больше 1
     */
    private static Optional<Method> requireSingleOrNoneMethod(Class<?> testClass,
                                                              Class<? extends Annotation> annotation) {
        List<Method> methods = getAnnotatedMethods(testClass, annotation);
        if (methods.size() > 1) {
            throw new IllegalTestClassException(testClass.getSimpleName() +
                    ": найдено " + annotation.getSimpleName() + methods.size());
        }
        return methods.isEmpty() ? Optional.empty()
                                 : Optional.of(methods.get(0));
    }
    
}