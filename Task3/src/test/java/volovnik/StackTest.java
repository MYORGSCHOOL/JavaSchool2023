package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import volovnik.exceptions.StackIsEmptyException;

public class StackTest {

    @Test
    @DisplayName("Тест на добавление в стек")
    public void pushTest() {
        Stack stack = new Stack(10);
        stack.push("s1");
        stack.push("s2");
        stack.push("s3");
        Assertions.assertEquals("s3", stack.top());
    }

    @Test
    @DisplayName("Тест на удаление из стека")
    public void popTest() {
        Stack stack = new Stack(10);
        stack.push("s1");
        stack.push("s2");
        stack.push("s3");
        Object deleted = stack.pop();
        Assertions.assertEquals("s2", stack.top());
        Assertions.assertEquals("s3", deleted);
    }

    @Test
    @DisplayName("Тест на пустой стек")
    public void isEmptyTest() {
        Stack stack = new Stack(10);
        Assertions.assertTrue(stack.isEmpty());
        stack.push("s1");
        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на верхний элемент стека")
    public void topTest() {
        Stack stack = new Stack(10);
        Assertions.assertNull(stack.top());
        stack.push("s1");
        stack.push("s2");
        Assertions.assertEquals("s2", stack.top());
    }

    @Test
    @DisplayName("Тест на удаление из пустого стека")
    public void emptyQueueDeleteTest() {
        Stack stack = new Stack(5);
        Assertions.assertThrows(StackIsEmptyException.class, stack::pop);
    }

    @Test
    @DisplayName("Тест на получение всех элементов")
    public void getAllTest() {
        Stack stack = new Stack(5);
        stack.push("s1");
        stack.push("s2");
        stack.push("s3");
        stack.push("s4");
        stack.pop();
        Object[] all = stack.getAll();
        Object[] test = new Object[] {"s1", "s2", "s3", null, null};
        Assertions.assertArrayEquals(test, all);
    }
}
