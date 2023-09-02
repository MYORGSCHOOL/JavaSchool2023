package koroliov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {
    Queue queue;

    @BeforeEach
    void prepareDate() {
        queue = new Queue();
        queue.Enqueue("One");
        queue.Enqueue("Two");
        queue.Enqueue("Three");

    }

    @Test
    void testDequeue() {
        assertEquals(1, queue.Dequeue());
    }

    @Test
    void testEnqueue() {
        assertEquals(1, queue.Enqueue("Boy"));
    }

    @Test
    void testTop() {
        assertEquals("One", (String) queue.Top());
    }

    @Test
    void testGetAll() {
        Object[] arr = {"One", "Two", "Three"};
        Object[] temp = queue.getAll();
        Boolean result = false;
        for (int i = 0; i < temp.length; i++) {
            if (arr[i].equals(temp[i])) {
                result = true;
            } else result = false;
        }

        assertEquals(true, result);
    }

    @Test
    void testIsEmpty() {
        assertEquals(false, queue.isEmpty());
    }
}
