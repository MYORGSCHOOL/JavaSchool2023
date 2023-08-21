package babadzhanov;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс очереди Object, основанный на FIFO
 */
public class Queue {

    /**
     * Массив для хранения объектов в очереди
     */
    private final Object[] queueArray;

    /**
     * Максимальное количество объектов в очереди
     */
    private final int capacity;

    /**
     * Указатель на индекс первого объект в очереди
     */
    private int front;

    /**
     * Указатель на индекс последнего объекта в очереди
     */
    private int rear;

    /**
     * Текущее кол-во объектов в очереди
     */
    private int count;

    public Queue(int capacity) {
        this.queueArray = new Object[capacity];
        this.capacity = capacity;
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    /**
     * Метод возвращает копию массива очереди
     *
     * @return Копия массива очереди
     */
    public Object[] toArray() {
        return queueArray.clone();
    }

    /**
     * Метод проверяет, что очередь пуста
     *
     * @return true - очередь пуста, false - очередь не пуста
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Метод проверяет, что очередь полностью заполнена
     *
     * @return true - очередь заполнена, false - очередь не заполнена
     */
    public Boolean isFull() {
        return (count == capacity);
    }

    /**
     * Метод вставляет элемент в конец очереди
     *
     * @param object объект вставляемый в очередь
     * @return Массив объектов
     * @throws IndexOutOfBoundsException, если очередь полностью заполнена
     */
    public Object[] enqueue(Object object) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Достигнут максимальный размер очереди");
        }
        rear = (rear + 1) % capacity;
        queueArray[rear] = object;
        count++;
        return queueArray;
    }

    /**
     * Метод удаляет элемент из начала очереди.
     *
     * @return Массив объектов
     * @throws NoSuchElementException, если очередь пуста
     */
    public Object[] dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста - нет элементов для удаления");
        }
        queueArray[front] = null;
        front = (front + 1) % capacity;
        count--;
        return queueArray;
    }

    /**
     * Метод возвращает первый элемент очереди
     *
     * @return Первый элемент очереди
     */
    public Object top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста - нет элементов для удаления");
        }
        return queueArray[front];
    }

    @Override
    public String toString() {
        return "Queue{" +
                "queueArray=" + Arrays.toString(queueArray) +
                ", capacity=" + capacity +
                ", front=" + front +
                ", rear=" + rear +
                ", count=" + count +
                '}';
    }
}
