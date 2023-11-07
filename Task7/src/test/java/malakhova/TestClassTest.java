package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestClassTest {
    @Test
    @DisplayName("Проверка выполнения методов без аннотации TestMethod")
    public void testSuccessLaunchTestMethod() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TestClass.start(TestWithoutAnnotationTestMethod.class));
    }

    @Test
    @DisplayName("Проверка выполнения методов с неправильным количеством аннотаций BeforeAllMethod")
    public void testSuccessLaunchBeforeAllMethod() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TestClass.start(IncorrectNumberBeforeAllMethod.class));
    }

    @Test
    @DisplayName("Проверка выполнения методов с неправильным количеством аннотаций AfterAllMethod")
    public void testSuccessLaunchAfterAllMethod() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TestClass.start(IncorrectNumberAfterAllMethod.class));
    }

    @Test
    @DisplayName("Проверка выполнения методов")
    public void testSuccessLaunchMethod() {
        Assertions.assertTrue(LaunchMethod.launchMethod());
    }
}
