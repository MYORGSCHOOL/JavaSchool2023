package karmanchikova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class StackTest {
    @Test
    @DisplayName("Тест на добавление элемента в стек")
    public void testSuccessEnqueueOneElement() {
        Stack stack = new Stack(3);
        stack.push(1);
        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Тест на удаление элемента из стека")
    public void testExceptionDeleteFromQueue() {
        Stack stack = new Stack(3);
        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("Тест на проверку пустого стека")
    public void testEmptyQueueCheck() {
        Stack stack = new Stack(3);
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку заполненого стека")
    public void testFullQueueCheck() {
        Stack stack = new Stack(3);
        Assertions.assertFalse(stack.isFull());
    }
}
