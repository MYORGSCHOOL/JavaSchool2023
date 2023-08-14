package druzhinin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class StackTest {
    @Test
    @DisplayName("Тест на получение исключения NegativeArraySizeException" +
            " при создании стека с отрицательным максимальным размером")
    public void testNegativeArraySizeExceptionWhenCreateStackWithNegativeSize() {
        Assertions.assertThrows(NegativeArraySizeException.class, () -> new Stack(-1));
    }

    @Test
    @DisplayName("Тест на успешное создание стека с положительным максимальным размером")
    public void testSuccessCreateQueue() {
        Object[] expected = new Object[3];
        Stack stack = new Stack(3);
        Assertions.assertArrayEquals(expected, stack.toArray());
    }

    @Test
    @DisplayName("Тест на успешную вставку элементов в неполный стек")
    public void testSuccessPushIntoNotFullStack() {
        Object o1 = new Object();
        Object o2 = new Object();

        Object[] expected = {o1, o2};
        Stack stack = new Stack(2);
        stack.push(o1);
        stack.push(o2);

        Assertions.assertArrayEquals(expected, stack.toArray());
    }

    @Test
    @DisplayName("Тест на получение исключения EmptyStackException" +
            " при удалении элемента из пустого стека")
    public void testIndexOutOfBoundsExceptionWhenPopFromEmptyStack() {
        Stack stack = new Stack(3);
        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("Тест на успешное удаление элемента из непустого стека")
    public void testSuccessPopFromNotEmptyStack() {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();

        Object[] expected = {o1, o2, o4};

        Stack stack = new Stack(3);
        stack.push(o1);
        stack.push(o2);
        stack.push(o3);
        Object poppedObject = stack.pop();
        stack.push(o4);

        Assertions.assertArrayEquals(expected, stack.toArray());
        Assertions.assertEquals(o3, poppedObject);
    }

    @Test
    @DisplayName("Тест на проверку пустого стека на пустоту")
    public void testEmptyStackIsEmptyIsTrue() {
        Stack stack = new Stack(3);
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку непустого стека на пустоту")
    public void testNotEmptyStackIsEmptyIsFalse() {
        Stack stack = new Stack(3);
        stack.push(new Object());
        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест на равенство null верхнего элемента пустого стека")
    public void testEmptyStackTopElementIsNull() {
        Stack stack = new Stack(3);
        Assertions.assertNull(stack.top());
    }

    @Test
    @DisplayName("Тест на правильное получение верхнего элемента непустого стека")
    public void testNotEmptyStackTopIsCorrect() {
        Object o1 = new Object();
        Object o2 = new Object();

        Stack stack = new Stack(3);
        stack.push(o1);
        stack.push(o2);

        Assertions.assertEquals(o2, stack.top());
    }

    @Test
    @DisplayName("Тест на успешное получение копии массива элементов стека")
    public void testSuccessGettingCopyFromToArray() {
        Object o = new Object();

        Stack stack = new Stack(3);
        Object[] copy = stack.toArray();
        copy[0] = o;
        stack.push(o);

        Assertions.assertNotEquals(copy, stack.toArray());
        Assertions.assertArrayEquals(copy, stack.toArray());
    }

    @Test
    @DisplayName("Тест на правильное получение строки содержимого пустого стека")
    public void testCorrectStringFromToStringOnEmptyStack() {
        String expected = "Stack{}";

        Stack stack = new Stack(5);
        Assertions.assertEquals(expected, stack.toString());
    }

    @Test
    @DisplayName("Тест на правильное получение строки содержимого непустого стека")
    public void testCorrectStringFromToStringOnNotEmptyStack() {
        String expected = "Stack{1, 2, 3}";

        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        Assertions.assertEquals(expected, stack.toString());
    }
}
