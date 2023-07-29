package kashuba;

import java.util.EmptyStackException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    @DisplayName("Проверка toString когда в стеке есть элементы")
    void test1() {
        Stack stack = new Stack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals("[1, 2, 3, 4]", stack.toString());
    }

    @Test
    @DisplayName("Проверка toString когда в стеке нет элементов")
    void test2() {
        Stack stack = new Stack(4);

        assertEquals("[]", stack.toString());
    }

    @Test
    @DisplayName("Проверка переполнения стека")
    void test3() {
        Stack stack = new Stack(1);

        stack.push(1);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.push(2));
    }

    @Test
    @DisplayName("Проверка на то, что метод top ничего не вернет, если ничего не положено")
    void test4() {
        Stack stack = new Stack(4);

        assertThrows(EmptyStackException.class, stack::top);
    }

    @Test
    @DisplayName("Проверка на то, что работает pop")
    void test5() {
        Stack stack = new Stack(4);

        stack.push(1);
        stack.push(2);
        stack.pop();

        assertEquals("[1]", stack.toString());
    }

    @Test
    @DisplayName("Проверка что сохраняется структура данных стека")
    void test6() {
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
    void test7() {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Проверка заполненности стека")
    void test8() {
        Stack stack = new Stack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertTrue(stack.isFull());
    }

    @Test
    @DisplayName("Проверка корректной работы pop")
    void test9() {
        Stack stack = new Stack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
    }
}
