package savinskiy;

import savinskiy.annotations.AfterAllMethod;
import savinskiy.annotations.BeforeAllMethod;
import savinskiy.annotations.TestMethod;

public class NormalClassWithAfterAndBefore {
    @BeforeAllMethod
    public static void beforeAll() {
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
