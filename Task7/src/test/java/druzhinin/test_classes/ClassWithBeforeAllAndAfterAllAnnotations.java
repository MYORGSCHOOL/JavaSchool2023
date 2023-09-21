package druzhinin.test_classes;

import druzhinin.annotations.AfterAllMethod;
import druzhinin.annotations.BeforeAllMethod;
import druzhinin.annotations.TestMethod;

import java.util.ArrayList;
import java.util.List;

public class ClassWithBeforeAllAndAfterAllAnnotations {
    private static final List<String> callSequence = new ArrayList<>();

    @TestMethod
    public void testMethod() {
        callSequence.add("test method");
    }

    @AfterAllMethod
    public void afterAllMethod() {
        callSequence.add("after all");
    }

    @BeforeAllMethod
    public void beforeAllMethod() {
        callSequence.add("before all");
    }


    public static List<String> getCallSequence() {
        return callSequence;
    }
}
