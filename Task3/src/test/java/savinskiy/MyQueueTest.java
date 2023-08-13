package savinskiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyQueueTest {
    private MyQueue queue;

    @BeforeEach
    public void init() {
        queue = new MyQueue(4);
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(1);
        queue.enqueue("2");
        queue.enqueue(3.0);

        assertEquals(1, queue.deque());
        assertEquals("2", queue.deque());
        assertEquals(3.0, queue.deque());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueue("Element 1");
        assertFalse(queue.isEmpty());

        queue.deque();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testTop() {
        String str = "Element 1";
        String str2 = "Element 2";
        String str3 = "Element 3";

        queue.enqueue(str);
        queue.enqueue(str2);
        queue.enqueue(str3);

        assertEquals(str, queue.top());

        queue.deque();
        assertEquals(str2, queue.top());

        queue.deque();
        assertEquals(str3, queue.top());
    }


    @Test
    public void testGetAll() {
        queue.enqueue(18);
        queue.enqueue(3);
        queue.enqueue(93);

        Object[] queueAll = queue.getAll();
        assertEquals(3, queueAll.length);
    }

    @Test
    public void testEnqueueToFullQueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        try {
            queue.enqueue(5);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDequeEmptyQueue() {
        try {
            queue.deque();
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }
}
