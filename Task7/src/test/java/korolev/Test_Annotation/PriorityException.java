package korolev.Test_Annotation;

import korolev.Annotation.TestMethod;

public class PriorityException {
    @TestMethod(order = 2)
    public void testOne() {
        System.out.println("Go test Priority!");
    }

    @TestMethod(order = 1)
    public void testTwo() {
        System.out.println("Go test Priority!");        
    }

    @TestMethod(order = -1)
    public void testThree() {
        System.out.println("Go test Priority!");
    }
}
