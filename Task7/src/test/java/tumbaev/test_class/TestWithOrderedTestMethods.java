package tumbaev.test_class;

import tumbaev.TestAssertions;
import tumbaev.annotation.TestMethod;

public class TestWithOrderedTestMethods {

    public static final String FIRST = "I am first";
    public static final String SECOND = "I am second";
    public static final String THIRD = "I am third";

    public TestWithOrderedTestMethods() {

    }

    @TestMethod(order = 1)
    public void testOne() {
        TestAssertions.doAssert(false, FIRST);
    }

    @TestMethod(order = 2)
    public void testTwo() {
        TestAssertions.doAssert(false, SECOND);
    }

    @TestMethod()
    public void testThree() {
        TestAssertions.doAssert(false, THIRD);
    }
}
