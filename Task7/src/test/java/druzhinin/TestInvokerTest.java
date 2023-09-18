package druzhinin;

import druzhinin.exceptions.InvalidMethodsAmountException;
import druzhinin.exceptions.NoTestMethodsException;
import druzhinin.test_classes.ClassWithBeforeAllAndAfterAllAnnotations;
import druzhinin.test_classes.ClassWithMultipleAfterAllAnnotations;
import druzhinin.test_classes.ClassWithMultipleBeforeAllAnnotations;
import druzhinin.test_classes.ClassWithTestsWithOrders;
import druzhinin.test_classes.ClassWithoutTestMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestInvokerTest {
    @Test
    @DisplayName("Тест на получение исключения InvalidMethodsAmountException при вызове " +
            "метода start для класса с несколькими методами с аннотацией @BeforeAllMethod")
    void testInvalidMethodsAmountExceptionWhenClassHasMultipleBeforeAllAnnotations() {
        Assertions.assertThrows(InvalidMethodsAmountException.class,
                () -> TestInvoker.start(ClassWithMultipleBeforeAllAnnotations.class));
    }

    @Test
    @DisplayName("Тест на получение исключения InvalidMethodsAmountException при вызове " +
            "метода start для класса с несколькими методами с аннотацией @AfterAllMethod")
    void testInvalidMethodsAmountExceptionWhenClassHasMultipleAfterAllAnnotations() {
        Assertions.assertThrows(InvalidMethodsAmountException.class,
                () -> TestInvoker.start(ClassWithMultipleAfterAllAnnotations.class));
    }

    @Test
    @DisplayName("Тест на получение исключения InvalidMethodsAmountException при вызове " +
            "метода start для класса без методов с аннотацией @TestMethod")
    void testNoTestMethodsExceptionWhenClassHasNoTestMethods() {
        Assertions.assertThrows(NoTestMethodsException.class,
                () -> TestInvoker.start(ClassWithoutTestMethods.class));
    }

    @Test
    @DisplayName("Тест на успешный вызов тестов класса, в котором есть " +
            "по одному методу с аннотациями @BeforeAllMethod и @AfterAllMethod")
    void testSuccessfulTestInvokingForClassWithBeforeAllAndAfterAllAnnotations() {
        List<String> expectedSequence = List.of("before all", "test method", "after all");
        TestInvoker.start(ClassWithBeforeAllAndAfterAllAnnotations.class);

        Assertions.assertEquals(expectedSequence,
                ClassWithBeforeAllAndAfterAllAnnotations.getCallSequence());
    }

    @Test
    @DisplayName("Тест на успешный вызов тестов класса, в котором есть методы тестов с разными приоритетами")
    void testSuccessfulTestInvokingForClassWithTestsWithOrders() {
        List<String> expectedSequence = List.of("order 1", "order 2", "order 3", "order 4", "no order");
        TestInvoker.start(ClassWithTestsWithOrders.class);

        Assertions.assertEquals(expectedSequence, ClassWithTestsWithOrders.getCallSequence());
    }
}
