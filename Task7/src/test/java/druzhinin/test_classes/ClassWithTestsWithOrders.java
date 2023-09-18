package druzhinin.test_classes;

import druzhinin.annotations.TestMethod;

import java.util.ArrayList;
import java.util.List;

public class ClassWithTestsWithOrders {
    private static final List<String> callSequence = new ArrayList<>();

    @TestMethod(order = 3)
    public void testMethodOrder3() {
        callSequence.add("order 3");
    }

    @TestMethod(order = 2)
    public void testMethodOrder2() {
        callSequence.add("order 2");
    }

    @TestMethod(order = 1)
    public void testMethodOrder1() {
        callSequence.add("order 1");
    }

    @TestMethod(order = 4)
    public void testMethodOrder4() {
        callSequence.add("order 4");
    }

    @TestMethod
    public void testMethodNoOrder() {
        callSequence.add("no order");
    }

    public static List<String> getCallSequence() {
        return callSequence;
    }
}
