package proskurina;

import proskurina.annotations.AfterAllMethod;
import proskurina.annotations.BeforeAllMethod;
import proskurina.annotations.Order;
import proskurina.annotations.TestMethod;

public class Task7 {
    public static void main(String[] args) {
        TestRunner.start(Test.class);
    }
}

class Test {
    
    @BeforeAllMethod
    void beforeAll() {
        System.out.println("beforeAll");
    }
    
    @Order(1)
    @TestMethod
    void testOne() {
        System.out.println("testOne");
    }
    
    @Order(2)
    @TestMethod
    void testTwo() {
        System.out.println("testTwo");
    }
    
    @AfterAllMethod
    void afterAll() {
        System.out.println("afterAll");
    }
    
}