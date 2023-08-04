package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Класс тестирования стека
 */
public class StackTest {
    /**
     * Код возврата ошибки
     */
    private static final int ERROR_CODE = -1;
    /**
     * Код возврата при успешном выполнении функции
     */
    private static final int SUCCESS_CODE = 1;
    /**
     * максимальный размер стека
     */
    private static final int MAX_SIZE = 5;

    @Test
    @DisplayName("Тест на успешную вставку элемента в стек")
    public void testSuccessPushElementStack() {
        Stack stack = new Stack(MAX_SIZE);
        stack.push(5);
        stack.push("a");
        Assertions.assertEquals(SUCCESS_CODE, stack.push(3.5));
        Assertions.assertEquals(3.5, stack.top());
    }

    @Test
    @DisplayName("Тест на неудачную попытку вставки элемента в стек")
    public void testFailPushElementStack() {
        Stack stack = new Stack(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            stack.push(i);
        }
        Assertions.assertEquals(ERROR_CODE, stack.push(MAX_SIZE));
        Assertions.assertNotEquals(MAX_SIZE, stack.top());
    }

    @Test
    @DisplayName("Тест на успешное удаление элементов из стека")
    public void testSuccessPopAllElementsStack() {
        Stack stack = new Stack(MAX_SIZE);
        int i;
        for (i = 0; i != MAX_SIZE; i++) {
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
        Stack stack = new Stack(MAX_SIZE);
        Assertions.assertEquals(null, stack.pop());
    }

    @Test
    @DisplayName("Тест на проверку пустоты стека - стек пустой")
    public void testCheckStackIsEmpty() {
        Stack stack = new Stack(MAX_SIZE);
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку пустоты стека - стек не пустой")
    public void testCheckStackIsNotEmpty() {
        Stack stack = new Stack(MAX_SIZE);
        stack.push(10);
        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на получение элемента вершины стека")
    public void testSuccessGetTopStack() {
        Stack stack = new Stack(MAX_SIZE);
        stack.push("string");
        stack.push(true);
        stack.push(55);
        Assertions.assertEquals(55, stack.top());
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения элемента вершины стека")
    public void testFailGetTopStack() {
        Stack stack = new Stack(MAX_SIZE);
        Assertions.assertEquals(null, stack.top());
    }
}
