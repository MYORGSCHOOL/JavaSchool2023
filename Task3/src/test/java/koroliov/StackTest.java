package koroliov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    private Stack stack = new Stack();

    @BeforeEach
    public void genData() {
        stack.push("one");
        stack.push("two");
        stack.push("three");
    }

    @Test
    @DisplayName("The method tests the return of the top element of the stack.")
    void testpop() {
        Assertions.assertEquals("three", (String) stack.pop());
    }

    @Test
    @DisplayName("The method tests the addition of an element to the top of the stack.")
    void testpush() {
        stack.push("test");
        Assertions.assertEquals("test", (String) stack.pop());
    }

    @Test
    @DisplayName("The method tests the return of the top stack element without deleting it.")
    void testtop() {
        String temp = (String) stack.top();
        Assertions.assertEquals(true, "three" == stack.pop() && "three" == temp);
    }

    @Test
    @DisplayName("The method tests retrieving an identical array of stack elements.")
    void testGetAll() {
        Object[] temp = stack.getAll();
        boolean check = true;

        for (int i = 2; i > 0; i--) {
            check = temp[i].equals(stack.pop());
        }

        Assertions.assertEquals(true, check);
    }

    @Test
    @DisplayName("The method tests the return of the result of the stack check for emptiness.")
    void testIsEmpty() {
        Assertions.assertEquals(false, stack.isEmpty());
    }
}
