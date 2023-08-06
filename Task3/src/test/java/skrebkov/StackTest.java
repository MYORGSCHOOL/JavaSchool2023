package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class StackTest {
    @Test
    @DisplayName("Тест на проверку пустого стека")
    public void testSuccessCheckEmptyStack() {
        Stack stack = new Stack();
        Assertions.assertTrue(stack.isEmpty());
        stack.push(new Object());
        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Тест неудачного взятия верхнего элемента из стака")
    public void testExceptionTakeTopElementFromStack() {
        Stack stack = new Stack();
        Assertions.assertThrows(EmptyStackException.class, stack::top);
    }
    @Test
    @DisplayName("Тест взятия верхнего элемента из стака")
    public void testSuccessTakeTopElementFromStack(){
        Stack stack = new Stack();
        for(int i = 0; i < 10; i++){
            stack.push(i);
        }
        Assertions.assertEquals(9,stack.top());
    }

    @Test
    @DisplayName("Тест неудачного взятия и удаления верхнего элемента из стака")
    public void testExceptionPopFromStack(){
        Stack stack = new Stack();
        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("Тест метода pop")
    public void testSuccessPopFromStack(){
        Stack stack = new Stack();
        for(int i = 0; i < 10; i++){
            stack.push(i);
        }
        Assertions.assertEquals(9,stack.pop());
        Assertions.assertEquals(8,stack.pop());
    }

    @Test
    @DisplayName("Проверка метода вывода всего массива")
    public void testEqualsObjectArrInGetAll(){
        Stack stack = new Stack();
        String[] arr = {"first","second"};
        stack.push(arr[0]);
        stack.push(arr[1]);
        Assertions.assertArrayEquals(arr, stack.getAll());
    }
}
