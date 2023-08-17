package kashuba;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    @DisplayName("Проверка toString когда в стеке есть элементы")
    void testCheckingToStringWhenThereAreElementsInTheStack() {
        Stack stack = new Stack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals("[1, 2, 3, 4]", stack.toString());
    }

    @Test
    @DisplayName("Проверка toString когда в стеке нет элементов")
    void testCheckingToStringWhenThereAreNoElementsInTheStack() {
        Stack stack = new Stack(4);

        assertEquals("[]", stack.toString());
    }

    @Test
    @DisplayName("Проверка на то, что метод top ничего не вернет, если ничего не положено")
    void testCheckingThatTheTopMethodWillNotReturnAnythingIfNothingIsSet() {
        Stack stack = new Stack(4);

        assertThrows(NoSuchElementException.class, stack::top);
    }

    @Test
    @DisplayName("Проверка на то, что работает pop")
    void testCheckingThatPopIsWorking() {
        Stack stack = new Stack(4);

        stack.push(1);
        stack.push(2);
        stack.pop();

        assertEquals("[1]", stack.toString());
    }

    @Test
    @DisplayName("Проверка что сохраняется структура данных стека")
    void testCheckingThatTheStackDataStructureIsPreserved() {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        stack.push(5);

        assertEquals("[1, 2, 3, 5]", stack.toString());
    }


    @Test
    @DisplayName("Проверка корректной работы isEmpty")
    void testCheckingTheCorrectOperationOfIsEmpty() {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Проверка заполненности стека")
    void testCheckingWhetherTheStackIsFull() {
        Stack stack = new Stack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertTrue(stack.isFull());
    }

    @Test
    @DisplayName("Проверка корректной работы pop")
    void testCheckingTheCorrectOperationOfPop() {
        Stack stack = new Stack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
    }
}
