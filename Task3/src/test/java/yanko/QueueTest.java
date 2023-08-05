package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    @Test
    @DisplayName("Вставка элемента в пустую очередь")
    public void testSuccessEnqueueOneElementToEmptyQueue() {
        Queue queue = new Queue();
        queue.enqueue(3);
        Assertions.assertEquals(3, queue.toArray()[0]);
    }

    @Test
    @DisplayName("Вставка элемента в переполненную очередь")
    public void testFailEnqueueOneElementToFullQueue() {
        Queue queue = new Queue();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(3);
        }
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> queue.enqueue(3));
    }

    @Test
    @DisplayName("Удаление элемента из непустой очереди")
    public void testSuccessGetFirstElementAndDequeueItFromNotEmptyQueue() {
        Queue queue = new Queue();
        queue.enqueue(3);
        queue.enqueue(4);
        Assertions.assertEquals(3, queue.dequeue());
        Assertions.assertEquals(4, queue.toArray()[0]);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Удаление элемента из пустой очереди")
    public void testFailGetFirstElementAndDequeueItFromEmptyQueue() {
        Queue queue = new Queue();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> queue.dequeue());
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Проверка пустой очереди на пустоту")
    public void testSuccessEmptyQueueIsEmpty() {
        Queue queue = new Queue();
        Assertions.assertEquals(true, queue.isEmpty());
    }

    @Test
    @DisplayName("Проверка непустой очереди на пустоту")
    public void testSuccessNotEmptyQueueIsNotEmpty() {
        Queue queue = new Queue();
        queue.enqueue(12);
        Assertions.assertEquals(false, queue.isEmpty());
    }

    @Test
    @DisplayName("Получение первого элемента из пустой очереди")
    public void testFailGetFirstElementFromEmptyQueue() {
        Queue queue = new Queue();
        Assertions.assertEquals(null, queue.top());
    }

    @Test
    @DisplayName("Получение первого элемента из непустой очереди")
    public void testSuccessGetFirstElementFromNotEmptyQueue() {
        Queue queue = new Queue();
        queue.enqueue(56);
        Assertions.assertEquals(56, queue.top());
    }
}
