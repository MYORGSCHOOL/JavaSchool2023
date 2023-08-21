package savinskiy;

import java.util.Arrays;

/**
 * Класс Очередь (кольцевая)
 */
public class Queue {
    /**
     * Массив для хранения элементов очереди
     */
    private final Object[] queue;
    /**
     * Элемент указывающий на начало очереди
     */
    private int front;
    /**
     * Элемент указывающий на конец очереди
     */
    private int rear;
    /**
     * Кол-во элементов очереди
     */
    private int size;

    public Queue(int capacity) {
        this.queue = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Метод вставляет элемент в конец очереди
     *
     * @param element который будет вставлен в конец очереди
     */
    public void enqueue(Object element) {
        if (size == queue.length) {
            throw new IllegalStateException("Очередь заполена");
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        size++;
    }

    /**
     * Метод удаляет элемент из начала очереди
     *
     * @return удаленный элемент
     */
    public Object deque() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }

        Object element = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return element;
    }

    /**
     * Метод проверяет пуста ли очередь
     *
     * @return true - очередь пуста, либо false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод возвращает первый элемент очереди
     *
     * @return первый элемент очерель
     */
    public Object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return queue[front];
    }

    /**
     * Вовзвращает копию массива очереди
     *
     * @return копия массива элементов
     */
    public Object[] getAll() {
        return Arrays.copyOf(queue, size);
    }
}
