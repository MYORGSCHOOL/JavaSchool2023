package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    @Test
    @DisplayName("Вставка элемента в пустой стек")
    public void testSuccessPushOneElementToEmptyStack() {
        Stack stack = new Stack();
        stack.push(8);
        Assertions.assertEquals(8, stack.toArray()[0]);
    }

    @Test
    @DisplayName("Вставка элемента в переполненный стек")
    public void testFailPushOneElementToFullStack() {
        Stack stack = new Stack();
        for(int i = 0; i < 100; i++) {
            stack.push(8);
        }
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.push(8));
        Assertions.assertEquals(100, stack.size());
    }

    @Test
    @DisplayName("Возврат верхнего элемента и удаление его из непустого стека")
    public void testSuccessGetFirstElementAndPopItFromNotEmptyStack() {
        Stack stack = new Stack();
        stack.push(64);
        stack.push(89);
        Assertions.assertEquals(89, stack.pop());
        Assertions.assertEquals(64, stack.toArray()[0]);
        Assertions.assertEquals(1, stack.size());
    }

    @Test
    @DisplayName("Возврат верхнего элемента и удаление его из пустого стека")
    public void testFailGetFirstElementAndPopItFromEmptyStack() {
        Stack stack = new Stack();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.pop());
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("Проверка пустого стека на пустоту")
    public void testSuccessEmptyStackIsEmpty() {
        Stack stack = new Stack();
        Assertions.assertEquals(true, stack.isEmpty());
    }

    @Test
    @DisplayName("Проверка непустого стека на пустоту")
    public void testSuccessNotEmptyStackIsNotEmpty() {
        Stack stack = new Stack();
        stack.push(23);
        Assertions.assertEquals(false, stack.isEmpty());
    }

    @Test
    @DisplayName("Получение верхнего элемента из пустого стека")
    public void testFailGetFirstElementFromEmptyStack() {
        Stack stack = new Stack();
        Assertions.assertEquals(null, stack.top());
    }

    @Test
    @DisplayName("Получение верхнего элемента из непустого стека")
    public void testSuccessGetFirstElementFromNotEmptyStack() {
        Stack stack = new Stack();
        stack.push(84);
        Assertions.assertEquals(84, stack.top());
    }
}
