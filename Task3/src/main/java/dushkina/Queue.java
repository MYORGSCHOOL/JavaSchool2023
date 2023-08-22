package dushkina;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий очередь (на массиве Object!) с основными операциями
 */
public class Queue {
    /**
     * Поле для хранения очереди объектов
     */
    private final Object[] queueElements;
    /**
     * Максимальное количество элементов в очереди
     */
    private int maxCount;
    /**
     * Текущее количество элементов в очереди
     */
    private int count;
    /**
     * Указатель на первый элемент в очереди
     */
    private int front;
    /**
     * Указатель на последний элемент в очереди
     */
    private int back;

    public Queue(int maxCount) {
        if (maxCount < 0) {
            throw new NegativeArraySizeException();
        }
        this.maxCount = maxCount;
        this.queueElements = new Object[this.maxCount];
        this.count = 0;
        this.front = 0;
        this.back = -1;
    }

    /**
     * Метод, вставляющий элемент в конец очереди
     *
     * @param o - элемент, который нужно вставить в конец очереди
     * @throws IndexOutOfBoundsException - выброс исключения, когда очередь заполнена
     */
    public void enqueue(Object o) {
        if (maxCount > count) {
            back++;
            this.queueElements[back] = o;
            count++;
        } else {
            throw new IndexOutOfBoundsException("Очередь заполнена");
        }
    }

    /**
     * Метод, выводящий элемент из начала очереди
     *
     * @return элемент, который вывели из очереди
     * @throws NoSuchElementException - выброс исключения, когда очередь пуста
     */
    public Object dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Object deleteElement = top();
        System.arraycopy(queueElements, front + 1, queueElements, front, --count);
        queueElements[back] = null;
        back--;
        return deleteElement;
    }

    /**
     * Метод, проверяющий пуста ли очередь
     *
     * @return boolean, в зависимости от результата
     */
    public Boolean isEmpty() {
        return count == 0;
    }

    /**
     * Метод, возвращающий первый элемент очереди для просмотра
     *
     * @return первый элемент очереди, если очередь не пуста, иначе null
     */
    public Object top() {
        if (!this.isEmpty()) {
            return queueElements[front];
        } else {
            return null;
        }
    }

    /**
     * Метод, показывающий, что находится в очереди
     *
     * @return массив элементов, находящихся в очереди
     */
    public Object[] getAll() {
        Object[] data = new Object[count];
        System.arraycopy(queueElements, 0, data, 0, count);
        return data;
    }

    /**
     * Метод, выводящий данные класса в строковом виде
     *
     * @return строка с полями класса и их значениями
     */
    @Override
    public String toString() {
        return "Queue{" +
                "queueElements=" + Arrays.toString(queueElements) +
                ", maxCount=" + maxCount +
                ", count=" + count +
                ", front=" + front +
                ", back=" + back +
                '}';
    }
}
