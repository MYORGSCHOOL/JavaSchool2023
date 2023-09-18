package druzhinin.test_classes;

import druzhinin.annotations.AfterAllMethod;
import druzhinin.annotations.TestMethod;

public class ClassWithMultipleAfterAllAnnotations {
    @TestMethod
    public void testMethod() {
    }

    @AfterAllMethod
    public void afterAllMethod1() {
    }

    @AfterAllMethod
    public void afterAllMethod2() {
    }
}
