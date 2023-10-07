package piryazev.testedClasses;

import piryazev.annotations.BeforeAllMethod;

public class TestTwoBeforeAllAnnotations {
    @BeforeAllMethod
    public void first() {
        System.out.println("i'm a first");
    }

    @BeforeAllMethod
    public void firstOrNot() {
        System.out.println("no, i'm a first!");
    }
}
