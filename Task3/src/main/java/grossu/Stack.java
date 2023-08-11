package grossu;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Класс Stack реализует ограниченный массив объектов, построенному в виде стека по протоколу LIFO
 */
public class Stack {
    private static final int DEFAULT_STACK_CAPACITY = 10;
    /**
     * Поле для хранения стека объектов
     */
    private Object[] stackArray;

    /**
     * Указатель на верхний элемент стека
     */
    private int top;

    public Stack(int maxCount) {
        if (maxCount >= 0) {
            this.stackArray = new Object[maxCount];
            this.top = -1;
        } else {
            throw new NegativeArraySizeException();
        }
    }

    public Stack() {
        this(DEFAULT_STACK_CAPACITY);
    }

    public Object[] toArray() {
        return stackArray.clone();
    }

    /**
     * Функция добавляет элемент в стек сверху
     * Если стек переполнен, то емкость размер стека увеличивается
     *
     * @param o элемент, который необходимо добавить в стек
     */
    public void push(Object o) {
        if (top != stackArray.length - 1) {
            top++;
            stackArray[top] = o;
        } else {
            increaseCapacity();
        }
    }

    /**
     * Функция возвращает первый элемент после удаления его из стека
     *
     * @return первый элемент, если удаление прошло успешно
     * @throws EmptyStackException, если стек пуст
     */
    public Object pop() {
        if (!this.isEmpty()) {
            Object temp = stackArray[top];
            stackArray[top] = null;
            top--;
            return temp;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Функция проверяющая стек на пустоту
     *
     * @return true, если стек пуст, или false - в обратном случае
     */
    public Boolean isEmpty() {
        return top == -1;
    }

    /**
     * Функция, которая возвращает верхний элемент без удаления
     *
     * @return верхний элемент или null, если стек пуст
     */
    public Object top() {
        if (!this.isEmpty()) {
            return stackArray[top];
        }
        return null;
    }

    private void increaseCapacity() {
        int newCapacity = stackArray.length + (stackArray.length >> 1);
        Object[] newStackArray = new Object[newCapacity];
        System.arraycopy(stackArray, 0, newStackArray, 0, stackArray.length);
        stackArray = newStackArray;
    }

    /**
     * Функция для предоставления информации о состоянии стека
     *
     * @return строку с полями класса Stack
     */
    @Override
    public String toString() {
        return "Stack{" +
                "stackArray=" + Arrays.toString(stackArray) +
                '}';
    }
}
