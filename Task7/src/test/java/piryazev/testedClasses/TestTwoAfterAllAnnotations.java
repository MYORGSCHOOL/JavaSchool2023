package piryazev.testedClasses;

import piryazev.annotations.AfterAllMethod;

public class TestTwoAfterAllAnnotations {
    @AfterAllMethod
    public void first() {
        System.out.println("i'm a first");
    }

    @AfterAllMethod
    public void firstOrNot() {
        System.out.println("no, i'm a first!");
    }
}
