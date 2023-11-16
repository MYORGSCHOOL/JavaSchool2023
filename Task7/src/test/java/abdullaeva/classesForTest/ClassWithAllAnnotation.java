package abdullaeva.classesForTest;

import abdullaeva.annotations.AfterAllMethod;
import abdullaeva.annotations.BeforeAllMethod;
import abdullaeva.annotations.TestMethod;

/**
 * Класс для проверки работы всех кастомных аннотаций при вызове тест-методов в рамках одного класса
 */
public class ClassWithAllAnnotation {
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
     * Первый тест-метод для проверки порядка вызова тестов с кастомными аннотациями.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @BeforeAllMethod
    public void firstTestMethod() {
        executedMethodMessage = "Method was called first according to the set BeforeAllMethod annotation";
        resultString = resultString + "BeforeAllMethod; ";
        System.out.println("firstTestMethod: " + executedMethodMessage);
    }

    /**
     * Второй тест-метод для проверки порядка вызова тестов с кастомными аннотациями.
     * Метод должен быть вызван после метода с аннотацией beforeAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 1)
    public void secondTestMethod() {
        executedMethodMessage = "Method was called second after executed method with BeforeAllMethod annotation";
        resultString = resultString + "TestMethod(order = 1); ";
        System.out.println("secondTestMethod: " + executedMethodMessage);
    }

    /**
     * Третий тест-метод для проверки порядка вызова тестов с кастомными аннотациями.
     * Метод должен быть вызван последним после выполнения всех тест-методов.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @AfterAllMethod
    public void thirdTestMethod() {
        executedMethodMessage = "Method was called last according to the set AfterAllMethod annotation";
        resultString = resultString + "AfterAllMethod; ";
        System.out.println("thirdTestMethod: " + executedMethodMessage);
    }

    /**
     * Четвёртый тест-метод для проверки порядка вызова тестов с кастомными аннотациями.
     * Метод должен быть вызван после метода с предшествующим текущему приоритетом.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 2)
    public void fourthTestMethod() {
        executedMethodMessage = "Method was called third after executed method with BeforeAllMethod annotation";
        resultString = resultString + "TestMethod(order = 2); ";
        System.out.println("fourthTestMethod: " + executedMethodMessage);
    }

}
