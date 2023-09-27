package volovnik.classes;

import volovnik.annotations.AfterAllMethod;
import volovnik.annotations.BeforeAllMethod;
import volovnik.annotations.TestMethod;

public class TestWithMultipleBeforeAll {

    @TestMethod(order = 1)
    public void someTest() {
        System.out.println("some test");
    }

    @TestMethod(order = 2)
    public void someTest2() {
        System.out.println("some test 2");
    }

    @BeforeAllMethod
    public void beforeAllTest() {
        System.out.println("before all test");
    }

    @BeforeAllMethod
    public void beforeAllTest2() {
        System.out.println("before all test 2");
    }

    @AfterAllMethod
    public void afterAllTest() {
        System.out.println("after all test");
    }
}
