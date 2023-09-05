package yanko;

import yanko.annotations.AfterAllMethod;
import yanko.annotations.BeforeAllMethod;
import yanko.annotations.TestMethod;

import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * класс для запуска тестов
 */
public class TestRunner {
    /**
     * запуск тестов для конкретного класса с помощью объекта этого класса
     *
     * @param className имя класса
     */
    public static void start(Class<?> className) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object classObject = className.getConstructor().newInstance();
        Method[] methods = className.getDeclaredMethods();
        List<Method> beforeAllMethod = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(BeforeAllMethod.class)).collect(Collectors.toList());
        if (beforeAllMethod.size() > 1) {
            throw new AnnotationFormatError("found several beforeallmethod annotations");
        }
        if (!beforeAllMethod.isEmpty()) {
            beforeAllMethod.get(0).setAccessible(true);
            beforeAllMethod.get(0).invoke(classObject);
        }
        List<Method> methodsInOrder = Arrays.stream(className.getDeclaredMethods())
                .filter(method -> method.getAnnotation(TestMethod.class) != null)
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(TestMethod.class).order())).collect(Collectors.toList());
        for (Method method : methodsInOrder) {
            method.setAccessible(true);
            method.invoke(classObject);
        }
        List<Method> afterAllMethod = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AfterAllMethod.class)).collect(Collectors.toList());
        if (afterAllMethod.size() > 1) {
            throw new AnnotationFormatError("found several afterallmethod annotations");
        }
        if (!afterAllMethod.isEmpty()) {
            afterAllMethod.get(0).setAccessible(true);
            afterAllMethod.get(0).invoke(classObject);
        }
    }
}
