package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import volovnik.exceptions.QueueIsEmptyException;

public class QueueTest {

    @Test
    @DisplayName("Тест на добавление в очередь")
    public void enqueueTest() {
        Queue queue = new Queue(10);
        queue.enqueue("s1");
        queue.enqueue("s2");
        queue.enqueue("s3");
        Assertions.assertEquals("s1", queue.top());
    }

    @Test
    @DisplayName("Тест на удаление из очереди")
    public void dequeueTest() {
        Queue queue = new Queue(10);
        queue.enqueue("s1");
        queue.enqueue("s2");
        queue.enqueue("s3");
        queue.dequeue();
        Assertions.assertEquals("s2", queue.top());
    }

    @Test
    @DisplayName("Тест на пустую очередь")
    public void isEmptyTest() {
        Queue queue = new Queue(10);
        Assertions.assertTrue(queue.isEmpty());
        queue.enqueue("s1");
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест на первый элемент очереди")
    public void topTest() {
        Queue queue = new Queue(10);
        Assertions.assertNull(queue.top());
        queue.enqueue("s1");
        queue.enqueue("s2");
        Assertions.assertEquals("s1", queue.top());
    }

    @Test
    @DisplayName("Тест на удаление из пустой очереди")
    public void emptyQueueDeleteTest() {
        Queue queue = new Queue(5);
        Assertions.assertThrows(QueueIsEmptyException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Тест на получение всех элементов")
    public void getAllTest() {
        Queue queue = new Queue(5);
        queue.enqueue("s1");
        queue.enqueue("s2");
        queue.enqueue("s3");
        queue.enqueue("s4");
        queue.dequeue();
        Object[] all = queue.getAll();
        Object[] test = new Object[] {null, "s2", "s3", "s4", null};
        Assertions.assertArrayEquals(test, all);
    }
}
