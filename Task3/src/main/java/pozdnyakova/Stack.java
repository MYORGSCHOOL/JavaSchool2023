package pozdnyakova;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Класс, реализующий стек на массиве Object
 */
public class Stack {
    /**
     * число, на сколько элементов происходит расширение массива
     */
    private final static int CHANGE_SIZE = 10;
    /**
     * массив для хранения элементов стека
     */
    private Object array[];
    /**
     * индекс вершины стека
     */
    private int top;

    public Stack() {
        array = new Object[CHANGE_SIZE];
        this.top = -1;
    }

    /**
     * Метод для вставки элемента в стек сверху, при необходимости расширяет массив стека
     *
     * @param object элемент, который нужно вставить
     */
    public void push(Object object) {
        if (top == array.length - 1) {
            Object[] newArray = new Object[array.length + CHANGE_SIZE];
            System.arraycopy(array, 0, newArray, 0, top + 1);
            array = newArray;
        }
        top++;
        array[top] = object;
    }

    /**
     * Метод для удаления элемента из стека
     *
     * @return удаленный элемент - если стек не пустой
     */
    public Object pop() {
        if (!isEmpty()) {
            Object topStack = array[top];
            array[top] = null;
            --top;
            return topStack;
        } else {
            throw new EmptyStackException();
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
     */
    public Object top() {
        if (!isEmpty()) {
            return array[top];
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Метод получения объектов стека
     *
     * @return копия массива стека
     */
    public Object[] getAll() {
        int size = 0;
        while (size != array.length && array[size] != null) {
            size++;
        }
        return Arrays.copyOf(array, size);
    }
}

