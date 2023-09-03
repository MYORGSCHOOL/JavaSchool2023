package tumbaev.test_class;

import tumbaev.TestAssertions;
import tumbaev.annotation.AfterAllMethod;
import tumbaev.annotation.BeforeAllMethod;
import tumbaev.annotation.TestMethod;

import java.util.ArrayDeque;
import java.util.Queue;

public class TestWithBeforeAndAfterAllMethods {

    public static final String BEFORE_ALL = "before all";
    public static final String TEST = "test";
    public static final String AFTER_ALL = "after all";
    public static Queue<String> workingQueue = new ArrayDeque<>(3);

    @BeforeAllMethod
    public void beforeAllMethod() {
        workingQueue.add(BEFORE_ALL);
    }

    @TestMethod
    public void test() {
        workingQueue.add(TEST);
        TestAssertions.doAssert(false, "should fail");
    }

    @AfterAllMethod
    public void afterAllMethod() {
        workingQueue.add(AFTER_ALL);
    }
}
