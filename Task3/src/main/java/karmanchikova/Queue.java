package karmanchikova;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс для реализации очереди на массивах Object
 */
public class Queue {
    /**
     * Массив для хранения элементов очереди
     */
    private final Object[] arrQueue;
    /**
     * Указывает на первый элемент в очереди
     */
    private int front;
    /**
     * Указывает на последний элемент в очереди
     */
    private int last;
    /**
     * Максимальный размер очереди
     */
    private int capacity;
    /**
     * Текущий размер очереди
     */
    private int count;

    public Queue(int size) {
        this.arrQueue = new Object[size];
        this.capacity = size;
        this.front = 0;
        this.last = -1;
        this.count = 0;
    }

    /**
     * Функция для добавления элемента в queue
     *
     * @param newElement элемент который необходимо добавить в конец очереди
     */
    public void enqueue(Object newElement) {
        if (isFull()) {
            capacity+=1;
        }
        System.out.println("Добавлен элемент: " + newElement);
        last = (last + 1) % capacity;
        arrQueue[last] = newElement;
        count++;
    }

    /**
     * Функция для удаления первого элемента из очереди
     */
    public void dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Object deleteElement = arrQueue[front];
        System.out.println("Удален элемент: " + deleteElement);
        arrQueue[front] = null;
        front = (front + 1) % capacity;
        count--;
    }

    /**
     * Функция для возврата первого элемента queue
     *
     * @return возвращает первый элемент в очереди
     */
    public Object top() {
        if (isEmpty()) {
            throw new NegativeArraySizeException();
        }
        return arrQueue[front];
    }

    /**
     * Функция для возврата объектов очереди
     *
     * @return возвращает объекты в очереди
     */
    public Object[] getAll() {
        return arrQueue.clone();
    }

    /**
     * Функция для проверки опустошенности очереди
     *
     * @return в зависимости от результата либо true, либо false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Функция для проверки заполненности очереди
     *
     * @return в зависимости от результата либо true, либо false
     */
    public boolean isFull() {
        return count == capacity;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "arrObject=" + Arrays.toString(arrQueue) +
                ", front=" + front +
                ", rear=" + last +
                ", capacity=" + capacity +
                ", count=" + count +
                '}';
    }
}