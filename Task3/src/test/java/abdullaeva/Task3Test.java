package abdullaeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;

public class Task3Test {

    @DisplayName("Тест проверяет корректную работу метода, определяющего пустоту стэка")
    @Test
    public void testIsEmptyStack() {
        Stack stackTest = new Stack(3);
        Assertions.assertTrue(stackTest.isEmpty());
    }

    @DisplayName("Тест проверяет работу метода для определения пустоты на непустом стэке")
    @Test
    public void testIsEmptyErrorStack() {
        Stack stackTest = new Stack(3);
        stackTest.push(3);
        Assertions.assertFalse(stackTest.isEmpty());
    }

    @DisplayName("Тест проверяет корректность метода вставки объекта в стэк")
    @Test
    public void testPushStack() {
        Stack stackTest = new Stack(3);
        final Object add = 'b';
        Assertions.assertEquals(1, stackTest.push(add));
    }

    @DisplayName("Тест проверяет работу метода вставки объекта в стэк, если стэк переполнен")
    @Test
    public void testPushErrorStack() {
        Stack stackTest = new Stack(3);
        stackTest.push(1);
        stackTest.push(2);
        stackTest.push(3);
        final Object add = 'b';
        Assertions.assertNotEquals(-1, stackTest.push(add));
    }

    @DisplayName("Тест проверяет корректность метода получения верхнего объекта стэка")
    @Test
    public void testTopStack() {
        Stack stackTest = new Stack(3);
        stackTest.push(5);
        Assertions.assertEquals(5, stackTest.top());
    }

    @DisplayName("Тест проверяет работу метода получения верхнего объекта стэка, если стэк пуст")
    @Test
    public void testTopErrorStack() {
        Stack stackTest = new Stack(3);
        Assertions.assertNull(stackTest.top());
    }

    @DisplayName("Тест проверяет корректность работы метода получения верхнего объекта стэка после его удаления")
    @Test
    public void testPopStack() {
        Stack stackTest = new Stack(3);
        Object add = 'b';
        stackTest.push(add);
        Assertions.assertEquals(add, stackTest.pop());
    }

    @DisplayName("Тест проверяет работу метода получения верхнего объекта стэка после его удаления, если стэк пуст")
    @Test
    public void testPopErrorStack() {
        Stack stackTest = new Stack(3);
        Assertions.assertNull(stackTest.pop());
    }

    @DisplayName("Тест проверяет корректность метода получения копии массива стэка")
    @Test
    public void testGetAllStack() {
        Stack stackTest = new Stack(3);
        Object[] cloneStack = new Object[3];
        stackTest.push(1);
        stackTest.push(2);
        stackTest.push("a");
        cloneStack[0] = 1;
        cloneStack[1] = 2;
        cloneStack[2] = "a";
        Assertions.assertArrayEquals(cloneStack, stackTest.getAll());
    }

    @DisplayName("Тест проверяет корректную работу метода, определяющего пустоту очереди")
    @Test
    public void testIsEmptyQueue() {
        Queue queueTest = new Queue(3);
        Assertions.assertTrue(queueTest.isEmpty());
    }

    @DisplayName("Тест проверяет работу метода для определения пустоты на непустой очереди")
    @Test
    public void testIsEmptyErrorQueue() {
        Queue queueTest = new Queue(3);
        queueTest.enQueue(5);
        Assertions.assertFalse(queueTest.isEmpty());
    }

    @DisplayName("Тест проверяет корректность метода вставки объекта в очередь")
    @Test
    public void testEnQueue() {
        Queue queueTest = new Queue(3);
        Object add = 'b';
        Assertions.assertEquals(1, queueTest.enQueue(add));
    }

    @DisplayName("Тест проверяет работу метода вставки объекта в очередь, если очередь переполнена")
    @Test
    public void testEnQueueError() {
        Queue queueTest = new Queue(3);
        queueTest.enQueue(1);
        queueTest.enQueue(2);
        queueTest.enQueue(3);
        Object add = 'b';
        Assertions.assertEquals(-1, queueTest.enQueue(add));
    }

    @DisplayName("Тест проверяет корректность метода получения первого объекта в очереди")
    @Test
    public void testTopQueue() {
        Queue queueTest = new Queue(3);
        queueTest.enQueue(5);
        Assertions.assertEquals(5, queueTest.top());
    }

    @DisplayName("Тест проверяет работу метода получения первого объекта в очереди, если очередь пуста")
    @Test
    public void testTopErrorQueue() {
        Queue queueTest = new Queue(3);
        Assertions.assertNull(queueTest.top());
    }

    @DisplayName("Тест проверяет корректность работы метода удаления объекта из начала очереди")
    @Test
    public void testDeQueue() {
        Queue queueTest = new Queue(3);
        queueTest.enQueue(5);
        Assertions.assertEquals(1, queueTest.deQueue());
    }

    @DisplayName("Тест проверяет работу метода удаления объекта из начала очереди, если очередь пуста")
    @Test
    public void testDeQueueError() {
        Queue queueTest = new Queue(3);
        Assertions.assertEquals(-1, queueTest.deQueue());
    }

    @DisplayName("Тест проверяет корректность метода получения копии массива очереди")
    @Test
    public void testGetAllQueue() {
        Queue queueTest = new Queue(3);
        Object[] cloneQueue = new Object[3];
        queueTest.enQueue(1);
        queueTest.enQueue(2);
        queueTest.enQueue("a");
        cloneQueue[0] = 1;
        cloneQueue[1] = 2;
        cloneQueue[2] = "a";
        Assertions.assertArrayEquals(cloneQueue, queueTest.getAll());
    }
}
