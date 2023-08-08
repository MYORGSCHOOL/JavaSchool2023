package volovnik;

import volovnik.exceptions.QueueIsEmptyException;

import java.util.Arrays;

public class Queue {

    private int queueStart = 0;
    private int queueEnd = -1;
    private int objectsInQueue = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int EXPAND_MULTIPLIER = 2;
    private Object[] queue;

    public Queue() {
        queue = new Object[DEFAULT_SIZE];
    }

    public Queue(int initialSize) {
        queue = new Object[initialSize];
    }

    /**
     * Метод добавляет элемент в начало очереди
     *
     * @param object добавляемый элемент
     */
    public void enqueue(Object object) {
        if (objectsInQueue == queue.length) {
            int expandedSize = queue.length * EXPAND_MULTIPLIER;
            Object[] expandedQueue = new Object[expandedSize];
            for (int i = 0; i < objectsInQueue; i++) {
                expandedQueue[i] = queue[(queueStart + i) % queue.length];
            }
            queue = expandedQueue;
            queueStart = 0;
            queueEnd = objectsInQueue - 1;
        }
        queueEnd = (queueEnd + 1) % queue.length;
        queue[queueEnd] = object;
        objectsInQueue += 1;
    }

    /**
     * Метод удаляет элемент из начала очереди
     *
     * @throws QueueIsEmptyException очередь пустая
     */
    public void dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException();
        }
        queue[queueStart] = null;
        queueStart = (queueStart + 1) % queue.length;
        objectsInQueue -= 1;
    }

    /**
     * Метод проверяет на пустую очередь
     *
     * @return true - если очередь пустая, иначе false
     */
    public boolean isEmpty() {
        return objectsInQueue == 0;
    }

    /**
     * Метод возвращает первый элемент очереди
     *
     * @return первый элемент очереди
     */
    public Object top() {
        return isEmpty() ? null : queue[queueStart];
    }

    public Object[] getAll() {
        return Arrays.copyOf(queue, queue.length);
    }
}
