package abdullaeva.classesForTest;

import abdullaeva.annotations.BeforeAllMethod;
import abdullaeva.annotations.TestMethod;

/**
 * Класс для проверки работы кастомных аннотации при вызове тест-методов в классе с двумя методами,
 * помеченными аннотацией BeforeAllMethod
 */
public class ClassWithTwoBeforeAllAnnotation {
    /**
     * Строка для вывода информации о вызванном методе.
     */
    private String executedMethodMessage;

    /**
     * Тест-метод для проверки корректности вызова методов с аннотацией BeforeAllMethod.
     * Метод должен быть вызван первым до выполнения всех тест-методов.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @BeforeAllMethod
    public void firstTestMethod() {
        executedMethodMessage = "Method was called first according to the set BeforeAllMethod annotation";
        System.out.println("firstTestMethod: " + executedMethodMessage);
    }

    /**
     * Тест-метод для проверки порядка вызова тестов в классе, где 2 метода
     * помечены аннотацией BeforeAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 1)
    public void secondTestMethod() {
        executedMethodMessage = "Method was called third after executed method with BeforeAllMethod annotation";
        System.out.println("secondTestMethod: " + executedMethodMessage);
    }

    /**
     * Второй тест-метод для проверки корректности вызова методов с аннотацией BeforeAllMethod.
     * Метод должен вызвать исключение.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @BeforeAllMethod
    public void thirdTestMethod() {
        executedMethodMessage = "Method was called second according to the set BeforeAllMethod annotation";
        System.out.println("thirdTestMethod: " + executedMethodMessage);
    }
}
