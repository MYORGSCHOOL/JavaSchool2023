package korolev.Test_Annotation;

import korolev.Annotation.AfterAllMethod;

public class BruteForceAfterAll {
    @AfterAllMethod
    public void testOne() {
        System.out.println("Go test AfterAll!");
    }

    @AfterAllMethod
    public void testTwo() {
        System.out.println("Go test AfterAll!");
    }
}
