package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yanko.testClasses.*;

import java.lang.annotation.AnnotationFormatError;

public class Task7Test {
    @Test
    @DisplayName("запуск тестов для класса, у которого несколько методов помечены аннотацией BeforeAllMethod")
    public void testUnsuccessInvokeTestRunnerClassWithTwoBeforeAllAnnotations() {
        Class<TestClassTwoBefore> testClass = TestClassTwoBefore.class;
        Assertions.assertThrows(AnnotationFormatError.class, () -> TestRunner.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса, у которого несколько методов помечены аннотацией AfterAllMethod")
    public void testUnsuccessInvokeTestRunnerClassWithTwoAfterAllAnnotations() {
        Class<TestClassTwoAfter> testClass = TestClassTwoAfter.class;
        Assertions.assertThrows(AnnotationFormatError.class, () -> TestRunner.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса с методами BeforeAll, AfterAll и Test с разными приоритетами")
    public void testSuccessInvokeTestRunnerClassWithBeforeAllAfterAllAndTestAnnotations() {
        Class<TestClass> testClass = TestClass.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса с методами Test в корректной последовательности")
    public void testSuccessInvokeTestRunnerClassWithTestAnnotationsCorrectOrder() {
        Class<TestClassNoBeforeNoAfter> testClass = TestClassNoBeforeNoAfter.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса без методов")
    public void testSuccessInvokeTestRunnerClassWithNoMethods() {
        Class<TestClassEmpty> testClass = TestClassEmpty.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса с методами Test с одинаковыми приоритетами")
    public void testSuccessInvokeTestRunnerClassWithTestAnnotationEquelPriorities() {
        Class<TestClassEqualPriority> testClass = TestClassEqualPriority.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(testClass));
    }
}
