package grankin;

/**
 * Класс с реализацией структуры данных стек
 */
public class MyStack {

    /**
     * Массив для хранения данных
     */
    private Object[] array;

    /**
     * Максимальный размер массива
     */
    private int maxSize;

    /**
     * Текущая заполненность массива
     */
    private int currentSize;

    /**
     * Начальный размер стека
     */
    private final int START_SIZE = 10;

    /**
     * Конструктор по умолчанию
     */
    public MyStack() {
        this.maxSize = START_SIZE;
        this.array = new Object[this.maxSize];
        this.currentSize = -1;
    }

    /**
     * Конструктор с параметрами
     *
     * @param maxSize - максимальный размер будущего стека
     */
    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        this.array = new Object[maxSize];
        this.currentSize = -1;
    }

    /**
     * Положить элемент в стек
     *
     * @param anyObject - Передаваемый объект
     * @return - -1 - если стек переполнен, 1 - если стек не заполнен и объект добавился втек
     */
    public int push(Object anyObject) {
        int result = -1;
        if (this.array != null) {
            if (this.currentSize >= this.maxSize - 1) {
                this.addCapacity();
            }
            this.currentSize += 1;
            this.array[this.currentSize] = anyObject;
            result = 1;
        }
        return result;
    }

    /**
     * Забрать вершину из стека
     *
     * @return - возвращается объект, если все хорошо, или null
     */
    public Object pop() {
        Object result = null;
        if (this.array != null) {
            if (this.currentSize > -1) {
                result = this.array[this.currentSize];
                this.array[this.currentSize] = null;
                this.currentSize -= 1;
            }
        }
        return result;
    }

    /**
     * Получить вершину из стека
     *
     * @return - возвращается объект, если все хорошо, или null
     */
    public Object top() {
        Object result = null;
        if (this.array != null) {
            if (this.currentSize > -1) {
                result = this.array[this.currentSize];
            }
        }
        return result;
    }

    /**
     * Проверка, что стек пуст
     *
     * @return - возвращается true, если стек пуст, иначе - false
     */
    public boolean isEmpty() {
        if (currentSize == -1) {
            return true;
        }
        return false;
    }

    /**
     * Проверка, что стек полон
     *
     * @return - возвращается true, если стек полон, иначе - false
     */
    public boolean isFull() {
        if (currentSize == maxSize - 1) {
            return true;
        }
        return false;
    }

    /**
     * Отображение текущего стека
     */
    public void displayMyStack() {
        System.out.println("Текущий стек:");
        for (int i = 0; i < this.currentSize + 1; i++) {
            System.out.println(this.array[i]);
        }
        System.out.println();
    }

    /**
     * Вернуть внутренний массив
     *
     * @return - копия внутреннего массива
     */
    public Object[] getArray() {
        Object[] result = new Object[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            result[i] = this.array[i];
        }
        return result;
    }

    private void addCapacity() {
        Object[] tmp = new Object[this.currentSize + 1];
        if (this.currentSize >= 0) {
            System.arraycopy(this.array, 0, tmp, 0, this.currentSize);
        }
        this.array = new Object[this.maxSize * 2];
        System.arraycopy(tmp, 0, this.array, 0, this.currentSize);
        this.maxSize = this.maxSize * 2;
    }

    /**
     * Текущая заполненность стека
     *
     * @return - возвращается текущая заполненность
     */
    public int currentSize() {
        return currentSize + 1;
    }

    /**
     * Максимальный размер стека
     *
     * @return - возвращается максимальный размер стека
     */
    public int maxSize() {
        return maxSize;
    }
}
