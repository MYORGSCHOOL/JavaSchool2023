package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import skrebkov.classForTesting.BadTestingClass;
import skrebkov.classForTesting.GoodTestingClass;
import skrebkov.classForTesting.TwoAfterAllClass;
import skrebkov.classForTesting.TwoBeforeAllClass;
import skrebkov.exception.TestAnnotationError;

public class TestingTest {
    @Test
    @DisplayName("Успешное тестирование класса без ошибок")
    public void successTestingGoodClassTest() {
        var goodClass = new GoodTestingClass();
        Assertions.assertDoesNotThrow(() -> Testing.runTest(goodClass));
    }

    @Test
    @DisplayName("Успешное тестирование класса с ошибками")
    public void successTestingBadClassTest() {
        var badClass = new BadTestingClass();
        Assertions.assertDoesNotThrow(() -> Testing.runTest(badClass));
    }

    @Test
    @DisplayName("Тестирование ошибок в формате аннотаций")
    public void throwAnnotationFormatErrorTest() {
        var twoAfterAll = new TwoAfterAllClass();
        var twoBeforeAll = new TwoBeforeAllClass();
        Assertions.assertThrows(TestAnnotationError.class, () -> Testing.runTest(twoBeforeAll));
        Assertions.assertThrows(TestAnnotationError.class, () -> Testing.runTest(twoAfterAll));
    }
}
