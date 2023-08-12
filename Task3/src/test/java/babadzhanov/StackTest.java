package babadzhanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class StackTest {

    @Test
    @DisplayName("Проверка пустого стэка")
    void checkStackIsEmpty() {
        Stack stack = new Stack(3);
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Проверка заполненного стэка")
    void checkStackIsFull() {
        Stack stack = new Stack(1);
        stack.push(1);
        Assertions.assertTrue(stack.isFull());
    }

    @Test
    @DisplayName("Проверка добавления элемента в стэк")
    void checkPushElementToStack() {
        Stack stack = new Stack(1);
        stack.push(1);
        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Проверка расширения стэка")
    void checkExpandedStack() {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        stack.push(2);
        Assertions.assertEquals(4, stack.getCapacity());
    }

    @Test
    @DisplayName("Проверка удаления элемента из стэка")
    void checkPopElementFromStack() {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        stack.pop();
        Assertions.assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Проверка удаления элемента из пустого стэка")
    public void checkNoSuchElementExceptionWhenStackIsEmpty() {
        Stack stack = new Stack(1);
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("Проверка получения верхнего элемента стэка")
    void checkTopElementFromStack() {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        Assertions.assertEquals(2, stack.top());
    }
}