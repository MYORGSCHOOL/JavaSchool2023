package koroliov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {
    private Queue queue = new Queue();
    @BeforeEach
    public void genData() {
        queue.Enqueue("one");
        queue.Enqueue("two");
        queue.Enqueue("three");
    }

    @Test
    void testDequeue() {
        queue.Dequeue();
        Assertions.assertEquals("two", queue.Top());
    }

    @Test
    void testEnqueue() {
        queue.Enqueue("four");
        Object[] temp = queue.getAll();

        Assertions.assertEquals("four", temp[temp.length - 1]);
    }

    @Test
    void testTop() {
        Assertions.assertEquals("one", queue.Top());
    }

    @Test
    void testGetAll() {
        Object[] temp = queue.getAll();
        boolean check = true;

        for (int i = 0; i < 3; i++) {
            check = temp[i].equals(queue.Top());
            queue.Dequeue();
        }

        Assertions.assertEquals(true, check);
    }

    @Test
    void testIsEmpty() {
        Assertions.assertEquals(false, queue.isEmpty());
    }
}
