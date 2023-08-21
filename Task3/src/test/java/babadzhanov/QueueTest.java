package babadzhanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class QueueTest {

    @Test
    @DisplayName("Проверка пустой очереди")
    void checkQueueIsEmpty() {
        Queue queue = new Queue(2);
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Проверка заполненной очереди")
    void checkQueueIsFull() {
        Queue queue = new Queue(1);
        queue.enqueue(1);
        Assertions.assertTrue(queue.isFull());
    }

    @Test
    @DisplayName("Проверка добавления элемента в незаполненную очередь")
    void checkEnqueueElementToQueue() {
        Queue queue = new Queue(2);
        queue.enqueue(1);
        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Проверка добавления элемента в переполненную очередь")
    public void checkIndexOutOfBoundsExceptionWhenQueueIFull() {
        Queue queue = new Queue(3);
        for (int i = 0; i < 3; i++) {
            queue.enqueue(1);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> queue.enqueue(1));
    }

    @Test
    @DisplayName("Проверка удаления элемента из заполненной очереди")
    void checkDequeueElementFromQueue() {
        Queue queue = new Queue(2);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();
        Assertions.assertEquals(2, queue.top());
    }

    @Test
    @DisplayName("Проверка удаления элемента из пустой очереди")
    public void checkNoSuchElementExceptionWhenQueueIsEmpty() {
        Queue queue = new Queue(1);
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    @DisplayName("Проверка получение 1-го элемента из очереди")
    void checkTopElementFromQueue() {
        Queue queue = new Queue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        Assertions.assertEquals(1, queue.top());
    }
}