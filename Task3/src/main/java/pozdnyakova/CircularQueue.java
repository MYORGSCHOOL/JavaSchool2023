package pozdnyakova;

/**
 * Класс, реализующий циклическую очередь на массиве Object
 */
public class CircularQueue {
    /**
     * Код возврата ошибки
     */
    private static final int ERROR_CODE = -1;
    /**
     * Код возврата при успешном выполнении функции
     */
    private static final int SUCCESS_CODE = 1;

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
     * @return 1 - если удалось вставить элемент
     * -1 - если не удалось вставить из-за того, что очередь достигла максимального размера
     */
    public int enqueue(Object object) {
        if ((front == 0 && rear == maxSize - 1) || (rear == front - 1)) {
            return ERROR_CODE;
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
            return SUCCESS_CODE;
        }
    }

    /**
     * Метод для удаление элемента из начала циклической очереди
     *
     * @return 1 - если очередь не пустая, и элемент был удален
     * -1 - если очередь пустая, и удаления не произошло
     */
    public int dequeue() {
        if (isEmpty()) {
            return ERROR_CODE;
        } else {
            if (front == maxSize - 1 && rear != front) {
                front = 0;
            } else if (front == rear) {
                front = -1;
            } else {
                array[front] = null;
                front++;
            }
            return SUCCESS_CODE;
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
     * null - если очередь пустая
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        } else {
            return array[front];
        }
    }
}
