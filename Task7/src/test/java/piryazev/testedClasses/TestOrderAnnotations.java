package piryazev.testedClasses;

import org.junit.jupiter.api.Test;
import piryazev.annotations.TestMethod;

import java.util.ArrayList;
import java.util.List;

public class TestOrderAnnotations {

    public static List<String> orderTestList = new ArrayList<>();

    @TestMethod
    public void zeroAnnotation() {
        System.out.println("First default annotation");
        orderTestList.add("First default annotation");
    }

    @TestMethod(order = 1)
    public void firstAnnotation() {
        System.out.println("Second default annotation");
        orderTestList.add("Second default annotation");
    }

    @TestMethod(order = 10)
    public void orderTen() {
        System.out.println("order ten");
        orderTestList.add("order ten");
    }

    @TestMethod(order = 4)
    public void orderFour() {
        System.out.println("order Four");
        orderTestList.add("order Four");
    }

    @TestMethod(order = 33)
    public void orderThirtyThree() {
        System.out.println("order ThirtyThree");
        orderTestList.add("order ThirtyThree");
    }

    @TestMethod(order = 2)
    public void orderTwo() {
        System.out.println("order one");
        orderTestList.add("order one");
    }
}
