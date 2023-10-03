package koroliov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    private Queue queue = new Queue();
    @BeforeEach
    public void genData() {
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
    }

    @Test
    @DisplayName("The method tests the removal of the first item from the queue.")
    void testDequeue() {
        queue.dequeue();
        Assertions.assertEquals("two", queue.top());
    }

    @Test
    @DisplayName("The method tests adding a new item to the top of the queue.")
    void testEnqueue() {
        queue.enqueue("four");
        Object[] temp = queue.getAll();

        Assertions.assertEquals("four", temp[0]);
    }

    @Test
    @DisplayName("The method tests the return of the first deleted item from the queue.")
    void testTop() {
        Assertions.assertEquals("one", queue.top());
    }

    @Test
    @DisplayName("The method tests retrieving an identical array of queue elements.")
    void testGetAll() {
        Object[] temp = queue.getAll();
        boolean check = true;

        for (int i = 2; i > 0; i--) {
            check = temp[i].equals(queue.top());
            queue.dequeue();
        }

        Assertions.assertEquals(true, check);
    }

    @Test
    @DisplayName("The method tests the return of the result of the queue check for emptiness.")
    void testIsEmpty() {
        Assertions.assertEquals(false, queue.isEmpty());
    }
}
