package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Класс тестирования циклической очереди
 */
public class CircularQueueTest {
    /**
     * Код возврата ошибки
     */
    private static final int ERROR_CODE = -1;
    /**
     * Код возврата при успешном выполнении функции
     */
    private static final int SUCCESS_CODE = 1;
    /**
     * максимальный размер очереди
     */
    private static final int MAX_SIZE = 5;

    @Test
    @DisplayName("Тест на успешное добавление элемента в очередь")
    public void testSuccessEnqueueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        Assertions.assertEquals(SUCCESS_CODE, circularQueue.enqueue(55));
    }

    @Test
    @DisplayName("Тест на успешное добавление элемента в очередь в начало массива на место удаленного элемента")
    public void testSuccessCircularEnqueueStartArrayOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            circularQueue.enqueue(i);
        }
        circularQueue.dequeue();
        Assertions.assertEquals(SUCCESS_CODE, circularQueue.enqueue(MAX_SIZE));
    }

    @Test
    @DisplayName("Тест на неудачную попытку добавления элемента в очередь")
    public void testFailEnqueueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            circularQueue.enqueue(i);
        }
        Assertions.assertEquals(ERROR_CODE, circularQueue.enqueue(MAX_SIZE));
    }

    @Test
    @DisplayName("Тест на успешное удаление элемента из очереди")
    public void testSuccessDequeueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        for (int i = 0; i != MAX_SIZE; i++) {
            circularQueue.enqueue(i);
        }
        Assertions.assertEquals(SUCCESS_CODE, circularQueue.dequeue());
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления элемента из очереди")
    public void testFailDequeueOneElement() {
        CircularQueue circularQueue = new CircularQueue(MAX_SIZE);
        Assertions.assertEquals(ERROR_CODE, circularQueue.dequeue());
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
        Assertions.assertEquals(null, circularQueue.top());
    }
}
