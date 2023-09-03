package tsimmer;

import tsimmer.annotation.AfterAllMethod;
import tsimmer.annotation.BeforeAllMethod;
import tsimmer.annotation.TestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Класс в котором реализовано выполнение тестов
 */
public class TestClass {
    /**
     * подсчет количества аннтоаций before
     */
    private int beforeCount = 0;
    /**
     * подсчет количества аннотаций after
     */
    private int afterCount = 0;

    /**
     * Метод реализующий выполнение аннотаций
     *
     * @param className класс в котором находятся методы
     * @throws Exception методов с аннотацией @BeforeAllMethod или @AfterAllMethod >1
     */
    public static void start(@SuppressWarnings("rawtypes") Class className) throws Exception {
        TestClass testClass = new TestClass();
        Method[] methods = className.getDeclaredMethods();
        ArrayList<Method> arrayList = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeAllMethod.class)) {
                testClass.beforeCount++;
            }
            if (method.isAnnotationPresent(AfterAllMethod.class)) {
                testClass.afterCount++;
            }
        }
        if ((testClass.afterCount | testClass.beforeCount) > 1) {
            throw new RuntimeException();
        }

        for (Method m : methods) {

            if (m.isAnnotationPresent(BeforeAllMethod.class)) {
                m.invoke(null);
            }

            if (m.isAnnotationPresent(TestMethod.class)) {
                arrayList.add(m);
            }
        }

        arrayList.sort(Comparator.comparingInt(o -> o.getAnnotation(TestMethod.class).order()));

        for (int i = 0; i <= arrayList.size() - 1; i++) {
            System.out.print("Приоритет: " + arrayList.get(i).getAnnotation(TestMethod.class).order() + " Тест: ");
            arrayList.get(i).invoke(null);
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterAllMethod.class)) {
                m.invoke(null);
            }
        }
    }
}
