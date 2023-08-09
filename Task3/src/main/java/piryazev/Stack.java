package piryazev;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Реализация класса Stack, который принимает любые объекты
 */
public class Stack {
    /**
     * Стек в виде массива объектов
     */
    private Object[] stackArray;
    /**
     * Указатель на самый верхний объект стека
     */
    private int top;
    /**
     * Стартовый размер стека
     */
    private static final int START_SIZE = 10;
    /**
     * Множитель размера стека
     */
    private static final int EXPAND_SIZE = 2;

    public Stack() {
        this.stackArray = new Object[START_SIZE];
        this.top = -1;
    }

    /**
     * Метод для загрузки объекта в стек
     *
     * @param object загружаемый объект
     */
    public void push(Object object) {
        if (getCurrentSize() == stackArray.length) {
            expandStack();
        }
        top++;
        stackArray[top] = object;
    }

    /**
     * Метод для выгрузки объекта из стека
     *
     * @return выгружаемый объект
     */
    public Object pop() {
        if (!isEmpty()) {
            Object object = stackArray[top];
            stackArray[top] = null;
            top--;
            return object;
        }
        throw new NoSuchElementException();
    }

    /**
     * Проверка пустой стек или нет
     *
     * @return True если пустой, False если не пустой
     */
    public Boolean isEmpty() {
        return top == -1;
    }

    /**
     * Метод возвращающий без удалений самый верхний объект из стека
     *
     * @return самый верхний объект
     */
    public Object top() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Метод для расширения стека
     */
    public void expandStack() {
        int newSize = stackArray.length * EXPAND_SIZE;
        Object[] newStackArray = new Object[newSize];

        if (getCurrentSize() >= 0) System.arraycopy(stackArray, 0, newStackArray, 0, getCurrentSize());

        stackArray = newStackArray;
        top = getCurrentSize() - 1;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stackArray=" + Arrays.toString(stackArray) +
                '}';
    }

    public int getCurrentSize() {
        return top + 1;
    }
}
