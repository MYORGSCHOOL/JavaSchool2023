package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    @Test
    @DisplayName("Тест добавления элемента в очередь")
    public void testSuccessEnqueueElementsToQueue() {
        Queue arrayQueue = new Queue(7);
        arrayQueue.enqueue(6);
        Object[] queue1 = new Object[7];
        Object[] queue2 = arrayQueue.getQueue();
        queue1[0] = 6;
        Assertions.assertArrayEquals(queue1, queue2);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(6);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arrayQueue.enqueue(6);
        });
    }

    @Test
    @DisplayName("Тест удаления элемента из очереди")
    public void testSuccessDequeueElementsFromQueue() {
        Queue arrayQueue = new Queue(7);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(7);
        arrayQueue.dequeue();
        Object[] queue1 = new Object[7];
        Object[] queue2 = arrayQueue.getQueue();
        queue1[0] = 7;
        Assertions.assertArrayEquals(queue1, queue2);
        arrayQueue.dequeue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arrayQueue.dequeue();
        });
    }

    @Test
    @DisplayName("Тест на проверку пустая ли очередь")
    public void testSuccessIsEmptyQueue() {
        Queue arrayQueue = new Queue(7);
        arrayQueue.enqueue(6);
        Assertions.assertEquals(false, arrayQueue.isEmpty());
        arrayQueue.dequeue();
        Assertions.assertEquals(true, arrayQueue.isEmpty());
    }

    @Test
    @DisplayName("Тест метода, возвращающего верхний элемент очереди")
    public void testIllegalArgumentExceptionWhenTopOfEmptyQueue() {
        Queue arrayQueue = new Queue(7);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arrayQueue.top();
        });
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(7);
        arrayQueue.enqueue(8);
        Assertions.assertEquals(6, arrayQueue.top());
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
    }
}
