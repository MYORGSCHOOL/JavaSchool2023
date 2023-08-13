package sevostyanov;

public class Queue {
    /**
     * Максимальный размер очереди
     */
    private int maxSize;
    /**
     * Массив для хранения элементов очереди
     */
    private Object[] queueArray;
    /**
     * Индекс первого элемент в очереди
     */
    private int front;
    /**
     * Индекс последнего элемента в очереди
     */
    private int rear;
    /**
     * Текущий размер очереди
     */
    private int currentSize;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.queueArray = new Object[maxSize];
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
    }

    /**
     * Метод проверяет заполненность очереди
     *
     * @return Возвращает тру если очередь пуста
     */
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    /**
     * Метод проверяет заполненность очереди
     *
     * @return Возвращает тру если очередь заполнена
     */
    public boolean isFull() {
        return (currentSize == maxSize);
    }

    /**
     * Метод добавляет элемент в конец очереди, если очередь
     * звполнена выводит в терминальный вывод очередь полна , если очередь свободна
     * добавлет элемент в конец
     *
     * @param item Елемент который будет добавлен
     */
    public void enqueue(Object item) {
        if (isFull()) {
            System.out.println("Очередь полна. Невозможно добавить элемент.");
            return;
        }
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queueArray[++rear] = item;
        currentSize++;
    }

    /**
     * Методо который удаляет элемент из начала очереди, если очередь пуста выводит
     * в терминальный вывод что невозможно удалить, если очерь не пуста
     * метод удаляет элемент из начала
     *
     * @return Элемент который удаляем из начала
     */
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста, невозможно удалить.");
            return null;
        }
        Object removedItem = queueArray[front];
        queueArray[front] = null;
        if (front == maxSize - 1) {
            front = 0;
        } else {
            front++;
        }
        currentSize--;
        return removedItem;
    }

    /**
     * Метод который возвращает первый элемент из очереди, если очередь пуста
     * выводит сообщение в терминальный вывод что пусто
     *
     * @return Возвращает первый элемент в очереди
     */
    public Object top() {
        if (isEmpty()) {
            System.out.println("Пусто");
            return null;
        }
        return queueArray[front];
    }
}
