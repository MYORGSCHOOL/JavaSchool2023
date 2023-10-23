package karmanchikova;

import karmanchikova.TestClass.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.AnnotationFormatError;

public class Task7Test {
    @Test
    @DisplayName("запуск теста для класса c аннотацией BeforeAllMethod")
    public void testUnsuccessInvokeTestRunnerClassWithTwoBeforeAllAnnotations() {
        Class<TestClassTwoBefore> testClass = TestClassTwoBefore.class;
        Assertions.assertThrows(AnnotationFormatError.class, () -> Task7.start(testClass));
    }

    @Test
    @DisplayName("запуск теста для класса c аннотацией AfterAllMethod")
    public void testUnsuccessInvokeTestRunnerClassWithTwoAfterAllAnnotations() {
        Class<TestClassTwoAfter> testClass = TestClassTwoAfter.class;
        Assertions.assertThrows(AnnotationFormatError.class, () -> Task7.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса с аннтотациями BeforeAll, AfterAll и Test")
    public void testSuccessInvokeTestRunnerClassWithBeforeAllAfterAllAndTestAnnotations() {
        Class<TestClass> testClass = TestClass.class;
        Assertions.assertDoesNotThrow(() -> Task7.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов с методами Test")
    public void testSuccessInvokeTestRunnerClassWithTestAnnotationsCorrectOrder() {
        Class<TestClassNoBeforeNoAfter> testClass = TestClassNoBeforeNoAfter.class;
        Assertions.assertDoesNotThrow(() -> Task7.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса без методов")
    public void testSuccessInvokeTestRunnerClassWithNoMethods() {
        Class<TestClassIsEmpty> testClass = TestClassIsEmpty.class;
        Assertions.assertDoesNotThrow(() -> Task7.start(testClass));
    }

    @Test
    @DisplayName("запуск тестов для класса с методами Test с одинаковыми приоритетами")
    public void testSuccessInvokeTestRunnerClassWithTestAnnotationEquelPriorities() {
        Class<TestClassEqualPriority> testClass = TestClassEqualPriority.class;
        Assertions.assertDoesNotThrow(() -> Task7.start(testClass));
    }
}