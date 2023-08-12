package karmanchikova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class QueueTest {
    @Test
    @DisplayName("Тест на добавление элемента в очередь")
    public void testSuccessEnqueueOneElement() {
        Queue queue = new Queue(3);
        queue.enqueue(1);
        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Тест на удаление элемента из очереди")
    public void testExceptionDeleteFromQueue() {
        Queue queue = new Queue(3);
        Assertions.assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Тест на проверку пустой очереди")
    public void testEmptyQueueCheck() {
        Queue queue = new Queue(3);
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку заполненой очереди")
    public void testFullQueueCheck() {
        Queue queue = new Queue(3);
        Assertions.assertFalse(queue.isFull());
    }
}