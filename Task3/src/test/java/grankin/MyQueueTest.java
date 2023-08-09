package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyQueueTest {

    @Test
    @DisplayName("Удачное добавление трех элементов")
    public void testSuccessEnqueueThreeElements() {
        MyQueue queue1 = new MyQueue(3);
        queue1.enqueue(0);
        queue1.enqueue(1);
        queue1.enqueue(2);
        Object[] integers = new Object[3];
        integers[0] = 0;
        integers[1] = 1;
        integers[2] = 2;
        Assertions.assertArrayEquals(integers, queue1.getArray());
    }

    @Test
    @DisplayName("Неудачное добавление в переполненную очередь")
    public void testUnSuccessEnqueueFourElements() {
        MyQueue queue1 = new MyQueue(3);
        queue1.enqueue(0);
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);      // Данный элемент не добавится в очередь!
        Object[] integers = new Object[3];
        integers[0] = 0;
        integers[1] = 1;
        integers[2] = 2;
        Assertions.assertArrayEquals(integers, queue1.getArray());
    }

    @Test
    @DisplayName("Удачное забирание элемента")
    public void testSuccessDequeueElement() {
        MyQueue queue1 = new MyQueue(3);
        queue1.enqueue(0);
        queue1.enqueue(1);
        queue1.enqueue(2);
        Assertions.assertEquals(1, queue1.dequeue());
    }

    @Test
    @DisplayName("Забирание несуществующего элемента")
    public void testUnSuccessDequeueElement() {
        MyQueue queue1 = new MyQueue(3);
        Assertions.assertEquals(-1, queue1.dequeue());
    }

    @Test
    @DisplayName("Удачное получение элемента")
    public void testSuccessTopElement() {
        MyQueue queue1 = new MyQueue(3);
        queue1.enqueue(0);
        queue1.enqueue(1);
        queue1.enqueue(2);
        Assertions.assertEquals(0, queue1.top());
    }

    @Test
    @DisplayName("Получение несуществующего элемента")
    public void testUnSuccessTopElement() {
        MyQueue queue1 = new MyQueue(3);
        Assertions.assertEquals(null, queue1.top());
    }

    @Test
    @DisplayName("Очередь пустая")
    public void testIsEmpty() {
        MyQueue queue1 = new MyQueue(3);
        Assertions.assertEquals(true, queue1.isEmpty());
    }

    @Test
    @DisplayName("Очередь не пустая")
    public void testIsNotEmpty() {
        MyQueue queue1 = new MyQueue(3);
        queue1.enqueue(0);
        Assertions.assertEquals(false, queue1.isEmpty());
    }

    @Test
    @DisplayName("Очередь полная")
    public void testIsFull() {
        MyQueue queue1 = new MyQueue(1);
        queue1.enqueue(0);
        Assertions.assertEquals(true, queue1.isFull());
    }

    @Test
    @DisplayName("Очередь не полная")
    public void testIsNotFull() {
        MyQueue queue1 = new MyQueue(3);
        queue1.enqueue(0);
        Assertions.assertEquals(false, queue1.isFull());
    }
}
