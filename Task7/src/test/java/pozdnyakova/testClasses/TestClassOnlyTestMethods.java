package pozdnyakova.testClasses;

import pozdnyakova.annotations.TestMethod;

/**
 * Класс теста только с методами с аннотацией @TestMethod
 */

public class TestClassOnlyTestMethods {
    private static String testString = "";

    public static String getTestString() {
        return testString;
    }

    @TestMethod(order = 1)
    public void firstTestMethod() {
        testString = testString + "firstMethod ";
    }

    @TestMethod(order = 4)
    public void secondTestMethod() {
        testString = testString + "secondMethod ";
    }

    @TestMethod(order = 7)
    public void thirdTestMethod() {
        testString = testString + "thirdMethod ";
    }
}
