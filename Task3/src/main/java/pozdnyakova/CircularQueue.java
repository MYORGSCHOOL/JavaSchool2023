package pozdnyakova;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий циклическую очередь на массиве Object
 */
public class CircularQueue {
    /**
     * массив для хранения элементов очереди
     */
    private final Object array[];
    /**
     * максимальный возможный размер циклической очереди
     */
    private final int maxSize;
    /**
     * индекс первого элемента в очереди
     */
    private int front;
    /**
     * индекс последнего элемента в очереди
     */
    private int rear;

    public CircularQueue(int maxSize) {
        array = new Object[maxSize];
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
    }

    /**
     * Метод для вставки элемента в конец циклической очереди
     *
     * @param object элемент, который нужно вставить
     */
    public void enqueue(Object object) {
        if ((front == 0 && rear == maxSize - 1) || (rear == front - 1)) {
            throw new IllegalStateException("Циклическая очередь заполнена!");
        } else {
            if (front != 0 && rear == maxSize - 1) {
                rear = 0;
            } else {
                rear++;
            }
            if (front == -1) {
                front = 0;
            }
            array[rear] = object;
        }
    }

    /**
     * Метод для удаление элемента из начала циклической очереди
     */
    public void dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (front == maxSize - 1 && rear != front) {
                front = 0;
            } else if (front == rear) {
                front = -1;
            } else {
                array[front] = null;
                front++;
            }
        }
    }

    /**
     * Метод для проверки, пуста ли циклическая очередь
     *
     * @return true - если очередь пустая
     * false - если очередь не пустая
     */
    public boolean isEmpty() {
        return (front == -1);
    }

    /**
     * Метод для получения первого элемента очереди
     *
     * @return объект с начала очереди - если очередь не пустая
     */
    public Object top() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return array[front];
        }
    }

    /**
     * Метод получения объектов в очереди
     *
     * @return копия массива очереди
     */
    public Object[] getAll() {
        return Arrays.copyOf(array, array.length);
    }
}
