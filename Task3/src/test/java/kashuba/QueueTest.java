package kashuba;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QueueTest {
    @Test
    @DisplayName("Тест на пустоту очереди с top()")
    void testNoSuchElementExceptionWhenTopEmptyQueue() {
        Queue queue = new Queue();

        assertThrows(NoSuchElementException.class, queue::top);
    }

    @Test
    @DisplayName("Тест на переполнение очереди")
    void testQueueOverflow() {
        Queue queue = new Queue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertThrows(IndexOutOfBoundsException.class, () -> queue.enqueue(3));
    }

    @Test
    @DisplayName("Проверка корректной работы toString()")
    void testCheckingTheCorrectOperationOfToString() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    @DisplayName("Тест на вычет")
    void testDeduction() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();
        queue.dequeue();

        assertEquals("[3]", queue.toString());
    }

    @Test
    @DisplayName("Проверку метода top()")
    void testCheckingTheTopMethod() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Проверка на пустоту очереди")
    void testCheckingForEmptyQueue() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Проверка на заполненность очереди")
    void testCheckingForQueueFullness() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertTrue(queue.isFull());
    }

    @Test
    @DisplayName("Проверка на пустоту очереди с toString()")
    void testCheckingForEmptyQueueWithToString() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.dequeue();

        assertEquals("[]", queue.toString());
    }

    @Test
    @DisplayName("Проверка на не пустую очередь")
    void testCheckingForANonemptyQueue() {
        Queue queue = new Queue(1);

        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }
}