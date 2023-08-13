package druzhinin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {
    @Test
    @DisplayName("Тест на получение исключения NegativeArraySizeException" +
            " при создании очереди с отрицательным максимальным размером")
    public void testNegativeArraySizeExceptionWhenCreateQueueWithNegativeSize() {
        Assertions.assertThrows(NegativeArraySizeException.class, () -> new Queue(-1));
    }

    @Test
    @DisplayName("Тест на успешное создание очереди с положительным максимальным размером")
    public void testSuccessCreateQueue() {
        Object[] expected = new Object[3];
        Queue queue = new Queue(3);
        Assertions.assertArrayEquals(expected, queue.toArray());
    }

    @Test
    @DisplayName("Тест на успешную вставку элементов в незаполненную очередь")
    public void testSuccessEnqueueIntoNotFullQueue() {
        Object o1 = new Object();
        Object o2 = new Object();

        Object[] expected = {o1, o2};
        Queue queue = new Queue(2);
        queue.enqueue(o1);
        queue.enqueue(o2);

        Assertions.assertArrayEquals(expected, queue.toArray());
    }

    @Test
    @DisplayName("Тест на получение исключения IndexOutOfBoundsException" +
            " при вставке элемента в заполненную очередь")
    public void testIndexOutOfBoundsExceptionWhenEnqueueFullQueue() {
        Queue queue = new Queue(2);
        queue.enqueue(new Object());
        queue.enqueue(new Object());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> queue.enqueue(new Object()));
    }

    @Test
    @DisplayName("Тест на получение исключения IndexOutOfBoundsException" +
            " при удалении элемента из пустой очереди")
    public void testIndexOutOfBoundsExceptionWhenDequeueEmptyQueue() {
        Queue queue = new Queue(3);
        Assertions.assertThrows(IndexOutOfBoundsException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Тест на удаление элемента из непустой очереди")
    public void testSuccessDequeueFromNotEmptyQueue() {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();

        Object[] expected = {o4, o2, o3};

        Queue queue = new Queue(3);
        queue.enqueue(o1);
        queue.enqueue(o2);
        queue.enqueue(o3);
        queue.dequeue();
        queue.enqueue(o4);

        Assertions.assertArrayEquals(expected, queue.toArray());
    }

    @Test
    @DisplayName("Тест на проверку пустой очереди на пустоту")
    public void testEmptyQueueIsEmptyIsTrue() {
        Queue queue = new Queue(3);
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест на проверку непустой очереди на пустоту")
    public void testNotEmptyQueueIsEmptyIsFalse() {
        Queue queue = new Queue(3);
        queue.enqueue(new Object());
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Тест на равенство null первого с начала элемента пустой очереди")
    public void testEmptyQueueTopElementIsNull() {
        Queue queue = new Queue(3);
        Assertions.assertNull(queue.top());
    }

    @Test
    @DisplayName("Тест на правильное получение первого с начала элемента непустой очереди")
    public void testNotEmptyQueueTopIsCorrect() {
        Object o1 = new Object();
        Object o2 = new Object();

        Queue queue = new Queue(3);
        queue.enqueue(o1);
        queue.enqueue(o2);

        Assertions.assertEquals(o1, queue.top());
    }

    @Test
    @DisplayName("Тест на успешное получение копии массива элементов очереди")
    public void testSuccessGettingCopyFromToArray() {
        Object o = new Object();

        Queue queue = new Queue(3);
        Object[] copy = queue.toArray();
        copy[0] = o;
        queue.enqueue(o);

        Assertions.assertNotEquals(copy, queue.toArray());
        Assertions.assertArrayEquals(copy, queue.toArray());
    }

    @Test
    @DisplayName("Тест на правильное получение строки содержимого пустой очереди")
    public void testCorrectStringFromToStringOnEmptyQueue() {
        String expected = "Queue{}";

        Queue queue = new Queue(5);
        Assertions.assertEquals(expected, queue.toString());
    }

    @Test
    @DisplayName("Тест на правильное получение строки содержимого непустой очереди")
    public void testCorrectStringFromToStringOnNotEmptyQueue() {
        String expected = "Queue{2, 3, 4}";

        Queue queue = new Queue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.dequeue();
        Assertions.assertEquals(expected, queue.toString());
    }
}
