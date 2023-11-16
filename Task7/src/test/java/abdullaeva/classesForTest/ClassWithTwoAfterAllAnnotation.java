package abdullaeva.classesForTest;

import abdullaeva.annotations.AfterAllMethod;
import abdullaeva.annotations.TestMethod;

/**
 * Класс для проверки работы кастомных аннотации при вызове тест-методов в классе с двумя методами,
 * помеченными аннотацией AfterAllMethod
 */
public class ClassWithTwoAfterAllAnnotation {
    /**
     * Строка для вывода информации о вызванном методе.
     */
    private String executedMethodMessage;

    /**
     * Тест-метод для проверки корректности вызова методов с аннотацией AfterAllMethod.
     * Метод должен быть вызван последним после выполнения всех тест-методов.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @AfterAllMethod
    public void firstTestMethod() {
        executedMethodMessage = "Method was called first according to the set BeforeAllMethod annotation";
        System.out.println("firstTestMethod: " + executedMethodMessage);
    }

    /**
     * Тест-метод для проверки порядка вызова тестов в классе, где 2 метода
     * помечены аннотацией AfterAllMethod.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @TestMethod(order = 1)
    public void secondTestMethod() {
        executedMethodMessage = "Method was called first according to the set order";
        System.out.println("secondTestMethod: " + executedMethodMessage);
    }

    /**
     * Второй тест-метод для проверки корректности вызова методов с аннотацией AfterAllMethod.
     * Метод должен вызвать исключение.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @AfterAllMethod
    public void thirdTestMethod() {
        executedMethodMessage = "Method was called third according to the set AfterAllAnnotation annotation";
        System.out.println("thirdTestMethod: " + executedMethodMessage);
    }
}
