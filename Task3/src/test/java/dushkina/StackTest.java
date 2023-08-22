package dushkina;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Класс - тестирование методов класса Stack
 */
public class StackTest {
    @Test
    @DisplayName("Тест на добавление элемента сверху переполненного стека")
    public void insertingAnElementOnTopOfAnOverflowingStack() {
        assertDoesNotThrow(() -> {
            Stack stack = new Stack(8);
            for (int i = 0; i < 12; i++) {
                stack.push("book" + (i + 1));
            }
        });
    }

    @Test
    @DisplayName("Тест на добавление элемента сверху стека")
    void pushTest() {
        Stack stack = new Stack(3);
        stack.push("book1");
        stack.push("book2");
        Object[] arrayTest = {"book1", "book2"};
        assertArrayEquals(arrayTest, stack.getAll());
    }

    @Test
    @DisplayName("Тест на возвращение верхнего элемента стека(метод top())")
    public void topTest() {
        Stack stack = new Stack(3);
        stack.push("book1");
        stack.push("book2");
        assertEquals("book2", stack.top());
    }

    @Test
    @DisplayName("Тест на возвращение верхнего элемента (метод top()) из пустого стека")
    public void theTopElementWhenTheStackIsEmpty() {
        Stack stack = new Stack(3);
        assertEquals(null, stack.top());
    }

    @Test
    @DisplayName("Тест метода isEmpty(), когда стек пуст")
    public void isEmptyTest() {
        Stack stack = new Stack(3);
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест метода isEmpty(), когда очередь не пуста")
    public void theQueueIsNotEmptyTest() {
        Stack stack = new Stack(3);
        stack.push("1");
        stack.push("2");
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Возвращение верхнего элемента и удаление его из стека")
    public void popTest() {
        Stack stack = new Stack(3);
        stack.push("1");
        stack.push("2");
        assertEquals("2", stack.pop());
        Object[] arrayTest = {"1"};
        assertArrayEquals(arrayTest, stack.getAll());
    }

    @Test
    @DisplayName("Возвращение верхнего элемента и удаление его из пустого стека")
    public void withdrawFromAnEmptyQueueTest() {
        Stack stack = new Stack(3);
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }
}
