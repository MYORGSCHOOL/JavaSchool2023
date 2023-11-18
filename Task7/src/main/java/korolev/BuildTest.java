package korolev;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import korolev.Annotation.AfterAllMethod;
import korolev.Annotation.BeforeAllMethod;
import korolev.Annotation.TestMethod;
import korolev.Exceptions.IteratingOverAnnotations;
import korolev.Exceptions.MissingNecessaryElements;
import korolev.Exceptions.PriorityAllocationError;

/**
 * A class that allows you to create tests for methods with implementation BeforeAll,
 * AfertAll and TestMethod.
 * @author Nikita Koroliov
 * @version 1.0
 */
public final class BuildTest {
    /**
     * Method for running methods,
     * takes into the method a {@code start()} class with written tests.
     * 
     * @param clazz The passed class with the generated test annotations.
     * @throws Exception Displays all possible errors while running tests.
     */
    public static void start(Class<?> clazz) throws Exception {
        try {
            List<Result> resultList = generadeResultList(clazz);

            for (Result r : resultList) {
                System.out.printf("TestNameMethod:(%s) | Status:(%s) - %s\n\n", r.getNameMethod(), r.isStatus(), r.getMassage());
            }
        } catch (Exception e) {
            System.out.printf("\tERROR DURING THE TEST PROCESS!: (%s)\n\n", e);
            throw e;
        }
    }

    /**
     * The method collects methods with tests into the sent method {@code star} in {@link BuildTest}, 
     * saves all results in the class {@link Result}
     * The method sends the list from {@link Result} to {@code start()}.
     * @param classus Accepted class with test annotations at the start.
     * @return List<Result> Returns a list with the results of running test methods.
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchElementException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    private static List<Result> generadeResultList(Class<?> classus) 
    throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
    InstantiationException {
        Method[] methods = classus.getMethods();

        Constructor<?> temp = classus.getConstructor();
        temp.setAccessible(true);
        Object unlockedClass = temp.newInstance();

        Optional<Method> beforeAll = getBeforeOrAfterAllMethod(methods, BeforeAllMethod.class);
        Optional<Method> afterAll = getBeforeOrAfterAllMethod(methods, AfterAllMethod.class);

        if (beforeAll.isPresent()) {
            beforeAll.get().invoke(unlockedClass);
        }

        List<Method> testMethodList = getTestMethod(methods);
        List<Result> results = startFilling(testMethodList, unlockedClass);

        if (afterAll.isPresent()) {
            afterAll.get().invoke(unlockedClass);
        }

        return results;

    }

    /**
     * The method launches the methods taken from the passed class into the stratum, 
     * fills the results into the fields of the class {@link Result}, unnerves the list {@link Result} and returns it.
     * @param list List of collected methods from the passed class.
     * @param clazz A constructor from the passed class to run methods.
     * @return List of completed method processing results.
     */
    private static List<Result> startFilling(List<Method> list, Object clazz) {
        List<Result> results = new ArrayList<>();

        for (Method m : list) {
            if (!m.isAnnotationPresent(TestMethod.class)) {
                continue;
            }

            try {
                m.invoke(clazz);
                results.add(new Result(true, "Executed successfully!", m.getName()));
            } catch (Exception e) {
                results.add(new Result(false, e.toString(), m.getName()));
            }
        }

        return results;
    }

    /**
     * The method accepts an array of methods 
     * filled in the method {@code generadeResultList()}, 
     * collects all methods with the annotation {@link TestMethod}.
     * @param arrMethods An array of methods of the passed class.
     * @return A sorted list of methods with the {@link TestMethod} annotation.
     */
    private static List<Method> getTestMethod(Method[] arrMethods) {
        List<Method> allTest = Arrays.stream(arrMethods)
            .filter(m -> m.isAnnotationPresent(TestMethod.class)).collect(Collectors.toList());
        
        TestMethod method;
        for (Method m : allTest) {
            method = m.getAnnotation(TestMethod.class);
            if (method.order() <= 1) {
                throw new PriorityAllocationError("Incorrect prioritization of methods!");
            }
        }

        if (allTest.isEmpty()) {
            throw new MissingNecessaryElements("Missing the necessary test elements!");
        }

        Collections.sort(allTest, new TestComparator());
        return allTest;
    }

    /**
     * Method for collecting {@code BeforeAll} annotations or {@code AfterAll} annotations.
     * Returns an {@code Optional<Method>} to prevent a {@link NullPointerException}.
     * @param arrMethods An array of methods of the passed class.
     * @param cl The {@link BeforeAll} or {@linkAfterAll} annotation class is passed.
     * @return Returns the Optional<Method> wrapper.
     */
    private static Optional<Method> getBeforeOrAfterAllMethod(Method[] arrMethods, Class<? extends Annotation> cl) {
        List<Method> list = Arrays.stream(arrMethods)
            .filter(m -> m.isAnnotationPresent(cl)).collect(Collectors.toList());
        
        if (list.size() > 1) {
            throw new IteratingOverAnnotations("Exceeded number of annotations BeforeAll!");
        }

        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}

/**
 * {@link Comaparetor} class for sorting methods with priorities in {@code getTestMethod()}.
 */
class TestComparator implements Comparator<Method> {
    public int compare(Method m1, Method m2) {
        TestMethod t1 = m1.getAnnotation(TestMethod.class);
        TestMethod t2 = m2.getAnnotation(TestMethod.class);

        if (t1.order() < t2.order()){
            return -1;
        }
        if (t1.order() == t2.order()) {
            return 0;
        } else {
            return 1;
        }
    }
}
