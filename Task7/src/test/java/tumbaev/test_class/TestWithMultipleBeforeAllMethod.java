package tumbaev.test_class;

import tumbaev.TestAssertions;
import tumbaev.annotation.BeforeAllMethod;

public class TestWithMultipleBeforeAllMethod {

    @BeforeAllMethod
    public void beforeAllMethodOne() {
        TestAssertions.doAssert(true, "This method shouldn't work, because of multiple @BeforeAllMethod");
    }

    @BeforeAllMethod
    public void beforeAllMethodTwo() {
        TestAssertions.doAssert(true, "This method shouldn't work, because of multiple @BeforeAllMethod");
    }
}
