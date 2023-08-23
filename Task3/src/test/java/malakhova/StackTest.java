package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    @Test
    @DisplayName("Тест добавления элемента в стек")
    public void testSuccessPushElementsToStack() {
        Stack arrayStack = new Stack();
        arrayStack.push(1);
        Object[] stack1 = new Object[10];
        Object[] stack2 = arrayStack.getStack();
        stack1[0] = 1;
        Assertions.assertArrayEquals(stack1, stack2);
    }

    @Test
    @DisplayName("Тест удаления элемента из стека")
    public void testSuccessPopElementsFromStack() {
        Stack arrayStack = new Stack();
        arrayStack.push(1);
        arrayStack.push(2);
        Assertions.assertEquals(2, arrayStack.pop());
        arrayStack.pop();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arrayStack.pop();
        });
    }

    @Test
    @DisplayName("Тест на проверку пустой ли стек")
    public void testSuccessIsEmptyStack() {
        Stack arrayStack = new Stack();
        arrayStack.push(5);
        Assertions.assertEquals(false, arrayStack.isEmpty());
        arrayStack.pop();
        Assertions.assertEquals(true, arrayStack.isEmpty());
    }

    @Test
    @DisplayName("Тест метода, возвращающего верхний элемент стека")
    public void testIllegalArgumentExceptionWhenTopOfEmptyStack() {
        Stack arrayStack = new Stack();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arrayStack.top();
        });
    }
}
