package savinskiy;

import savinskiy.annotations.AfterAllMethod;
import savinskiy.annotations.TestMethod;

public class ClassAfterAllTwoTimes {

    @TestMethod
    public void testMethod() {
        System.out.println("Test1");
    }

    @AfterAllMethod
    public static void afterAll() {
        System.out.println("AfterAll");
    }

    @AfterAllMethod
    public static void afterAll2() {
        System.out.println("AfterAll2");
    }
}
