package grossu;


import java.util.Arrays;


/**
 * Класс Queue реализует ограниченный массив объектов, построенный в виде очереди по протоколу FIFO
 */
public class Queue {
    private Object[] queue;
    private int maxCount;
    private int count;
    private int front;
    private int rear;

    public Queue(int maxCount) {
        try {
            this.maxCount = maxCount;
            this.queue = new Object[this.maxCount];
            this.count = 0;
            this.front = 0;
            this.rear = -1;

        } catch (java.lang.NegativeArraySizeException e) {
            System.out.println("Negative size array");
        }
    }

    public Object[] getQueue() {
        return queue;
    }

    /**
     * Функция вставляет передаваемый элемент в конец очереди
     * Если очередь будет переполнена,
     * будет выброшено исключение ArrayIndexOutOfBoundsException
     *
     * @param o элемент, который необходимо добавить в конец очереди
     */
    public void enqueue(Object o) {
        try {
            rear = (rear + 1) % maxCount;
            queue[rear] = o;
            count++;
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("The queue is full");
        }
    }


    /**
     * Функция удаляет элемент из начала очереди
     * Если очередь пуста, то об этом будет сообщено пользователю
     *
     * @return удаленный элемент в случае удачного удаления, null, если стек пуст
     */
    public Object dequeue() {
        if (!this.isEmpty()) {
            Object temp = queue[front];
            queue[front] = null;
            front = (front + 1) % maxCount;
            count--;
            return temp;
        }
        System.out.println("The queue is empty");
        return null;

    }

    /**
     * Функция, которая проверяет очередь на пустоту
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
            return queue[0];
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
                "queue=" + Arrays.toString(queue) +
                ", maxCount=" + maxCount +
                ", count=" + count +
                ", front=" + front +
                ", rear=" + rear +
                '}';
    }
}
