package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.EmptyStackException;

/**
 * Класс тестирования стека
 */
public class StackTest {

    @Test
    @DisplayName("Тест на успешную вставку элемента в стек")
    public void testSuccessPushElementStack() {
        Stack stack = new Stack();
        stack.push(5);
        stack.push("a");
        Assertions.assertEquals("a", stack.top());
    }

    @Test
    @DisplayName("Тест на успешное удаление элементов из стека")
    public void testSuccessPopAllElementsStack() {
        Stack stack = new Stack();
        int i;
        for (i = 0; i != 5; i++) {
            stack.push(i);
        }
        Assertions.assertEquals(--i, stack.pop());
        Assertions.assertEquals(--i, stack.pop());
        Assertions.assertEquals(--i, stack.pop());
        Assertions.assertEquals(--i, stack.pop());
        Assertions.assertEquals(--i, stack.pop());
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления элемента из стека")
    public void testFailPopElementStack() {
        Stack stack = new Stack();
        Assertions.assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        });
    }

    @Test
    @DisplayName("Тест на проверку пустоты стека - стек пустой")
    public void testCheckStackIsEmpty() {
        Stack stack = new Stack();
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку пустоты стека - стек не пустой")
    public void testCheckStackIsNotEmpty() {
        Stack stack = new Stack();
        stack.push(10);
        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на получение элемента вершины стека")
    public void testSuccessGetTopStack() {
        Stack stack = new Stack();
        stack.push("string");
        stack.push(true);
        stack.push(55);
        Assertions.assertEquals(55, stack.top());
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения элемента вершины стека")
    public void testFailGetTopStack() {
        Stack stack = new Stack();
        Assertions.assertThrows(EmptyStackException.class, () -> {
            stack.top();
        });
    }
}
