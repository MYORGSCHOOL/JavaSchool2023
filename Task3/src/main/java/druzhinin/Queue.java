package druzhinin;

/**
 * Класс для кольцевой очереди на массиве элементов Object.
 *
 * @author Дружинин Артем.
 */
public class Queue {
    /**
     * Массив объектов (элементов), находящихся в очереди.
     */
    private final Object[] queueArray;

    /**
     * Индекс начала очереди.
     */
    private int frontIndex;

    /**
     * Индекс конца очереди.
     */
    private int backIndex;

    /**
     * Текущее количество элементов в очереди.
     */
    private int currentCount;

    /**
     * Конструктор очереди по максимальному количеству элементов.
     *
     * @param maxCount Максимальное количество элементов в очереди.
     * <br/>
     * {@code maxCount} должен быть неотрицательным.
     * @throws NegativeArraySizeException если {@code maxCount} меньше 0.
     */
    public Queue(int maxCount) {
        if (maxCount <= 0) {
            throw new NegativeArraySizeException();
        }

        frontIndex = 0;
        backIndex = -1;
        currentCount = 0;
        queueArray = new Object[maxCount];
    }

    /**
     * Метод для вставки нового элемента в конец очереди.
     *
     * @param newElement Новый элемент.
     * @throws IndexOutOfBoundsException при попытке вставки нового элемента в переполненную очередь.
     */
    public void enqueue(Object newElement) {
        if (currentCount == queueArray.length) {
            throw new IndexOutOfBoundsException(backIndex + 1);
        }

        backIndex = (backIndex + 1) % queueArray.length;
        queueArray[backIndex] = newElement;
        currentCount++;
    }

    /**
     * Метод для удаления элемента из начала очереди.
     *
     * @throws IndexOutOfBoundsException при попытке удаления из пустой очереди.
     */
    public void dequeue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException(frontIndex);
        }

        queueArray[frontIndex] = null;
        frontIndex = (frontIndex + 1) % queueArray.length;
        currentCount--;
    }

    /**
     * Метод для проверки, пуста ли очередь.
     *
     * @return Возвращает true, если очередь пуста, иначе - false.
     */
    public boolean isEmpty() {
        return currentCount == 0;
    }

    /**
     * Метод для получения элемента из начала очереди.
     *
     * @return Возвращает первый с начала элемент в очереди.
     * <br/>
     * Если очередь пустая, то возвращает null.
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }

        return queueArray[frontIndex];
    }

    /**
     * Метод для получения копии массива элементов очереди.
     *
     * @return Возвращает копию массива элементов очереди.
     */
    public Object[] toArray() {
        return queueArray.clone();
    }

    /**
     * Метод для представления содержимого кольцевой очереди в виде строки в правильном виде (без null).
     *
     * @return Возвращает строку вида "{@code Queue{элементы очереди через запятую}}".
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Queue{");
        if (isEmpty()){
            return builder.append("}").toString();
        }
        if (frontIndex <= backIndex) {
            for (int i = frontIndex; i <= backIndex; i++) {
                builder.append(queueArray[i]);
                if (i == backIndex) {
                    builder.append("}");
                }
                else {
                    builder.append(", ");
                }
            }
        }
        else {
            for (int i = frontIndex; i < queueArray.length; i++) {
                builder.append(queueArray[i]).append(", ");
            }

            for (int i = 0; i <= backIndex; i++) {
                builder.append(queueArray[i]);
                if (i == backIndex) {
                    builder.append("}");
                }
                else {
                    builder.append(", ");
                }
            }
        }

        return builder.toString();
    }
}
