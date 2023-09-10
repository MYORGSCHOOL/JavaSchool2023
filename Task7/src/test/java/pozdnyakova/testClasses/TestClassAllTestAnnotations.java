package pozdnyakova.testClasses;

import pozdnyakova.annotations.AfterAllMethod;
import pozdnyakova.annotations.BeforeAllMethod;
import pozdnyakova.annotations.TestMethod;

/**
 * Класс теста со всеми созданными тестовыми аннотациями
 */

public class TestClassAllTestAnnotations {
    private static String testString = "";

    public static String getTestString() {
        return testString;
    }

    @BeforeAllMethod
    public void beforeMethod() {
        testString = testString + "beforeMethod ";
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

    @AfterAllMethod
    public void afterMethod() {
        testString = testString + "afterMethod ";
    }
}
