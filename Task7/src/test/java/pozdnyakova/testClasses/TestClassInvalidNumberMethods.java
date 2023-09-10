package pozdnyakova.testClasses;

import pozdnyakova.annotations.BeforeAllMethod;
import pozdnyakova.annotations.TestMethod;

/**
 * Класс теста с недопустимым количеством методов @BeforeAllMethod
 */

public class TestClassInvalidNumberMethods {
    private static String testString = "";

    public static String getTestString() {
        return testString;
    }

    @BeforeAllMethod
    public void firstBeforeMethod() {
        testString = testString + "beforeMethod ";
    }

    @TestMethod(order = 1)
    public void firstTestMethod() {
        testString = testString + "firstMethod ";

    }

    @BeforeAllMethod
    public void secondBeforeMethod() {
        testString = testString + "beforeMethod ";
    }

}
