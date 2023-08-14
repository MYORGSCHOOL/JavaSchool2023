package tsimmer;

import java.util.Arrays;

/**
 * Реализует набор методов для Stack
 */
public class Stack {
    /**
     * Максимальный размер стека
     */
    private final int PRIMARY_SIZE = 3;
    /**
     * Массив объектов
     */
    private Object[] stackArray;
    /**
     * Верхний элемент стека
     */
    private int top;

    /**
     * Значение которое возвращает метод в случае успешного выполнения
     */
    private static final int RETURN_SUCCESS = 1;

    public Stack() {
        stackArray = new Object[PRIMARY_SIZE];
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

        if (stackArray.length == PRIMARY_SIZE) {
            Object[] newArr = new Object[2 * PRIMARY_SIZE];
            System.arraycopy(stackArray, 0, newArr, 0, top + 1);
            stackArray = newArr;
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
        return (top == PRIMARY_SIZE - 1);
    }

    public Object[] getAll() {

        int size = 0;
        for (int i = 0; i < stackArray.length; i++) {
            if (stackArray[size] != null) {
                size++;
            }
        }
        return Arrays.copyOf(stackArray, size);
    }

}
