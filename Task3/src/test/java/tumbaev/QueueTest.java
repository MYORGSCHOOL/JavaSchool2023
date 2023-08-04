package tumbaev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueueTest {

    @Test
    @DisplayName("IllegalArgumentException if creating queue with negative size")
    void testIllegalArgumentExceptionWhenCreateQueueWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new Queue(-1));
    }

    @Test
    @DisplayName("OutOfMemoryError if creating queue with too large size")
    void testOutOfMemoryErrorWhenCreateQueueTooLargeSize() {
        assertThrows(OutOfMemoryError.class, () -> new Queue(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("OutOfMemoryError when enqueueing too many elements")
    void enqueueTooManyElements() {
        Queue queue = new Queue();
        assertThrows(OutOfMemoryError.class, () -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                queue.enqueue(i);
            }
        });
    }

    @Test
    @DisplayName("Enqueue element to queue with size 0")
    void testCanEnqueueElementsToQueueWithZeroSize() {
        Queue queue = new Queue(0);
        queue.enqueue(1);
        assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("Enqueue more elements than initial queue size")
    void testCanEnqueueMoreElementsThanInitialSize() {
        Queue queue = new Queue(1);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.top());
    }

    @Test
    @DisplayName("Top element from empty queue should be null")
    void testTopEmptyQueueShouldBeNull() {
        Queue queue = new Queue();
        assertNull(queue.top());
    }

    @Test
    @DisplayName("Top element from queue multiple times should be idempotent")
    void testTopStackMultipleTimesShouldBeIdempotent() {
        Queue queue = new Queue();
        queue.enqueue(1);
        for (int i = 0; i < 3; i++) {
            assertEquals(1, queue.top());
        }
    }

    @Test
    @DisplayName("Dequeue element from empty queue should throw EmptyQueueException")
    void testDequeueEmptyQueueShouldReturnNull() {
        Queue queue = new Queue();
        assertThrows(EmptyQueueException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Dequeue element from queue should remove the element")
    void testDequeueQueueShouldRemoveElement() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.top());
    }

    @Test
    @DisplayName("Enqueue should work correctly after dequeue of non-empty queue")
    void testEnqueueShouldWorkCorrectlyAfterDequeue() {
        Queue queue = new Queue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        queue.enqueue(1);
        assertEquals(2, queue.dequeue());

        assertEquals(3, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }

    @Test
    @DisplayName("Is empty should return true if queue is empty")
    void testIsEmptyShouldReturnTrueIfQueueIsEmpty() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Queue should become empty after dequeue of all elements")
    void testIsEmptyShouldReturnTrueAfterDequeueOfAllElements() {
        Queue queue = new Queue();
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }
}