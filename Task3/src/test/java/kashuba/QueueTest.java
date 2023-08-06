package kashuba;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    @DisplayName("Тест на пустоту очереди с top()")
    void test1() {
        Queue queue = new Queue();

        assertThrows(IllegalStateException.class, queue::top);
    }

    @Test
    @DisplayName("Тест на переполнение очереди")
    void test2() {
        Queue queue = new Queue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> queue.enqueue(3));
    }

    @Test
    @DisplayName("Проверка корректной работы toString()")
    void test3() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    @DisplayName("Тест на вычет")
    void test4() {
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
    void test5() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Проверка на пустоту очереди")
    void test6() {
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
    void test7() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertTrue(queue.isFull());
    }

    @Test
    @DisplayName("Проверка на пустоту очереди с toString()")
    void test8() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.dequeue();

        assertEquals("[]", queue.toString());
    }

    @Test
    @DisplayName("Проверка на не пустую очередь")
    void test9() {
        Queue queue = new Queue(1);

        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }
}
