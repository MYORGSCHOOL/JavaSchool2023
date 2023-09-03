package grossu;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.BeforeAllMethod;
import grossu.testannotation.TestMethod;
import grossu.testexception.ClassNotFoundForTest;
import grossu.testexception.UncorrectedUseAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



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
     * Класс запускающий работу тестировщика
     * Класс проверяет передаваемое имя тестируемого класса, если оно не пустое, работа продолжается,
     * иначе выбрасывается исключение ClassNotFoundForTest
     * Метод {@code loadClass} получает объект типа Class и передает его в метод {@code runTest},
     * если класс найти не получается, то выбрасывается исключение ClassNotFoundForTest
     *
     * @param className - имя тестируемого класса
     */
    public static void start(String className) {
        if (className.isEmpty()) {
            throw new ClassNotFoundForTest("class name is empty");
        }
        try {
            Class<?> classForTest = loadClass(className);
            runTest(classForTest);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundForTest("not found class on this name");
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод пробует получить объект класса по заданному имени
     *
     * @param className - имя класса, который необходимо получить
     * @return объект типа Class
     * @throws ClassNotFoundException - если класс не получилось найти
     */
    private static Class<?> loadClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    /**
     * Метод, который вызывает методы помеченные аннотациями из переданного класса
     * Массив методов проверяется на наличие аннотации TestMethod, если нет, то исключение UncorrectedUseAnnotation
     * В переменную {@code beforeAllMethod} функция {@code checkExistMethod} возвращает метод с аннотацией BeforeAllMethod
     * или Optional.empty() если метода с такой аннотацией нет
     * Если метод все же найден, то он вызывается
     * В список {@code testMethod} функция {@code sortedMethodInOrder} возвращает отсортированный по порядку выполнения
     * список методов, а затем они запускаются
     * В переменную {@code afterAllMethod} функция {@code checkExistMethod} возвращает метод с аннотацией afterAllMethod
     * или Optional.empty() если метода с такой аннотацией нет
     * Если метод все же найден, то он вызывается
     *
     * @param classTest - тестируемый класс
     * @throws UncorrectedUseAnnotation - при некорректных аннотациях в тестируемом классе
     */
    private static void runTest(Class<?> classTest) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Method[] methods = classTest.getMethods();
        Object classObj = classTest.getDeclaredConstructor().newInstance();
        if (Arrays.stream(methods).anyMatch(m -> m.isAnnotationPresent(TestMethod.class))) {
            Optional<Method> beforeAllMethod = checkExistMethod(methods, BeforeAllMethod.class);
            if (beforeAllMethod.isPresent()) {
                beforeAllMethod.get().invoke(classObj);
            }

            List<Method> testMethod = sortedMethodInOrder(methods);
            for (var m : testMethod) {
                m.invoke(classObj);
            }

            Optional<Method> afterAllMethod = checkExistMethod(methods, AfterAllMethod.class);
            if (afterAllMethod.isPresent()) {
                afterAllMethod.get().invoke(classObj);
            }
        } else {
            throw new UncorrectedUseAnnotation("in class must be at least one TestMethod annotation");
        }
    }

    /**
     * Метод находит в переданном массиве методы с заданной аннотацией и добавляет список
     * Если найдено несколько методов, выбрасывается исключение UncorrectedUseAnnotation
     *
     * @param methods  - массив методов
     * @param annotate - искомая аннотация
     * @return Optional.empty() - если аннотация не найдена либо Optional.of(найденный метод)
     */
    private static Optional<Method> checkExistMethod(Method[] methods, Class<? extends Annotation> annotate) {
        List<Method> beforeAllMethod = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(annotate)).collect(Collectors.toList());
        if (beforeAllMethod.size() > 1) {
            throw new UncorrectedUseAnnotation("in class can be only one annotation " + annotate.getName());
        }
        if (!beforeAllMethod.isEmpty()) {
            return Optional.of(beforeAllMethod.get(0));

        }
        return Optional.empty();
    }

    /**
     * Метод возвращает отсортированный по порядку выполнения список из методов с аннотацией TestMethod
     *
     * @param methods - массив методов
     * @return отсортированный список методов
     */
    private static List<Method> sortedMethodInOrder(Method[] methods) {
        return Arrays.stream(methods).filter(m -> m.isAnnotationPresent(TestMethod.class))
                .sorted(Comparator.comparing(m1 -> m1.getAnnotation(TestMethod.class).order()))
                .collect(Collectors.toList());
    }
}
