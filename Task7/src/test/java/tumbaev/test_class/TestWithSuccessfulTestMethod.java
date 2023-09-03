package tumbaev.test_class;

import tumbaev.TestAssertions;
import tumbaev.annotation.TestMethod;

public class TestWithSuccessfulTestMethod {

    @TestMethod
    public void test() {
        TestAssertions.doAssert(true, "This method should work");
    }
}
