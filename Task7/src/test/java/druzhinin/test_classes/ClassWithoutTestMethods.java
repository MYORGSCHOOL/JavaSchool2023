package druzhinin.test_classes;

import druzhinin.annotations.AfterAllMethod;
import druzhinin.annotations.BeforeAllMethod;

public class ClassWithoutTestMethods {
    @BeforeAllMethod
    public void beforeAllMethod() {
    }

    @AfterAllMethod
    public void afterAllMethod() {
    }
}
