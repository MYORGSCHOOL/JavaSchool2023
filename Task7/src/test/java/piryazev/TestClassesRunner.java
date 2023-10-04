package piryazev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import piryazev.exception.NoAnnotationException;
import piryazev.testedClasses.TestAllAnnotations;
import piryazev.testedClasses.TestNoAnnotations;
import piryazev.testedClasses.TestOrderAnnotations;
import piryazev.testedClasses.TestTwoBeforeAllAnnotations;

import java.util.Arrays;
import java.util.List;

public class TestClassesRunner {
    @Test
    @DisplayName("Testing standard class with all annotations")
    public void testClassWithAllAnnotations() throws Exception {
        List<String> expected = Arrays.asList("I'm a first",
                "maybe i'm second or not?", "probably i'm third", "probably i'm fourth???", "screw this program up!");
        TestRunner.start(TestAllAnnotations.class);
        Assertions.assertEquals(expected, TestAllAnnotations.allAnnotationsListTest);
    }

    @Test
    @DisplayName("Testing class with two BeforeAll annotation")
    public void testClassWithTwoBeforeAllAnnotations() {
        Assertions.assertThrows(Exception.class, () -> TestRunner.start(TestTwoBeforeAllAnnotations.class));
    }

    @Test
    @DisplayName("Testing class with two AfterAll annotation")
    public void testClassWithTwoAfterAllAnnotations() {
        Assertions.assertThrows(Exception.class, () -> TestRunner.start(TestTwoBeforeAllAnnotations.class));
    }

    @Test
    @DisplayName("Testing class with order annotations")
    public void testClassWithOrderTests() throws Exception {
        List<String> expected = Arrays.asList("First default annotation",
                "Second default annotation", "order one", "order Four", "order ten", "order ThirtyThree");
        TestRunner.start(TestOrderAnnotations.class);
        Assertions.assertEquals(expected, TestOrderAnnotations.orderTestList);
    }

    @Test
    @DisplayName("Testing empty test class")
    public void testNoAnnotationsClass() {
        Assertions.assertThrows(NoAnnotationException.class, () -> TestRunner.start(TestNoAnnotations.class));
    }
}
