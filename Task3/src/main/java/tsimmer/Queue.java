package tsimmer;

import java.util.Arrays;

/**
 * Реализует набор методов для Queue
 */
public class Queue {
    /**
     * Максимальный размер очереди
     */
    private final int maxSize;
    /**
     * Массив объектов
     */
    private final Object[] queueArray;
    /**
     * Начало очереди
     */
    private int front;
    /**
     * Конец очереди
     */
    private int rear;
    /**
     * Текущее количество элементов в очереди
     */
    private int currentSize;
    /**
     * Значение которое возвращает метод в случае ошибки
     */
    private static final int RETURN_ERR = -1;
    /**
     * Значение которое возвращает метод в случае успешного выполнения
     */
    private static final int RETURN_SUCCESS = 1;


    public Queue(int size) {
        maxSize = size;
        queueArray = new Object[maxSize];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    /**
     * Метод добавляет элемент в конец очереди.
     * Этот метод проверяет заполненность очереди.
     * Если очередь свободна, добавляет элемент.
     * Реализована круговая очередь, т.е возврат к началу массива
     *
     * @param item указывается элемент, который будет добавлен
     * @return -1 если ошибка вставки и 1 если вставка прошла успешно
     */
    public Object enqueue(Object item) {
        if (isFull()) {
            System.out.println("Очередь полна. Невозможно добавить элемент.");
            return RETURN_ERR;
        }
        rear = (rear + 1) % maxSize;
        queueArray[rear] = item;
        currentSize++;
        return RETURN_SUCCESS;
    }

    /**
     * Метод удаляет элемент из начала очереди.
     * Метод проверяет имеются ли элементы в очереди.
     * Если очередь содержит элементы, метод удаляет элемент из начала.
     *
     * @return удаленный элемент
     */
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста. Невозможно удалить элемент.");
            return RETURN_ERR;
        }
        Object removedItem = queueArray[front];
        front = (front + 1) % maxSize;
        currentSize--;
        return removedItem;
    }

    /**
     * Метод возвращает первый элемент из очереди.
     * В методе реализована проверка заполненности очереди.
     *
     * @return первый элемент очереди
     */
    public Object top() {
        if (isEmpty()) {
            System.out.println("Очередь пуста.");
            return null;
        }
        return queueArray[front];
    }

    /**
     * Метод проверяет заполненность очереди
     *
     * @return true, если очередь пуста
     */
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    /**
     * Метод проверяет заполненность очереди
     *
     * @return true, если очередь заполнена
     */
    public boolean isFull() {
        return (currentSize == maxSize);
    }

    public Object[] getAll() {

        int size = 0;
        for (int i = 0; i < queueArray.length; i++) {
            if (queueArray[size] != null) {
                size++;
            }
        }
        return Arrays.copyOf(queueArray, size);
    }

}
