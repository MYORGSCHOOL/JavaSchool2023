package tumbaev.test_class;

import tumbaev.TestAssertions;
import tumbaev.annotation.BeforeAllMethod;

public class TestWithMultipleAfterAllMethod {
    @BeforeAllMethod
    public void afterAllMethodOne() {
        TestAssertions.doAssert(true, "This method shouldn't work, because of multiple @BeforeAllMethod");
    }

    @BeforeAllMethod
    public void afterAllMethodTwo() {
        TestAssertions.doAssert(true, "This method shouldn't work, because of multiple @BeforeAllMethod");
    }
}
