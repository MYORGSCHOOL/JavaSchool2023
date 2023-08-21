package alexenko;

/**
 * Класс стек. Реализован функционал расширения массива элементов стека.
 */
public class Stack {

    /**
     * максимальный размер стека
     */
    private static final int MAX_SIZE = Integer.MAX_VALUE;

    /**
     * текущий размер стека
     */
    private int currentSize;

    /**
     * стек
     */
    private Object[] arr;

    /**
     * индекс верхнего элемента
     */
    private int top;

    /**
     * Конструктор стека. Инициализирует поля начальными значениями.
     * top имеет значение -1, так как стек пуст
     *
     * @param size начальный размер создаваемого стека
     */
    public Stack(int size) {
        this.currentSize = size;
        this.arr = new Object[this.currentSize];
        this.top = -1;
    }

    /**
     * Метод кладёт элемент на верх стека
     *
     * @param o элемент, который кладём на верх
     * @throws RuntimeException добавление элемента в полный стек
     */
    public void push(Object o) throws RuntimeException {
        if (isFull()) {
            resizeUp();
        }
        this.top += 1;
        this.arr[this.top] = o;
    }

    /**
     * Метод удаляет верхний элемент со стека и возвращает его
     *
     * @return верхний удалённый элемент
     * @throws RuntimeException удаление элемента из пустого стека
     */
    public Object pop() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Стек пуст");
        }
        Object o = this.arr[this.top];
        this.arr[this.top] = null;
        this.top -= 1;
        return o;
    }

    /**
     * Метод проверят стек на пустоту.
     *
     * @return true - стек пуст, false - стек не пуст
     */
    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        return false;
    }

    /**
     * Метод проверят стек на заполненность.
     *
     * @return true - стек полон, false - стек не полон
     */
    public boolean isFull() {
        if (this.top == this.currentSize - 1) {
            return true;
        }
        return false;
    }

    /**
     * Метод увеличивает размер стека. Если размер можно увеличить, то стек расширяется в 2 раза.
     *
     * @throws RuntimeException переполнение стека, если уже нельзя расширить
     */
    private void resizeUp() throws RuntimeException {
        int increment = MAX_SIZE - this.currentSize;
        if (this.currentSize > increment) {
            throw new RuntimeException("Переполнение стека");
        }
        this.currentSize *= 2;
        Object[] expandedArr = new Object[this.currentSize];
        System.arraycopy(this.arr, 0, expandedArr, 0, this.arr.length);
        this.arr = expandedArr;
    }

    /**
     * Метод возвращает врехний элемент стека, не удаляя его.
     *
     * @return верхний элемент - если стек не пуст, null - если стек пуст
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }
        return this.arr[this.top];
    }

    /**
     * Метод возвращает массив элементов стека.
     *
     * @return массив элементов
     */
    public Object[] getAll() {
        return this.arr;
    }

}
