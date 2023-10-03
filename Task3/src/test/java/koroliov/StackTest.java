package koroliov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {
    private Stack stack = new Stack();

    @BeforeEach
    public void genData() {
        stack.Push("one");
        stack.Push("two");
        stack.Push("three");
    }

    @Test
    void testPop() {
        Assertions.assertEquals("three", (String) stack.Pop());
    }

    @Test
    void testPush() {
        stack.Push("test");
        Assertions.assertEquals("test", (String) stack.Pop());
    }

    @Test
    void testTop() {
        String temp = (String) stack.Top();
        Assertions.assertEquals(true, "three" == stack.Pop() && "three" == temp);
    }

    @Test
    void testGetAll() {
        Object[] temp = stack.getAll();
        boolean check = true;

        for (int i = 2; i > 0; i--) {
            check = temp[i].equals(stack.Pop());
        }

        Assertions.assertEquals(true, check);
    }

    @Test
    void testIsEmpty() {
        Assertions.assertEquals(false, stack.isEmpty());
    }
}
