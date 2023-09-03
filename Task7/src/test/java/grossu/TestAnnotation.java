package grossu;


import grossu.testexception.UncorrectedUseAnnotation;
import grossu.testfortest.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования работы аннотаций из пакета {@link grossu.testannotation}
 */
public class TestAnnotation {
    @Test
    @DisplayName("Checking that code works correctly when in test class have all annotations")
    public void testDoesNotThrowWhenClassHaveAllAnnotations() {
        Assertions.assertDoesNotThrow(() -> TestRunner.start(ClassWithAllAnnotations.class.getName()));
    }

    @Test
    @DisplayName("Checking that code works correctly when in test class have annotation - BeforeAllMethod")
    public void testDoesNotThrowWhenClassHaveBeforeAllMethod() {
        Assertions.assertDoesNotThrow(() -> TestRunner.start(ClassWithBeforeAllMethod.class.getName()));
    }

    @Test
    @DisplayName("Checking that code works correctly when in test class have annotation - AfterAllMethod")
    public void testDoesNotThrowWhenClassHaveAfterAllMethod() {
        Assertions.assertDoesNotThrow(() -> TestRunner.start(ClassAfterAllMethod.class.getName()));
    }

    @Test
    @DisplayName("Checking that code works correctly when in test class have annotation - TestMethod no order")
    public void testDoesNotThrowWhenClassHaveTestMethodNoOrder() {
        Assertions.assertDoesNotThrow(() -> TestRunner.start(ClassWithTestMethodNoOrder.class.getName()));
    }

    @Test
    @DisplayName("Checking that code works correctly when in test class have annotation - TestMethod with order ")
    public void testDoesNotThrowWhenClassHaveTestMethodWithOrder() {
        Assertions.assertDoesNotThrow(() -> TestRunner.start(ClassWithTestMethodOrder.class.getName()));
    }

    @Test
    @DisplayName("Checking that code works correctly when in test class have annotation - TestMethod with equal order")
    public void testDoesNotThrowWhenClassHaveTestMethodWithEqualOrder() {
        Assertions.assertDoesNotThrow(() -> TestRunner.start(ClassWithTestMethodEqualOrder.class.getName()));
    }
    @Test
    @DisplayName("Checking that code works correctly when in test class does not have annotation - TestMethod")
    public void testGetThrowWhenClassDoesNotHaveTestMethod() {
        Assertions.assertThrows(UncorrectedUseAnnotation.class, ()->TestRunner.start(ClassWithoutTestMethod.class.getName()));
    }
    @Test
    @DisplayName("Checking that code works correctly when in test class have two annotation - BeforeAllMethod")
    public void testGetThrowWhenClassHaveTwoBeforeAllMethod() {
        Assertions.assertThrows(UncorrectedUseAnnotation.class, ()->TestRunner.start(ClassWithTwoBeforeAllMethod.class.getName()));
    }
    @Test
    @DisplayName("Checking that code works correctly when in test class have two annotation - AfterAllMethod")
    public void testGetThrowWhenClassHaveTwoAfterAllMethod() {
        Assertions.assertThrows(UncorrectedUseAnnotation.class, ()->TestRunner.start(ClassWithTwoAfterAllMethod.class.getName()));
    }

}
