package karmanchikova;

import karmanchikova.annotations.AfterAllMethod;
import karmanchikova.annotations.BeforeAllMethod;
import karmanchikova.annotations.TestMethod;

import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task7 {
    public static void start(Class<?> className) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object classObject = className.getConstructor().newInstance();
        Method[] methods = className.getDeclaredMethods();
        List<Method> beforeAllMethod = Arrays.stream(methods).
                filter(method -> method.isAnnotationPresent(BeforeAllMethod.class)).collect(Collectors.toList());
        if (beforeAllMethod.size() > 1) {
            throw new AnnotationFormatError("");
        }
        if (!beforeAllMethod.isEmpty()) {
            beforeAllMethod.get(0).setAccessible(true);
            beforeAllMethod.get(0).invoke(classObject);
        }
        List<Method> methodsOrder = Arrays.stream(className.getDeclaredMethods()).
                filter(method -> method.getAnnotation(TestMethod.class) != null).
                sorted(Comparator.comparingInt(method -> method.getAnnotation(TestMethod.class).order())).collect(Collectors.toList());
        for (Method method : methodsOrder) {
            method.setAccessible(true);
            method.invoke(classObject);
        }
        List<Method> afterAllMethod = Arrays.stream(methods).
                filter(method -> method.isAnnotationPresent(AfterAllMethod.class)).collect(Collectors.toList());
        if (afterAllMethod.size() > 1) {
            throw new AnnotationFormatError("");
        }
        if (!afterAllMethod.isEmpty()) {
            afterAllMethod.get(0).setAccessible(true);
            afterAllMethod.get(0).invoke(classObject);
        }
    }
}