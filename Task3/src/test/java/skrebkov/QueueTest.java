package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueTest {

    @Test
    @DisplayName("Проверка исключения при удаление элемента из очереди")
    public void testExceptionDeleteFromQueue(){
        Queue queue = new Queue();
        Assertions.assertThrows(IndexOutOfBoundsException.class, queue::dequeue);
    }
    @Test
    @DisplayName("Проверка метода isNull")
    public void testSuccessIsNull(){
        Queue queue = new Queue();
        Assertions.assertTrue(queue.isEmpty());
        queue.enqueue("Проверка метода isNull");
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("Проверка метода top")
    public void testSuccessTopFromQueue(){
        Queue queue = new Queue();
        queue.enqueue(1);
        Assertions.assertEquals(1, queue.top());
        queue.enqueue(99d);
        queue.dequeue();
        Assertions.assertEquals(99d,queue.top());
    }

    @Test
    @DisplayName("Проверка исключения в методе top")
    public void testExceptionTop(){
        Queue queue = new Queue();
        Assertions.assertThrows(IndexOutOfBoundsException.class, queue::top);
    }

    @Test
    @DisplayName("Проверка метода вывода всего массива")
    public void testEqualsObjectArrInGetAll(){
        Queue queue = new Queue();
        String[] arr = {"first","second"};
        queue.enqueue(arr[0]);
        queue.enqueue(arr[1]);
        Assertions.assertArrayEquals(arr, queue.getAll());
     }
}
