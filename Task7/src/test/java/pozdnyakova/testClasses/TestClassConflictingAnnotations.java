package pozdnyakova.testClasses;

import pozdnyakova.annotations.AfterAllMethod;
import pozdnyakova.annotations.BeforeAllMethod;
import pozdnyakova.annotations.TestMethod;

/**
 * Класс теста с методом с противоречием аннотаций
 */

public class TestClassConflictingAnnotations {
    private static String testString = "";

    public static String getTestString() {
        return testString;
    }

    @BeforeAllMethod
    public void beforeMethod() {
        testString = testString + "beforeMethod ";
    }

    @TestMethod(order = 1)
    @AfterAllMethod
    public void firstTestMethod() {
        testString = testString + "errorMethod ";
    }
}
