package abdullaeva.classesForTest;

import abdullaeva.annotations.BeforeAllMethod;
import abdullaeva.annotations.TestMethod;

/**
 * Класс для проверки работы кастомной аннотации BeforeAllMethod при вызове тест-методов
 */
public class ClassWithBeforeAllAnnotation {
    /**
     * Строка для вывода информации о вызванном методе.
     */
    private String executedMethodMessage;

    /**
     * Строка, в которой будет записана последовательность вызова методов.
     */
    private static String resultString = "";

    public static String getResultString() {
        return resultString;
    }

    /**
     * Первый тест-метод для проверки порядка вызова тестов после метода с аннотацией BeforeAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 1)
    public void firstTestMethod() {
        executedMethodMessage = "Method was called second after executed method with BeforeAllMethod annotation";
        resultString = resultString + "TestMethod(order = 1); ";
        System.out.println("firstTestMethod: " + executedMethodMessage);
    }

    /**
     * Второй тест-метод для проверки порядка вызова тестов после метода с аннотацией BeforeAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 2)
    public void secondTestMethod() {
        executedMethodMessage = "Method was called third after executed method with BeforeAllMethod annotation";
        resultString = resultString + "TestMethod(order = 2); ";
        System.out.println("secondTestMethod: " + executedMethodMessage);
    }

    /**
     * Третий тест-метод для проверки порядка вызова тестов после метода с аннотацией BeforeAllMethod.
     * Метод должен быть вызван первым до выполнения всех тест-методов.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @BeforeAllMethod
    public void thirdTestMethod() {
        executedMethodMessage = "Method was called first according to the set BeforeAllMethod annotation";
        resultString = resultString + "BeforeAllMethod; ";
        System.out.println("thirdTestMethod: " + executedMethodMessage);
    }
}
