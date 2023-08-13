package sevostyanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    @Test
    @DisplayName("���� �� ���������� �������� � ������� ")
    public void testEnqueue() {
        Queue queue = new Queue(7);
        queue.enqueue(1);
        Assertions.assertEquals(1, queue.top());
    }

    @Test
    @DisplayName("���� �� �������� �������� �� �������")
    public void testSuccessDequeueOneElement() {
        Queue queue = new Queue(7);
        Assertions.assertEquals(-1, queue.dequeue());
    }

    @Test
    @DisplayName("���� �� �������� ������ �������")
    public void testEmptyQueueCheck() {
        Queue queue = new Queue(7);
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("���� �� �������� ������ �������")
    public void testFullQueueCheck() {
        Queue queue = new Queue(7);
        Assertions.assertFalse(queue.isFull());
    }
}

