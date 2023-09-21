package druzhinin.test_classes;

import druzhinin.annotations.BeforeAllMethod;
import druzhinin.annotations.TestMethod;

public class ClassWithMultipleBeforeAllAnnotations {
    @BeforeAllMethod
    public void beforeAllMethod1() {
    }

    @BeforeAllMethod
    public void beforeAllMethod2() {
    }

    @TestMethod
    public void testMethod() {
    }
}
