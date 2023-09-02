package tumbaev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tumbaev.exception.NoTestMethodsException;
import tumbaev.exception.NotUniqueAnnotationException;
import tumbaev.test_class.TestWithBeforeAndAfterAllMethods;
import tumbaev.test_class.TestWithMultipleAfterAllMethod;
import tumbaev.test_class.TestWithMultipleBeforeAllMethod;
import tumbaev.test_class.TestWithNoMethods;
import tumbaev.test_class.TestWithOrderedTestMethods;
import tumbaev.test_class.TestWithSuccessfulTestMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tumbaev.Tester.start;

class TesterTest {

    @Test
    @DisplayName("Test class with no methods should throw exception")
    void testClassWithNoMethodsShouldThrowException() {
        assertThrows(NoTestMethodsException.class, () -> start(TestWithNoMethods.class));
    }

    @Test
    @DisplayName("Test class with multiple @BeforeAllMethod should throw exception")
    void testClassWithMultipleBeforeAllMethodShouldThrowException() {
        assertThrows(NotUniqueAnnotationException.class, () -> start(TestWithMultipleBeforeAllMethod.class));
    }

    @Test
    @DisplayName("Test class with multiple @AfterAllMethod should throw exception")
    void testClassWithMultipleAfterAllMethodShouldThrowException() {
        assertThrows(NotUniqueAnnotationException.class, () -> start(TestWithMultipleAfterAllMethod.class));
    }

    @Test
    @DisplayName("Test class with one successful test method should succeed")
    void testClassWithOneSuccessfulTestMethodShouldSucceed() {
        List<TestResult> testResults = Tester.start(TestWithSuccessfulTestMethod.class);
        assertEquals(1, testResults.size());
        assertTrue(testResults.get(0).isSuccessful());
    }

    @Test
    @DisplayName("Test methods should run in the specified order")
    void testMethodsShouldRunInTheSpecifiedOrder() {
        List<TestResult> testResults = Tester.start(TestWithOrderedTestMethods.class);
        assertEquals(3, testResults.size());
        assertEquals(TestWithOrderedTestMethods.FIRST, testResults.get(0).getMessage().get());
        assertEquals(TestWithOrderedTestMethods.SECOND, testResults.get(1).getMessage().get());
        assertEquals(TestWithOrderedTestMethods.THIRD, testResults.get(2).getMessage().get());
    }

    @Test
    @DisplayName("BeforeAllMethod should run before all tests")
    void testBeforeAllMethodShouldRunBeforeAllTests() {
        List<TestResult> testResults = Tester.start(TestWithBeforeAndAfterAllMethods.class);
        assertEquals(1, testResults.size());
        assertEquals(TestWithBeforeAndAfterAllMethods.BEFORE_ALL, TestWithBeforeAndAfterAllMethods.workingQueue.poll());
        assertEquals(TestWithBeforeAndAfterAllMethods.TEST, TestWithBeforeAndAfterAllMethods.workingQueue.poll());
        assertEquals(TestWithBeforeAndAfterAllMethods.AFTER_ALL, TestWithBeforeAndAfterAllMethods.workingQueue.poll());
    }
}