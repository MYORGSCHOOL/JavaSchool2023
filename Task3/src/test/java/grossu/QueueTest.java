package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;


/**
 * Класс для тестирования методов класса Queue
 */
public class QueueTest {
    @Test
    @DisplayName("checking that the function - enqueue works correctly")
    void testSuccessEnqueueWithOneElementInQueue() {
        Object o = new Object();
        Object[] expected = {o};
        Queue actual = new Queue(1);
        actual.enqueue(o);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("checking that the function - enqueue works correctly when queue is full")
    void testGetThrowEnqueueWhenQueueIsFull() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            Queue actual = new Queue(2);
            for (int i = 0; i < 3; i++) {
                actual.enqueue(new Object());
            }
        });
    }

    @Test
    @DisplayName("checking that the function - dequeue works correctly")
    void testSuccessDequeueWithTwoElementInQueue() {
        Object expected = new Object();
        Queue actual = new Queue(2);
        actual.enqueue(expected);
        actual.enqueue(new Object());
        Assertions.assertEquals(expected, actual.dequeue());
    }

    @Test
    @DisplayName("checking that the function - dequeue works correctly when queue is empty")
    void testGetThrowDequeueWhenStackIsEmpty() {
        Queue queue = new Queue(0);
        Assertions.assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when queue is empty")
    void testTrueIsEmptyWhenQueueIsEmpty() {
        Queue actual = new Queue(0);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when queue isn't empty")
    void testFalseIsEmptyWhenQueueIsNotEmpty() {
        Queue actual = new Queue(1);
        actual.enqueue(new Object());
        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    @DisplayName("checking that the function - top works correctly")
    void testSuccessTopWithTwoElementInQueue() {
        Object first = new Object();
        Object second = new Object();
        Queue queue = new Queue(2);
        queue.enqueue(first);
        queue.enqueue(second);
        Assertions.assertEquals(first, queue.top());
    }

    @Test
    @DisplayName("checking that the function - top works correctly when queue is empty")
    void testReturnNullTopWhenQueueIsNotEmpty() {
        Queue actual = new Queue(0);
        Assertions.assertNull(actual.top());
    }
}
