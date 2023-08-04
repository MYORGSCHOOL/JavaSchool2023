package pozdnyakova;

/**
 * Класс, реализующий стек на массиве Object
 */
public class Stack {
    /**
     * Код возврата ошибки
     */
    private static final int ERROR_CODE = -1;
    /**
     * Код возврата при успешном выполнении функции
     */
    private static final int SUCCESS_CODE = 1;
    /**
     * массив для хранения элементов стека
     */
    private final Object array[];
    /**
     * максимальный возможный размер стека
     */
    private final int maxSize;
    /**
     * индекс вершины стека
     */
    private int top;

    public Stack(int maxSize) {
        array = new Object[maxSize];
        this.maxSize = maxSize;
        this.top = -1;
    }

    /**
     * Метод для вставки элемента в стек сверху
     *
     * @param object элемент, который нужно вставить
     * @return 1 - если удалось вставить элемент
     * -1 - если не удалось вставить из-за того, что стек достиг максимального размера
     */
    public int push(Object object) {
        if (top == maxSize - 1) {
            return ERROR_CODE;
        } else {
            top++;
            array[top] = object;
            return SUCCESS_CODE;
        }
    }

    /**
     * Метод для удаления элемента из стека
     *
     * @return удаленный элемент - если стек не пустой
     * null - если стек пустой
     */
    public Object pop() {
        if (isEmpty()) return null;
        else {
            Object topStack = array[top];
            array[top] = null;
            --top;
            return topStack;
        }
    }

    /**
     * Метод для проверки, пуст ли стек
     *
     * @return true - если стек пустой
     * false - если стек не пустой
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Метод, позволяющий получить объект с вершины стека без его удаления
     *
     * @return объект с вершины стека - если стек не пустой
     * null - если стек пустой
     */
    public Object top() {
        if (isEmpty()) return null;
        else {
            return array[top];
        }
    }
}

