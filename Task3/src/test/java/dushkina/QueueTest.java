package dushkina;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс - тестирование методов класса Queue
 */
public class QueueTest {
    @Test
    @DisplayName("Тест на добавление элемента в конец переполненной очереди")
    public void putInAnOvercrowdedQueueTest() {
        Queue queue = new Queue(0);
        assertThrows(IndexOutOfBoundsException.class, () -> queue.enqueue("1"));
    }

    @Test
    @DisplayName("Тест на добавление элемента в конец очереди")
    void enqueueTest() {
        Queue queue = new Queue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        Object[] arrayTest = {"1", "2"};
        assertArrayEquals(arrayTest, queue.getAll());
    }

    @Test
    @DisplayName("Тест на возвращение первого элемента (метод top())")
    public void topTest() {
        Queue queue = new Queue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        assertEquals("1", queue.top());
    }

    @Test
    @DisplayName("Тест на возвращение первого элемента(метод top()) из пустой очереди")
    public void theTopElementWhenTheQueueIsEmptyTest() {
        Queue queue = new Queue(3);
        assertEquals(null, queue.top());
    }

    @Test
    @DisplayName("Тест метода isEmpty(), когда очередь пуста")
    public void isEmptyTest() {
        Queue queue = new Queue(3);
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест метода isEmpty(), когда очередь не пуста")
    public void theQueueIsNotEmptyTest() {
        Queue queue = new Queue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Вывод элемента из начала очереди")
    public void dequeueTest() {
        Queue queue = new Queue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        assertEquals("1", queue.dequeue());
    }

    @Test
    @DisplayName("Вывод элемента из начала пустой очереди")
    public void withdrawFromAnEmptyQueueTest() {
        Queue queue = new Queue(3);
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }
}
