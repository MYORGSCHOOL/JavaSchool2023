package savinskiy;

import savinskiy.annotations.AfterAllMethod;
import savinskiy.annotations.BeforeAllMethod;
import savinskiy.annotations.TestMethod;

public class ClassBeforeAllTwoTimes {

    @BeforeAllMethod
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @BeforeAllMethod
    public static void beforeAll2() {
        System.out.println("Before All");
    }

    @TestMethod
    public void testMethod() {
        System.out.println("Test1");
    }

    @AfterAllMethod
    public static void afterAll() {
        System.out.println("AfterAll");
    }
}
