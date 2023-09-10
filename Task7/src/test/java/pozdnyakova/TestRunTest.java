package pozdnyakova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pozdnyakova.exception.ConflictingAnnotationsException;
import pozdnyakova.exception.InvalidNumberMethodsException;
import pozdnyakova.testClasses.TestClassAllTestAnnotations;
import pozdnyakova.testClasses.TestClassConflictingAnnotations;
import pozdnyakova.testClasses.TestClassInvalidNumberMethods;
import pozdnyakova.testClasses.TestClassNoTestMethod;
import pozdnyakova.testClasses.TestClassOnlyTestMethods;

public class TestRunTest {
    @Test
    @DisplayName("Тестирование неудачного запуска теста - нет методов с аннотацией @TestMethod")
    public void testFailNoTestMethod() {
        Assertions.assertThrows(InvalidNumberMethodsException.class, () -> TestRun.start(TestClassNoTestMethod.class));
    }

    @Test
    @DisplayName("Тестирование неудачного запуска теста - к одному методу относится несколько созданных аннотаций")
    public void testFailConflictingAnnotations() {
        Assertions.assertThrows(ConflictingAnnotationsException.class, () -> TestRun.start(TestClassConflictingAnnotations.class));
    }

    @Test
    @DisplayName("Тестирование неудачного запуска теста - @BeforeAllMethod не в единственном экземпляре")
    public void testFailInvalidNumberBeforeAllMethods() {
        Assertions.assertThrows(InvalidNumberMethodsException.class, () -> TestRun.start(TestClassInvalidNumberMethods.class));
    }

    @Test
    @DisplayName("Тестирование удачного запуска теста - только методы @TestMethod")
    public void testSuccessOnlyTestMethods() {
        Assertions.assertDoesNotThrow(() -> TestRun.start(TestClassOnlyTestMethods.class));
        Assertions.assertEquals("firstMethod secondMethod thirdMethod ", TestClassOnlyTestMethods.getTestString());
    }

    @Test
    @DisplayName("Тестирование удачного запуска теста - методы с использованием всех созданных аннотаций")
    public void testSuccessAllTestAnnotations() {
        Assertions.assertDoesNotThrow(() -> TestRun.start(TestClassAllTestAnnotations.class));
        Assertions.assertEquals("beforeMethod firstMethod secondMethod thirdMethod afterMethod ", TestClassAllTestAnnotations.getTestString());
    }
}
