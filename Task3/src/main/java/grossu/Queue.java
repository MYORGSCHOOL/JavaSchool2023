package grossu;


import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * Класс Queue реализует ограниченный массив объектов, построенный в виде очереди по протоколу FIFO
 */
public class Queue {
    /**
     * Поле для хранения очереди объектов
     */
    private final Object[] queueArray;
    /**
     * Максимальное количество объектов в очереди
     */
    private final int maxCount;
    /**
     * Текущее количество объектов в очереди
     */
    private int count;
    /**
     * Указатель на начало очереди
     */
    private int front;
    /**
     * Указатель на конец очереди
     */
    private int rear;

    public Queue(int maxCount) {
        if (maxCount >= 0) {
            this.maxCount = maxCount;
            this.queueArray = new Object[this.maxCount];
            this.count = 0;
            this.front = 0;
            this.rear = -1;
        } else {
            throw new NegativeArraySizeException();
        }
    }

    public Object[] toArray() {
        return queueArray.clone();
    }

    /**
     * Функция вставляет передаваемый элемент в конец очереди
     *
     * @param o элемент, который необходимо добавить в конец очереди
     * @throws IndexOutOfBoundsException, если очередь полностью заполнена
     */
    public void enqueue(Object o) {
        if (count != maxCount) {
            rear = (rear + 1) % maxCount;
            queueArray[rear] = o;
            count++;
        } else {
            throw new IndexOutOfBoundsException(rear);
        }
    }

    /**
     * Функция удаляет элемент из начала очереди и возвращает удаленный элемент
     *
     * @return удаленный элемент, если удаление прошло удачно
     * @throws ArrayIndexOutOfBoundsException, если очередь пуста
     */
    public Object dequeue() {
        if (!this.isEmpty()) {
            Object temp = queueArray[front];
            queueArray[front] = null;
            front = (front + 1) % maxCount;
            count--;
            return temp;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Функция, которая проверяет очередь на пустоту
     * и возвращает boolean в зависимости от результата
     *
     * @return true, если очередь пуста, иначе false
     */
    public Boolean isEmpty() {
        return count == 0;
    }

    /**
     * Функция, которая возвращает первый элемент очереди
     *
     * @return первый элемент очереди или null, если очередь пуста
     */
    public Object top() {
        if (!this.isEmpty()) {
            return queueArray[front];
        }
        return null;
    }

    /**
     * Функция для предоставления информации о состоянии очереди
     *
     * @return строку с полями класса Queue
     */
    @Override
    public String toString() {
        return "Queue{" +
                "queue=" + Arrays.toString(queueArray) +
                ", maxCount=" + maxCount +
                ", count=" + count +
                ", front=" + front +
                ", rear=" + rear +
                '}';
    }
}
