package tsimmer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    @Test
    @DisplayName("Тест на добавление элемента")
    public void testSuccessPushOneElement() {
        Stack stack = new Stack();
        stack.push(1);
        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Тест на удаление элемента")
    public void testSuccessPopOneElement() {
        Stack stack = new Stack();
        Assertions.assertNull(stack.pop());
    }

    @Test
    @DisplayName("Тест на проверку пустого стека")
    public void testEmptyStackCheck() {
        Stack stack = new Stack();
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку полного стека")
    public void testFullStackCheck() {
        Stack stack = new Stack();
        Assertions.assertFalse(stack.isFull());
    }
}
