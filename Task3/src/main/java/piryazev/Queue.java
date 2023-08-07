package piryazev;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Реализация класса очереди Queue, для любых принимаемых объектов.
 */
public class Queue {
    /**
     * Массив объектов очереди
     */
    private Object[] queueArray;
    /**
     * Указатель на начало очереди
     */
    private int front;
    /**
     * Указатель на конец очереди
     */
    private int rear;
    /**
     * Текущий размер очереди
     */
    private int currentSize;
    /**
     * Стартовый размер очереди
     */
    private static final int START_SIZE = 10;
    /**
     * Множитель размера очереди
     */
    private static final int EXPAND_SIZE = 2;

    public Queue() {
        this.queueArray = new Object[START_SIZE];
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
    }

    /**
     * Метод добавляющий объект в очередь
     *
     * @param object добавляемый объект
     */
    public void enqueue(Object object) {
        if (currentSize == queueArray.length) {
            expandQueue();
        }
        rear = (rear + 1) % queueArray.length;
        queueArray[rear] = object;
        currentSize++;
    }

    /**
     * Метод удаляющий объект из очереди
     *
     * @return возвращает удаленный объект из очереди
     */
    public Object dequeue() {
        if (!isEmpty()) {
            Object object = queueArray[front];
            queueArray[front] = null;
            front = (front + 1) % queueArray.length;
            currentSize--;
            return object;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Метод, проверяющий пустая ли очередь
     *
     * @return True - очередь пуста, False - очередь не пуста
     */
    public Boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Метод, возвращающий первый элемент в списке очереди без удаления
     *
     * @return первый объект в очереди
     */
    public Object top() {
        if (!isEmpty()) {
            return queueArray[front];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Метод для расширения очереди
     */
    public void expandQueue() {
        int newSize = queueArray.length * EXPAND_SIZE;
        Object[] newQueueArray = new Object[newSize];

        for (int i = 0; i < currentSize; i++) {
            newQueueArray[i] = queueArray[(front + i) % queueArray.length];
        }

        queueArray = newQueueArray;
        front = 0;
        rear = currentSize - 1;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "queueArray=" + Arrays.toString(queueArray) +
                '}';
    }

    public int getCurrentSize() {
        return currentSize;
    }
}
