package tumbaev;

import tumbaev.annotation.AfterAllMethod;
import tumbaev.annotation.BeforeAllMethod;
import tumbaev.annotation.TestMethod;
import tumbaev.exception.AssertionFailError;
import tumbaev.exception.NoTestMethodsException;
import tumbaev.exception.NotUniqueAnnotationException;
import tumbaev.exception.TestRunningException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class that runs tests described in a special test class.
 */
public final class Tester {
    private Tester() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    /**
     * Runs test methods in a test class. Results of tests will be printed to console.
     * <p>
     * Test class should have a public constructor wih no arguments.
     * <p>
     * Test methods should be annotated with {@link TestMethod}.
     * They will be invoked in the order specified by the annotation.
     * Such methods have the following rules:
     * <ul>
     *     <li>At least one such method should be present in a test class</li>
     *     <li>Should not be a before-all or after-all method</li>
     *     <li>Should not have arguments</li>
     *     <li>Should be public</li>
     * </ul>
     *
     * <p>
     * Method annotated with {@link BeforeAllMethod} will be invoked before all tests.
     * <br>
     * Rules of before-all method:
     * <ul>
     *     <li>May not be present</li>
     *     <li>Should not have arguments</li>
     *     <li>Only one before-all method in a class</li>
     *     <li>Should be public</li>
     * </ul>
     * <p>
     * Method annotated with {@link AfterAllMethod} will be invoked after all test methods.
     * After-all method has the same rules as before-all.
     *
     * <p>
     *
     * @param testClass class with test methods
     * @throws TestRunningException         failed to run test(s)
     * @throws NoTestMethodsException       there are no methods annotated with {@link TestMethod}
     * @throws NotUniqueAnnotationException – there is more than one method annotated with BeforeAllMethod or AfterAllMethod
     */
    public static List<TestResult> start(Class<?> testClass) {
        try {
            List<TestResult> testResults = runTest(testClass);
            for (TestResult res : testResults) {
                System.out.printf("%s: %s%n", res.getTestMethodName(), res.isSuccessful() ? "PASS" : "FAIL");
                if (!res.isSuccessful()) {
                    res.getMessage().ifPresent(m -> System.out.printf("     message: '%s'%n", m));
                    res.getCause().ifPresent(c -> {
                        System.out.println("    exception:");
                        c.printStackTrace();
                    });
                }
            }
            return testResults;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | IllegalArgumentException e) {
            throw new TestRunningException("Failed to run test(s)", e);
        }
    }

    /**
     * Runs methods annotated with {@link BeforeAllMethod} (if present), {@link TestMethod} and {@link AfterAllMethod} (if present),
     * in this exact order. Test methods are invoked in order specified in {@link TestMethod} annotation.
     *
     * @param testClass class with test methods
     * @return test results
     * @throws NoSuchMethodException        cannot invoke method, because it's not found
     * @throws InvocationTargetException    method or a constructor throws an exception
     * @throws InstantiationException       test class is abstract
     * @throws IllegalAccessException       constructor or method is inaccessible
     * @throws IllegalArgumentException     test methods have arguments
     * @throws NoTestMethodsException       there are no methods annotated with {@link TestMethod}
     * @throws NotUniqueAnnotationException there is more than one method annotated with {@link BeforeAllMethod} or {@link AfterAllMethod}
     */
    private static List<TestResult> runTest(Class<?> testClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object obj = getInstanceOf(testClass);

        Method[] methods = testClass.getMethods();
        Optional<Method> beforeAllOpt = getBeforeAllMethod(methods);
        Optional<Method> afterAllOpt = getAfterAllMethod(methods);

        if (Arrays.stream(methods).noneMatch(m -> m.isAnnotationPresent(TestMethod.class))) {
            throw new NoTestMethodsException("At least one method annotated with @TestMethod should be present");
        }

        if (beforeAllOpt.isPresent()) {
            beforeAllOpt.get().invoke(obj);
        }

        List<TestResult> testResults = runTestMethods(obj, methods);

        if (afterAllOpt.isPresent()) {
            afterAllOpt.get().invoke(obj);
        }

        return testResults;
    }

