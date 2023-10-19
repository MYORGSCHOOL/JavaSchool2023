package kashuba;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmthTest {
    @Test
    @DisplayName("Проверка порядка вызова при выставленных аннотациях")
    public void testCheckingTheCallOrderWhenAnnotationsAreSet() {
        assertArrayEquals(new Object[]{0, 1, 3, 2, 4}, AnnotationRunner.runningTestMethods(Smth.class).toArray());
    }

    @Test
    @DisplayName("Проверка порядка вызова при не выставленных аннотациях")
    public void testCheckingTheCallOrderWhenAnnotationsAreNotSet() {
        assertArrayEquals(new Object[]{2, 1, 0}, AnnotationRunner.runningTestMethods(Smth1.class).toArray());
    }

    @Test
    @DisplayName("Проверка при отсутствии аннотаций в классе")
    public void testCheckingIfThereAreNoAnnotationsInTheClass() {
        assertThrows(AnnotationException.class, () -> AnnotationRunner.runningTestMethods(Smth2.class));
    }

    @Test
    @DisplayName("Проверка при @BeforeAllMethod > 1")
    public void testCheckingWhenBeforeAllMethodIsGreater1() {
        assertThrows(AnnotationException.class, () -> AnnotationRunner.runningTestMethods(Smth3.class));
    }

    @Test
    @DisplayName("Проверка при @AfterAllMethod > 1")
    public void testCheckingWhenAfterAllMethodIsGreater1() {
        assertThrows(AnnotationException.class, () -> AnnotationRunner.runningTestMethods(Smth4.class));
    }

    @Test
    @DisplayName("Проверка при некорректном order")
    public void testCheckingForAnIncorrectOrder() {
        assertThrows(AnnotationException.class, () -> AnnotationRunner.runningTestMethods(Smth5.class));
    }
}