package abdullaeva.classesForTest;

import abdullaeva.annotations.AfterAllMethod;
import abdullaeva.annotations.TestMethod;

/**
 * Класс для проверки работы кастомной аннотации AfterAllMethod при вызове тест-методов
 */
public class ClassWithAfterAllAnnotation {
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
     * Первый тест-метод для проверки порядка вызова тестов до метода с аннотацией AfterAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 1)
    public void firstTestMethod() {
        executedMethodMessage = "Method was called first according to the set order";
        resultString = resultString + "TestMethod(order = 1); ";
        System.out.println("firstTestMethod: " + executedMethodMessage);
    }

    /**
     * Второй тест-метод для проверки порядка вызова тестов до метода с аннотацией AfterAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 2)
    public void secondTestMethod() {
        executedMethodMessage = "Method was called second according to the set order";
        resultString = resultString + "TestMethod(order = 2); ";
        System.out.println("secondTestMethod: " + executedMethodMessage);
    }

    /**
     * Третий тест-метод для проверки порядка вызова тестов до метода с аннотацией AfterAllMethod.
     * Метод должен быть вызван последним после выполнения всех тест-методов.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @AfterAllMethod
    public void thirdTestMethod() {
        executedMethodMessage = "Method was called last according to the set AfterAllMethod annotation";
        resultString = resultString + "AfterAllMethod; ";
        System.out.println("thirdTestMethod: " + executedMethodMessage);
    }
}
