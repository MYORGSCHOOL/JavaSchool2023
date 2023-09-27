package savinskiy;

import savinskiy.annotations.AfterAllMethod;
import savinskiy.annotations.BeforeAllMethod;
import savinskiy.annotations.TestMethod;

public class Task7 {
    public static void main(String[] args) {
        TestRunner.start(Test.class);
    }

    static class Test {
        @BeforeAllMethod
        public static void beforeAll() {
            System.out.println("=== Before All ===");
        }

        @TestMethod(order = 20)
        public void testMethod1() {
            System.out.println("Test method 4");
        }

        @TestMethod(order = 1)
        public void testMethod2() {
            System.out.println("Test method 1");
        }

        @TestMethod(order = 3)
        public void testMethod3() {
            System.out.println("Test method 3");
        }

        @TestMethod(order = 2)
        public void testMethod4() {
            System.out.println("Test method 2");
        }

        @AfterAllMethod
        public static void afterAll() {
            System.out.println("=== AfterAll method ===");
        }
    }
}
