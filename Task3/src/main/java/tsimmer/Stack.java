package tsimmer;

/**
 * Реализует набор методов для Stack
 */
public class Stack {
    /**
     * Максимальный размер стека
     */
    private final int maxSize;
    /**
     * Массив объектов
     */
    private final Object[] stackArray;
    /**
     * Верхний элемент стека
     */
    private int top;
    /**
     * Значение которое возвращает метод в случае ошибки
     */
    private static final int RETURN_ERR = -1;
    /**
     * Значение которое возвращает метод в случае успешного выполнения
     */
    private static final int RETURN_SUCCESS = 1;

    public Stack(int size) {
        maxSize = size;
        stackArray = new Object[maxSize];
        top = -1;
    }

    /**
     * Метод для добавления элемента в стек сверху.
     * Этот метод проверяет заполненность стека.
     * Если стек свободен добавляет элемент.
     *
     * @param item указывается элемент, который будет добавлен
     * @return -1 если ошибка вставки и 1 если вставка прошла успешно
     */
    public Object push(Object item) {
        if (isFull()) {
            System.out.println("Стек полон. Невозможно добавить элемент.");
            return RETURN_ERR;
        }
        stackArray[++top] = item;
        return RETURN_SUCCESS;
    }

    /**
     * Метод удаляет верхний элемент из стека.
     * Метод проверяет имеются ли элементы в стеке.
     * Если стек содержит элементы, метод удаляет верхний элемент.
     *
     * @return удаленный элемент
     */
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Стек пуст. Невозможно удалить элемент.");
            return null;
        }
        return stackArray[top--];
    }

    /**
     * Метод возвращает верхний элемент стека
     * В методе реализована проверка заполненности стека.
     *
     * @return верхний элемент стека
     */
    public Object top() {
        if (isEmpty()) {
            System.out.println("Стек пуст.");
            return null;
        }
        return stackArray[top];
    }

    /**
     * Метод проверяет заполненность стека
     *
     * @return true, если стек пуст
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Метод проверяет заполненность стека
     *
     * @return true, если стек полон
     */
    public boolean isFull() {
        return (top == maxSize - 1);
    }

}
