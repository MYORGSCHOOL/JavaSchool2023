package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import volovnik.classes.TestCorrect;
import volovnik.classes.TestWithMultipleAfterAll;
import volovnik.classes.TestWithMultipleBeforeAll;

public class TesterTest {

    @Test
    @DisplayName("Успешный запуск тестов")
    public void testSuccessWithCorrectClass() {
        Assertions.assertDoesNotThrow(() -> TestRun.start(TestCorrect.class));
    }

    @Test
    @DisplayName("Тест на выброс исключения при наличии 2х и более @BeforeAllMethod")
    public void testWithMultipleBeforeAllThrowsException() {
        Assertions.assertThrows(RuntimeException.class, () -> TestRun.start(TestWithMultipleBeforeAll.class));
    }

    @Test
    @DisplayName("Тест на выброс исключения при наличии 2х и более @AfterAllMethod")
    public void testWithMultipleAfterAllThrowsException() {
        Assertions.assertThrows(RuntimeException.class, () -> TestRun.start(TestWithMultipleAfterAll.class));
    }
}
