package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;

/**
 * Класс тестирования циклической очереди
 */
public class CircularQueueTest {
    /**
     * максимальный размер очереди
     */
    private static final int MAX_SIZE = 5;

    @Test
    @DisplayName("Тест на успешное добавление элемента в очередь")
    public void testSuccessEnqueueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        circularQueue.enqueue(123);
        Assertions.assertEquals(123, circularQueue.top());
    }

    @Test
    @DisplayName("Тест на успешное добавление элемента в очередь в начало массива на место удаленного элемента")
    public void testSuccessCircularEnqueueStartArrayOneElement() {
        Object[] checkArray = new Object[5];
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            if (i != 0) {
                checkArray[i] = i;
            }
            circularQueue.enqueue(i);
        }
        circularQueue.dequeue();
        circularQueue.enqueue(MAX_SIZE);
        checkArray[0] = MAX_SIZE;
        Assertions.assertArrayEquals(checkArray, circularQueue.getAll());
    }

    @Test
    @DisplayName("Тест на неудачную попытку добавления элемента в очередь")
    public void testFailEnqueueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            circularQueue.enqueue(i);
        }
        Assertions.assertThrows(IllegalStateException.class, () -> circularQueue.enqueue(MAX_SIZE));
    }

    @Test
    @DisplayName("Тест на успешное удаление элемента из очереди")
    public void testSuccessDequeueOneElement() {
        int i;
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (i = 0; i != MAX_SIZE; i++) {
            circularQueue.enqueue(i);
        }
        i = 0;
        Assertions.assertEquals(i, circularQueue.top());
        circularQueue.dequeue();
        Assertions.assertEquals(++i, circularQueue.top());
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления элемента из очереди")
    public void testFailDequeueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        Assertions.assertThrows(NoSuchElementException.class, () -> circularQueue.dequeue());
    }

    @Test
    @DisplayName("Тест на проверку пустоты очереди - очередь пустая")
    public void testCheckQueueIsEmpty() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        Assertions.assertTrue(circularQueue.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку пустоты очереди - очередь не пустая")
    public void testCheckQueueIsNotEmpty() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        circularQueue.enqueue(55.5);
        Assertions.assertFalse(circularQueue.isEmpty());
    }

    @Test
    @DisplayName("Тест на получение первого элемента очереди")
    public void testSuccessQueueGetFirstElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            circularQueue.enqueue(i);
        }
        Assertions.assertEquals(0, circularQueue.top());
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения первого элемента очереди")
    public void testFailQueueGetFirstElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        Assertions.assertThrows(NoSuchElementException.class, () -> circularQueue.top());
    }
}
