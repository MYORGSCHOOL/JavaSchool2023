package sevostyanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    @Test
    @DisplayName("Тест на добавление элемента в очередь ")
    public void testEnqueue() {
        Queue queue = new Queue(7);
        queue.enqueue(1);
        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Тест на удаление элемента из очереди")
    public void testSuccessDequeueOneElement() {
        Queue queue = new Queue(7);
        Assertions.assertEquals(-1, queue.dequeue());
    }

    @Test
    @DisplayName("Тест на проверку пустой очереди")
    public void testEmptyQueueCheck() {
        Queue queue = new Queue(7);
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку полной очереди")
    public void testFullQueueCheck() {
        Queue queue = new Queue(7);
        Assertions.assertFalse(queue.isFull());
    }
}

