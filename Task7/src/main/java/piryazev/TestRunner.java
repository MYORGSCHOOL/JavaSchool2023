package piryazev;

import piryazev.annotations.AfterAllMethod;
import piryazev.annotations.BeforeAllMethod;
import piryazev.annotations.TestMethod;
import piryazev.exception.NoAnnotationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, выполняющий тестирование с использованием аннотаций.
 */
public class TestRunner {
    /**
     * Статическая переменная для получения первого элемента из массива.
     */
    public static int GET_FIRST_ELEMENT_ARRAY = 0;

    /**
     * Метод для запуска тестового класса, аннотированного пользовательскими аннотациями.
     *
     * @param className Класс, содержащий пользовательские аннотации и методы для тестирования.
     * @throws RuntimeException          Если в программе присутствует более одной аннотации @BeforeAllMethods или @AfterAllMethods.
     * @throws InvocationTargetException Если возникла ошибка при вызове метода.
     * @throws IllegalAccessException    Если нет доступа к методам.
     * @throws NoSuchMethodException     Если не найден конструктор по умолчанию в классе.
     * @throws InstantiationException    Если не удалось создать экземпляр класса.
     */
    public static void start(Class<?> className) throws RuntimeException, InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {

        if (className == null) {
            throw new NullPointerException("Class cannot be null");
        }

        Object instance = className.getDeclaredConstructor().newInstance();

        Method[] methods = className.getDeclaredMethods();
        List<Method> afterAllMethods = new ArrayList<>();
        List<Method> beforeAllMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterAllMethod.class)) {
                afterAllMethods.add(method);
            } else if (method.isAnnotationPresent(BeforeAllMethod.class)) {
                beforeAllMethods.add(method);
            } else if (method.isAnnotationPresent(TestMethod.class)) {
                testMethods.add(method);
            }
        }

        if (beforeAllMethods.size() > 1 || afterAllMethods.size() > 1) {
            throw new RuntimeException("Only one BeforeAllMethods or AfterAllMethods annotation can be in program");
        }

        if (testMethods.isEmpty() && afterAllMethods.isEmpty() && beforeAllMethods.isEmpty()) {
            throw new NoAnnotationException("At least one annotation should be in a program");
        }

        if (!beforeAllMethods.isEmpty()) {
            beforeAllMethods.get(GET_FIRST_ELEMENT_ARRAY).invoke(instance);
        }

        testMethods.sort((method1, method2) -> {
                    int order1 = method1.getAnnotation(TestMethod.class).order();
                    int order2 = method2.getAnnotation(TestMethod.class).order();
                    return Integer.compare(order1, order2);
                }
        );

        for (Method testMethod : testMethods) {
            testMethod.invoke(instance);
        }

        if (!afterAllMethods.isEmpty()) {
            afterAllMethods.get(GET_FIRST_ELEMENT_ARRAY).invoke(instance);
        }
    }
}
