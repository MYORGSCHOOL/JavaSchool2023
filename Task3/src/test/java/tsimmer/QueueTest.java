package tsimmer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    @Test
    @DisplayName("Тест на добавление элемента")
    public void testSuccessEnqueueOneElement() {
        Queue queue = new Queue(5);
        queue.enqueue(1);
        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Тест на удаление элемента")
    public void testSuccessDequeueOneElement() {
        Queue queue = new Queue(5);
        Assertions.assertEquals(-1,queue.dequeue());
    }

    @Test
    @DisplayName("Тест на проверку пустой очереди")
    public void testEmptyQueueCheck() {
        Queue queue = new Queue(5);
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку полной очереди")
    public void testFullQueueCheck() {
        Queue queue = new Queue(5);
        Assertions.assertFalse(queue.isFull());
    }
}
