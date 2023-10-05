package savinskiy;

import savinskiy.annotations.TestMethod;

public class NormalClass {
    @TestMethod(order = 5)
    public void testMethod5() {
        System.out.println("Test #5");
    }

    @TestMethod(order = 3)
    public void testMethod3() {
        System.out.println("Test #3");
    }

    @TestMethod(order = 4)
    public void testMethod4() {
        System.out.println("Test #4");
    }

    @TestMethod(order = 1)
    public void testMethod1() {
        System.out.println("Test #1");
    }

    @TestMethod
    public void testMethod() {
        System.out.println("Test1");
    }
}
