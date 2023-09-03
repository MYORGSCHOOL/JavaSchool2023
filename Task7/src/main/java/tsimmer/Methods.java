package tsimmer;

import tsimmer.annotation.AfterAllMethod;
import tsimmer.annotation.BeforeAllMethod;
import tsimmer.annotation.TestMethod;

/**
 * Класс с методами для которых применяются аннотации в качестве примера
 */
public class Methods {
    @AfterAllMethod
    public static void after() {
        System.out.println("After");
    }

    public static void NotTest() {
        System.out.println("NotTest");
    }

    @TestMethod(order = 2)
    public static void addTest2() {
        System.out.println("test 2");
    }

    @TestMethod(order = 1)
    public static void addTest1() {
        System.out.println("test 1");
    }

    @TestMethod(order = 3)
    public static void addTest3() {
        System.out.println("test 3");
    }

    @BeforeAllMethod
    public static void before() {
        System.out.println("Before");
    }

    @TestMethod(order = 3)
    public static void addTest4() {
        System.out.println("test 4");
    }


}
