package pozdnyakova.testClasses;

import pozdnyakova.annotations.BeforeAllMethod;

/**
 * Класс для тестирования случая, когда нет методов @TestMethod
 */

public class TestClassNoTestMethod {
    private static String testString = "";

    public static String getTestString() {
        return testString;
    }

    @BeforeAllMethod
    public void beforeMethod() {
        testString = testString + "beforeMethod ";
    }
}
