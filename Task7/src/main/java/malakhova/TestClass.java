package malakhova;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Класс, выполняющий тесты. Содержит методы для запуска методов в правильном порядке и проверки их наличия
 */
public class TestClass {
    /**
     * Метод, запускающий класс с тестами
     *
     * @param nameClass - класс, методы которого нужно выполнить
     */
    public static void start(Class nameClass) {
        if (!checkBeforeAndAfterTest(BeforeAllMethod.class, nameClass) || !checkBeforeAndAfterTest(AfterAllMethod.class, nameClass)) {
            throw new IllegalArgumentException();
        }
        if (countTestMethod(nameClass) == 0) {
            throw new IllegalArgumentException();
        }
        launchBeforeAndAfterTest(BeforeAllMethod.class, nameClass);
        launchTestMethod(nameClass);
        launchBeforeAndAfterTest(AfterAllMethod.class, nameClass);
    }

    /**
     * Метод, проверяющий количество методов с аннотацией BeforeAllMethod и AfterAllMethod
     *
     * @param testCheck - аннотация, количество которой проверяем
     * @param nameClass - класс, в котором проверяем количество методов
     * @return - true если методов 0 или 1, false если методов больше 1
     */
    private static boolean checkBeforeAndAfterTest(Class testCheck, Class nameClass) {
        int countMethod = 0;
        for (Method method : nameClass.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(testCheck);
            if (annotation != null) {
                countMethod++;
            }
        }
        if (countMethod > 1) {
            return false;
        }
        return true;
    }

    /**
     * Метод, проверяющий количество методов с аннотацией TestMethod
     *
     * @param nameClass - класс, в котором проверяем количество методов
     * @return - количество методов с аннотацией TestMethod
     */
    private static int countTestMethod( Class nameClass) {
        int countMethod = 0;
        for (Method method : nameClass.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(TestMethod.class);
            if (annotation != null) {
                countMethod++;
            }
        }
        return countMethod;
    }

    /**
     * Метод, запускающий методы с аннотацией BeforeAllMethod и AfterAllMethod
     *
     * @param testLaunch - аннотация, методы отмеченные которой необходимо запустить
     * @param nameClass  - класс, в котором находятся методы, которые нужно запустить
     */
    private static void launchBeforeAndAfterTest(Class testLaunch, Class nameClass) {
        for (Method method : nameClass.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(testLaunch);
            if (annotation != null) {
                try {
                    method.setAccessible(true);
                    method.invoke(nameClass);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.getMessage();
                }
            }
        }
    }

    /**
     * Метод, запускающий методы с аннотацией TestMethod
     *
     * @param nameClass - класс, в котором находятся методы, которые нужно запустить
     */
    private static void launchTestMethod(Class nameClass) {
        Method[] methods1 = distributionMethods(nameClass);
        int countMethod = countTestMethod(nameClass);
        for (int i = 0; i < countMethod; i++) {
            try {
                methods1[i].setAccessible(true);
                methods1[i].invoke(nameClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Метод, распределяющий методы с аннотацией TestMethod в порядке приоритета
     *
     * @param nameClass - класс, в котором находятся методы с аннотацией TestMethod
     * @return - массив методов с аннотацией TestMethod, расположенных в порядке приоритета
     */
    private static Method[] distributionMethods(Class nameClass) {
        Method[] methods = nameClass.getDeclaredMethods();
        int countPriorityMethod = checkPriority(nameClass);
        for (Method method : nameClass.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(TestMethod.class);
            Field field;
            if (annotation != null) {
                try {
                    field = annotation.getClass().getDeclaredField("order");
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.getMessage();
                }
                if (((TestMethod) annotation).order() == 0) {
                    methods[countPriorityMethod] = method;
                    countPriorityMethod++;
                } else {
                    int number = ((TestMethod) annotation).order() - 1;
                    methods[number] = method;
                }
            }
        }
        return methods;
    }

    /**
     * Метод, проверяющий количество методов с аннотацией TestMethod имеющих приоритет
     *
     * @param nameClass - класс, в котором находятся методы с аннотацией TestMethod
     * @return - количество методов
     */
    private static int checkPriority(Class nameClass) {
        int countMethod = 0;
        for (Method method : nameClass.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(TestMethod.class);
            Field field;
            if (annotation != null) {
                try {
                    field = annotation.getClass().getDeclaredField("order");
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.getMessage();
                }
                if (((TestMethod) annotation).order() != 0) {
                    countMethod++;
                }
            }
        }
        return countMethod;
    }
}
