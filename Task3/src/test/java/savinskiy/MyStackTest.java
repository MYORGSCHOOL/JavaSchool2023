package savinskiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {
    private MyStack stack;

    @BeforeEach
    public void init() {
        stack = new MyStack(4);
    }

    @Test
    public void testPush() {
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("3", stack.pop());
        assertEquals("2", stack.pop());
        assertEquals("1", stack.pop());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push("1");
        stack.push("2");
        stack.push("3");
        assertFalse(stack.isEmpty());

        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testTop() {
        stack.push(1);
        assertEquals(1, stack.top());

        stack.push(2);
        assertEquals(2, stack.top());

        stack.push(3);
        assertEquals(3, stack.top());
    }

    @Test
    public void testGetAll() {
        stack.push("1");
        stack.push("2");
        stack.push("3");

        Object[] allElements = stack.getAll();
        assertEquals("1", allElements[0]);
        assertEquals("2", allElements[1]);
        assertEquals("3", allElements[2]);
    }

    @Test
    public void testPushToFullStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        try {
            stack.push(5);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testPopEmptyStack() {
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testTopEmptyStack() {
        try {
            stack.top();
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }
}
