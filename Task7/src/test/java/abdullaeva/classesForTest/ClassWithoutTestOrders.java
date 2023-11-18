package abdullaeva.classesForTest;

import abdullaeva.annotations.BeforeAllMethod;

/**
 * Класс для проверки работы кастомных аннотаций при вызове тест-методов без заданного приоритета
 */
public class ClassWithoutTestOrders {
    /**
     * Строка для вывода информации о вызванном методе.
     */
    private String executedMethodMessage;

    /**
     * Тест-метод для проверки работы кастомных аннотаций в классе без тест-методов с заданным приоритетом.
     * Метод выводит сообщение с информацией о вызванном методе.
     */
    @BeforeAllMethod
    public void withoutOrderMethod() {
        executedMethodMessage = "Method was called first according to BeforeAllMethod annotation";
        System.out.println("withoutOrderMethod: " + executedMethodMessage);
    }
}
