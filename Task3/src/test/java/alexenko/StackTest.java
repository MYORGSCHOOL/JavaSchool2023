package alexenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {

    private static final int TEST_SIZE_STACK = 5;

    @Test
    @DisplayName("Вставка элемента в не полный стек")
    public void testPushObjectWhenStackNotFull() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        stack.push(1);

        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Удаление верхнего элемента из не пустого стека")
    public void testPopObjectWhenStackNotEmpty() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        stack.push(1);

        Assertions.assertEquals(1, stack.pop());
    }

    @Test
    @DisplayName("Удаление верхнего элемента из не пустого стека")
    public void testPopObjectWhenStackIsEmpty() {
        Stack stack = new Stack(TEST_SIZE_STACK);

        Assertions.assertThrowsExactly(RuntimeException.class, stack::pop);
    }

    @Test
    @DisplayName("Проверка пустого стека на пустоту")
    public void testStackIsEmptyTrue() {
        Stack stack = new Stack(TEST_SIZE_STACK);

        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Проверка не пустого стека на пустоту")
    public void testStackIsEmptyFalse() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        stack.push(1);

        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Проверка пустого стека на заполненность")
    public void testStackIsFullFalse() {
        Stack stack = new Stack(TEST_SIZE_STACK);

        Assertions.assertFalse(stack.isFull());
    }

    @Test
    @DisplayName("Проверка полного стека на заполненность")
    public void testStackIsFullTrue() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        Assertions.assertTrue(stack.isFull());
    }

    @Test
    @DisplayName("Получение верхнего элемента из не пустого стека")
    public void testGetTopObjectWhenStackNotEmpty() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        stack.push(1);

        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Получение верхнего элемента из пустого стека")
    public void testGetTopObjectWhenStackIsEmpty() {
        Stack stack = new Stack(TEST_SIZE_STACK);

        Assertions.assertNull(stack.top());
    }

    @Test
    @DisplayName("Получить все элементы пустого стека")
    public void testGetAllObjectsWhenStackIsEmpty() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        Object[] exp = {null, null, null, null, null};

        Assertions.assertArrayEquals(exp, stack.getAll());
    }

    @Test
    @DisplayName("Получить все элементы не пустого стека")
    public void testGetAllObjectsWhenStackNotEmpty() {
        Stack stack = new Stack(TEST_SIZE_STACK);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Object[] exp = {1, 2, 3, null, null};

        Assertions.assertArrayEquals(exp, stack.getAll());
    }

}
