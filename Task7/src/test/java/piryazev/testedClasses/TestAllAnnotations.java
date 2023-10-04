package piryazev.testedClasses;

import piryazev.annotations.AfterAllMethod;
import piryazev.annotations.BeforeAllMethod;
import piryazev.annotations.TestMethod;

import java.util.ArrayList;
import java.util.List;

public class TestAllAnnotations {

    public static List<String> allAnnotationsListTest = new ArrayList<>();

    @BeforeAllMethod
    public void before() {
        allAnnotationsListTest.add("I'm a first");
    }

    @TestMethod(order = 1)
    public void testOrderOne() {
        allAnnotationsListTest.add("maybe i'm second or not?");
    }

    @TestMethod(order = 2)
    public void testOrderTwo() {
        allAnnotationsListTest.add("probably i'm third");
    }

    @TestMethod(order = 3)
    public void testOrderThree() {
        allAnnotationsListTest.add("probably i'm fourth???");
    }

    @AfterAllMethod
    public void after() {
        allAnnotationsListTest.add("screw this program up!");
    }
}
