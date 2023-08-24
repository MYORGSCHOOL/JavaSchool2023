package sevostyanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    @Test
    @DisplayName("Тест на добавление элемента")
    public void testPush() {
        Stack stack = new Stack(3);
        stack.push(1);
        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Тест на удаление элемента")
    public void testPop() {
        Stack stack = new Stack(3);
        Assertions.assertNull(stack.pop());
    }

    @Test
    @DisplayName("Тест на проверку пустого стека")
    public void testEmptyStack() {
        Stack stack = new Stack(3);
        Assertions.assertTrue(stack.isEmpty());
    }
}
