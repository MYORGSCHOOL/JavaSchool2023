package abdullaeva.classesForTest;

import abdullaeva.annotations.TestMethod;

/**
 * Класс для проверки работы кастомных аннотаций при вызове тест-методов с одинаковыми заданными приоритетами вызова.
 */
public class ClassWithTwoSameTestOrders {
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
     * Первый тест-метод для проверки порядка вызова тестов с одинаковыми заданными приоритетами вызова.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 1)
    public void firstTestMethod() {
        executedMethodMessage = "Method was called first according to the set order";
        resultString = resultString + "TestMethod(order = 1); ";
        System.out.println("firstTestMethod: " + executedMethodMessage);
    }

    /**
     * Второй тест-метод для проверки порядка вызова тестов с одинаковыми заданными приоритетами вызова.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 2)
    public void secondTestMethod() {
        executedMethodMessage = "Method was called second according to the set order";
        resultString = resultString + "TestMethod(order = 2); ";
        System.out.println("secondTestMethod: " + executedMethodMessage);
    }

    /**
     * Третий тест-метод для проверки порядка вызова тестов с одинаковыми заданными приоритетами вызова.
     * Метод должен быть вызван после метода с тем же приоритетом без исключения.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 2)
    public void thirdTestMethod() {
        executedMethodMessage = "Method was called third after method with the same order";
        resultString = resultString + "TestMethod(order = 2) same; ";
        System.out.println("thirdTestMethod: " + executedMethodMessage);
    }
}