    /**
     * Invokes only tests annotated with {@link TestMethod}. Methods are sorted by the order specified in {@link TestMethod}.
     * <br>
     * Methods that are not annotated with {@link TestMethod}, or annotated with {@link BeforeAllMethod} or {@link AfterAllMethod}
     * are putted in the end of the array and ignored.
     *
     * @param obj     object of a test class
     * @param methods methods of a test class
     * @return test results
     */
    private static List<TestResult> runTestMethods(Object obj, Method[] methods) {
        sortTestMethods(methods);

        List<TestResult> results = new ArrayList<>();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(TestMethod.class)) {
                continue;
            }

            try {
                method.setAccessible(true);
                method.invoke(obj);
                results.add(TestResult.builder(true, method.getName()).build());
            } catch (InvocationTargetException e) {
                TestResult result;
                if (e.getTargetException() instanceof AssertionFailError) {
                    result = TestResult.builder(false, method.getName())
                            .message(e.getTargetException().getMessage())
                            .build();
                } else {
                    result = TestResult.builder(false, method.getName())
                            .cause(e.getTargetException())
                            .build();
                }
                results.add(result);
            } catch (Throwable e) {
                results.add((TestResult.builder(false, method.getName()).cause(e).build()));
            }
        }
        return results;
    }

    /**
     * Sort test methods by the order specified in {@link TestMethod}
     *
     * @param methods Methods to sort. Methods that are not annotated with {@link TestMethod},
     *                or annotated with {@link BeforeAllMethod} or {@link AfterAllMethod} are putted in the end of the array.
     */
    private static void sortTestMethods(Method[] methods) {
        Arrays.sort(methods, Comparator.comparingInt(m -> {
                    TestMethod annotation = m.getAnnotation(TestMethod.class);
                    if (annotation == null ||
                            m.isAnnotationPresent(AfterAllMethod.class) ||
                            m.isAnnotationPresent(BeforeAllMethod.class)) {
                        return Integer.MAX_VALUE;
                    }
                    return annotation.order();
                }
        ));
    }

    /**
     * Return method annotated with {@link BeforeAllMethod}
     *
     * @param methods test methods
     * @return empty if there is no method annotated with {@link BeforeAllMethod}, this method otherwise
     * @throws NotUniqueAnnotationException if there is more than one method annotated with {@link BeforeAllMethod}
     */
    private static Optional<Method> getBeforeAllMethod(Method[] methods) {
        List<Method> beforeAllMethods = Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(BeforeAllMethod.class)).collect(Collectors.toList());
        if (beforeAllMethods.size() > 1) {
            throw new NotUniqueAnnotationException("Annotation 'BeforeAllMethod' must be applied only once");
        }
        return beforeAllMethods.isEmpty() ? Optional.empty() : Optional.of(beforeAllMethods.get(0));
    }

    /**
     * Return method annotated with {@link AfterAllMethod}
     *
     * @param methods test methods
     * @return empty if there is no method annotated with {@link AfterAllMethod}, this method otherwise
     * @throws NotUniqueAnnotationException if there is more than one method annotated with {@link AfterAllMethod}
     */
    private static Optional<Method> getAfterAllMethod(Method[] methods) {
        List<Method> afterAllMethods = Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(AfterAllMethod.class)).collect(Collectors.toList());
        if (afterAllMethods.size() > 1) {
            throw new NotUniqueAnnotationException("Annotation 'AfterAllMethod' must be applied only once");
        }
        return afterAllMethods.isEmpty() ? Optional.empty() : Optional.of(afterAllMethods.get(0));
    }

    /**
     * Creates an instance of provided class
     *
     * @param c class, should have constructor with no arguments
     * @return instance of provided class
     * @throws NoSuchMethodException     constructor with no methods not found
     * @throws InvocationTargetException constructor threw exception
     * @throws InstantiationException    c is an abstract class
     * @throws IllegalAccessException    constructor is inaccessible
     */
    private static Object getInstanceOf(Class<?> c)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = c.getConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }
}
