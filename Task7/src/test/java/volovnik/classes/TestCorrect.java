package volovnik.classes;

import volovnik.annotations.AfterAllMethod;
import volovnik.annotations.BeforeAllMethod;
import volovnik.annotations.TestMethod;

/**
 * Тестируемый класс
 */
public class TestCorrect {

    @TestMethod
    public void someTestWithNoOrder() {
        System.out.println("some test with no order");
    }

    @TestMethod
    public void someTestWithNoOrder2() {
        System.out.println("some test with no order 2");
    }

    @TestMethod(order = 1)
    public void someTest() {
        System.out.println("some test with order 1");
    }

    @TestMethod(order = 2)
    public void someTest2() {
        System.out.println("some test with order 2");
    }

    @BeforeAllMethod
    public void beforeAllTest() {
        System.out.println("before all test");
    }

    @AfterAllMethod
    public void afterAllTest() {
        System.out.println("after all test");
    }
}
