package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования методов класса Queue
 */
public class QueueTest {
    @Test
    @DisplayName("checking that the function - enqueue works correctly")
    void enqueueTest() {
        Object o = new Object();
        Object[] expected = {o};
        Queue actual = new Queue(1);
        actual.enqueue(o);
        Assertions.assertArrayEquals(expected, actual.getQueue());
    }

    @Test
    @DisplayName("checking that the function - enqueue works correctly when queue is full")
    void enqueueOverflowTest() {
        Assertions.assertDoesNotThrow(() -> {
            Queue actual = new Queue(2);
            for (int i = 0; i < 3; i++) {
                actual.enqueue(new Object());
            }
        });
    }

    @Test
    @DisplayName("checking that the function - dequeue works correctly")
    void dequeueTest() {
        Object expected = new Object();
        Queue actual = new Queue(2);
        actual.enqueue(expected);
        actual.enqueue(new Object());
        Assertions.assertEquals(expected, actual.dequeue());

    }

    @Test
    @DisplayName("checking that the function - dequeue works correctly when queue is empty")
    void dequeueEmptyTest() {
        Queue queue = new Queue(0);
        Assertions.assertNull(queue.dequeue());

    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when queue is empty")
    void isEmptyTest() {
        Queue actual = new Queue(0);
        Assertions.assertTrue(actual.isEmpty());

    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when queue isn't empty")
    void isEmptyFalseTest() {
        Queue actual = new Queue(1);
        actual.enqueue(new Object());
        Assertions.assertFalse(actual.isEmpty());

    }

    @Test
    @DisplayName("checking that the function - top works correctly")
    void topTest() {
        Object first = new Object();
        Object second = new Object();
        Queue queue = new Queue(2);
        queue.enqueue(first);
        queue.enqueue(second);
        Assertions.assertEquals(first, queue.top());

    }

    @Test
    @DisplayName("checking that the function - top works correctly when queue is empty")
    void topNullTest() {
        Queue actual = new Queue(0);
        Assertions.assertNull(actual.top());
    }
}
