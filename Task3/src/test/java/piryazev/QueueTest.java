package piryazev;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class QueueTest {
    @Test
    @DisplayName("Checking that the queueArray expanded after start size")
    void testSuccessExpandQueue() {
        Queue queue = new Queue();
        for (int i = 0; i < 17; i++) {
            queue.enqueue(i);
        }
        Assertions.assertEquals(17, queue.getCurrentSize());
    }

    @Test
    @DisplayName("Checking the number of objects after enqueue")
    void testCurrentNumberOfObjectsInQueueAfterEnqueue() {
        Queue queue = new Queue();
        queue.enqueue("abcdef");
        queue.enqueue(2);
        queue.enqueue('3');
        Assertions.assertEquals(3, queue.getCurrentSize());
    }

    @Test
    @DisplayName("Checking that the NoSuchElementException is working properly in dequeue method")
    void testSuccessOutputExceptionOnNoSuchElementDequeue() {
        Queue queue = new Queue();
        Assertions.assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Checking the number of objects after dequeue")
    void testCurrentNumberOfObjectsInQueueAfterDequeue() {
        Queue queue = new Queue();
        queue.enqueue("abcdef");
        queue.enqueue("abcde");
        queue.dequeue();
        Assertions.assertEquals(1, queue.getCurrentSize());
    }

    @Test
    @DisplayName("Checking that the NoSuchElementException is working properly in top method")
    void testSuccessOutputExceptionOnNoSuchElementTop() {
        Queue queue = new Queue();
        Assertions.assertThrows(NoSuchElementException.class, queue::top);
    }

    @Test
    @DisplayName("Checking correct return object")
    void testCorrectObjectReturn() {
        Queue queue = new Queue();
        queue.enqueue(100);
        Object o = queue.top();
        Assertions.assertEquals(100, o);
    }

    @Test
    @DisplayName("Checking isEmpty function on True statement")
    void testTrueStatementIsEmpty() {
        Queue queue = new Queue();
        Assertions.assertEquals(Boolean.TRUE, queue.isEmpty());
    }

    @Test
    @DisplayName("Checking isEmpty function on False statement")
    void testFalseStatementIsEmpty() {
        Queue queue = new Queue();
        queue.enqueue(44);
        Assertions.assertEquals(Boolean.FALSE, queue.isEmpty());
    }

    @Test
    @DisplayName("Checking queue after enqueue some objects and after dequeue it")
    void testQueueAfterEnqueueAndDequeue() {
        Queue queue = new Queue();

        for (int i = 0; i < 7; i++) {
            queue.enqueue(i);
        }

        queue.dequeue(); //удаляем 0
        queue.dequeue(); //удаляем 1
        Object expected = "Queue{queueArray=[null, null, 2, 3, 4, 5, 6, null, null, null]}";
        Assertions.assertEquals(expected.toString(), queue.toString());
    }
}
