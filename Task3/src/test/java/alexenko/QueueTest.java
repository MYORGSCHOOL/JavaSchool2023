package alexenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {

    private static final int TEST_SIZE_QUEUE = 5;

    @Test
    @DisplayName("Вставка элемента в пустую очередь")
    public void testEnqueueOneObject() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);

        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Вставка элемента в полную очередь")
    public void testEnqueueOneObjectWhenQueueFull() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        Assertions.assertThrowsExactly(RuntimeException.class, () -> {
            queue.enqueue(6);
        });
    }

    @Test
    @DisplayName("Удаление элемента из не пустой очереди")
    public void testDequeueOneObject() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);
        queue.dequeue();

        Assertions.assertNotEquals(1, queue.top());
    }

    @Test
    @DisplayName("Удаление элемента из пустой очереди")
    public void testDequeueOneObjectWhenQueueEmpty() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);

        Assertions.assertThrowsExactly(RuntimeException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Проверка пустой очереди на пустоту")
    public void testQueueIsEmptyTrue() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);

        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Проверка не пустой очереди на пустоту")
    public void testQueueIsEmptyFalse() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);

        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Проверка заполненной очереди на заполненность")
    public void testQueueIsFullTrue() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        Assertions.assertTrue(queue.isFull());
    }

    @Test
    @DisplayName("Проверка пустой очереди на заполненость")
    public void testQueueIsFullFalseTest() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);

        Assertions.assertFalse(queue.isFull());
    }

    @Test
    @DisplayName("Получение элемента с начала очереди")
    public void testGetTopObject() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);

        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Получение элемента с начала очереди, после удаления первого элемента ")
    public void testGetTopObjectAfterDequeueObjFromQueue() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();

        Assertions.assertEquals(2, queue.top());
    }

    @Test
    @DisplayName("Получить все элементы из не пустой очереди")
    public void testGetAllObjectsFromQueue() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Object[] ex = {1, 2, 3, null, null};
        Object[] arr = queue.getAll();

        Assertions.assertArrayEquals(ex, arr);
    }

    @Test
    @DisplayName("Получить все элементы из пустой очереди")
    public void testGetAllObjectsWhenQueueIsEmpty() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        Object[] ex = {null, null, null, null, null};
        Object[] arr = queue.getAll();

        Assertions.assertArrayEquals(ex, arr);
    }

    @Test
    @DisplayName("Проверка очереди на закольцованность")
    public void testQueueLoop() {
        Queue queue = new Queue(TEST_SIZE_QUEUE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(6);
        queue.enqueue(7);
        Object[] ex = {6, 7, 3, 4, 5};
        Object[] arr = queue.getAll();

        Assertions.assertArrayEquals(ex, arr);
    }
}
