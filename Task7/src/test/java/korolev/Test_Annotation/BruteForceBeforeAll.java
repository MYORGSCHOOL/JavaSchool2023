package korolev.Test_Annotation;

import korolev.Annotation.AfterAllMethod;
import korolev.Annotation.BeforeAllMethod;
import korolev.Annotation.TestMethod;

public class BruteForceBeforeAll {
    @BeforeAllMethod
    public void beforeAll() {
        System.out.println("This beforeAll method!");
    }

    @BeforeAllMethod
    public void beforeAllTwo() {
        System.out.println("This is two beforeAll!");
    }

    @TestMethod()
    public void testMethodOne() {
        System.out.println("This test Method one!");
    }

    @TestMethod()
    public void testMethodTwo() {
        System.out.println("This test Method two!");
    }

    @AfterAllMethod
    public void afterAll() {
        System.out.println("This afterAll method!");
    }
}
