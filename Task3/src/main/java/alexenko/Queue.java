package alexenko;

/**
 * Класс очередь. Реализован функционал круговой очереди.
 */
public class Queue {

    /**
     * размер очереди
     */
    private final int SIZE;

    /**
     * очередь
     */
    private final Object[] array;

    /**
     * индекс начала очереди
     */
    private int front;

    /**
     * индекс конца очереди
     */
    private int rear;

    /**
     * Конструктор очереди. Инициализирует поля начальными значениями.
     * front и rear имеют значение -1, так как очередь пуста
     *
     * @param size размер создаваемой очереди
     */
    public Queue(int size) {
        this.SIZE = size;
        this.array = new Object[this.SIZE];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * Метод вставляет элемент в конец очереди
     *
     * @param o элемент, который вставляем
     * @throws RuntimeException добавление элемента в полную очередь
     */
    public void enqueue(Object o) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException("Переполнение очереди");
        }
        if (this.front == -1) {
            this.front = 0;
        }
        this.rear += 1;
        if (this.rear > SIZE - 1) {
            this.rear = 0;
        }
        this.array[this.rear] = o;
    }

    /**
     * Метод удаляет элемент с начала очереди
     *
     * @throws RuntimeException удаление элемента из пустой очереди
     */
    public void dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Очередь пустая");
        }
        this.array[this.front] = null;
        if (this.front == this.rear) {
            this.front = -1;
            this.rear = -1;
            return;
        }
        this.front += 1;
        if (this.front > SIZE - 1) {
            this.front = 0;
        }
    }

    /**
     * Метод проверяет очередь на пустоту
     *
     * @return true если очередь пустая, false если не пустая
     */
    public boolean isEmpty() {
        if (this.front == -1) {
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет очередь на заполненность
     *
     * @return true - очередь полная, false - в очереди есть место
     */
    public boolean isFull() {
        if (this.front == 0 && this.rear == this.SIZE - 1) {
            return true;
        }
        if (this.front == this.rear + 1) {
            return true;
        }
        return false;
    }

    /**
     * Метод возвращающий элемент из начала очереди
     *
     * @return первый элемент - если очередь не пустая, null - если очередь пустая
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }
        return this.array[this.front];
    }

    /**
     * Метод возвращает массив, который содержит все элементы находящиеся сейчас в очереди
     *
     * @return массив Object
     */
    public Object[] getAll() {
        return this.array;
    }
}
