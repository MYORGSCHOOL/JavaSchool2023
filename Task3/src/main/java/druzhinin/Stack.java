package druzhinin;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Класс для стека на массиве элементов Object.
 *
 * @author Дружинин Артем.
 */
public class Stack {
    /**
     * Базовая емкость массива элементов стека.
     */
    private static final int BASE_CAPACITY = 16;

    /**
     * Массив объектов (элементов), находящихся в стеке.
     */
    private Object[] stackArray;

    /**
     * Индекс верхнего элемента в стеке.
     */
    private int topIndex;

    /**
     * Конструктор стека по максимальному количеству элементов.
     *
     * @param maxCount Максимальное количество элементов в стеке.
     * <br/>
     * {@code maxCount} должен быть неотрицательным.
     * @throws NegativeArraySizeException если {@code maxCount} меньше 0.
     */
    public Stack(int maxCount) {
        if (maxCount <= 0) {
            throw new NegativeArraySizeException();
        }

        stackArray = new Object[maxCount];
        topIndex = -1;
    }

    public Stack() {
        this(BASE_CAPACITY);
    }

    /**
     * Метод для вставки элемента на верх стека.
     * При переполнении массива элементов стека емкость массива увеличивается в 1.5 раза.
     *
     * @param newElement Новый элемент.
     */
    public void push(Object newElement) {
        if (topIndex == stackArray.length - 1) {
            grow();
        }

        stackArray[++topIndex] = newElement;
    }

    /**
     * Метод для увеличения емкости массива элементов стека.
     *
     * @param newCapacity Новая емкость. Не может быть меньше текущей емкости.
     * @throws IllegalArgumentException при передаче
     * {@code newCapacity} меньше, чем текущая емкость массива.
     */
    private void grow(int newCapacity) {
        if (newCapacity < stackArray.length) {
            throw new IllegalArgumentException("New capacity of array cannot be less than current");
        }

        Object[] increasedStackArray = new Object[newCapacity];
        System.arraycopy(stackArray, 0, increasedStackArray, 0, stackArray.length);
        stackArray = increasedStackArray;
    }

    /**
     * Метод для увеличения емкости массива элементов стека.<br/>
     * По умолчанию увеличивает размер массива следующим образом:<br/>
     * Если текущая емкость массива - четное число, то она увеличивается в 1.5 раза.<br/>
     * Если нечетное - увеличивается на число: (текущая емкость - 1) / 2.
     */
    private void grow() {
        grow(stackArray.length + (stackArray.length >> 1));
    }

    /**
     * Метод для получения верхнего элемента из стека после удаления этого элемента.
     *
     * @return Возвращает верхний элемент стека.
     * @throws EmptyStackException при попытке удалить элемент из пустого стека.
     */
    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Object temp = stackArray[topIndex];
        stackArray[topIndex--] = null;

        return temp;
    }

    /**
     * Метод для проверки стека на пустоту.
     *
     * @return Возвращает true, если стек пуст, иначе - false.
     */
    public boolean isEmpty() {
        return topIndex == -1;
    }

    /**
     * Метод для получения верхнего элемента из стека.
     *
     * @return Возвращает null, если стек пуст, иначе - верхний элемент стека.
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }

        return stackArray[topIndex];
    }

    /**
     * Метод для получения копии массива элементов стека.
     *
     * @return Возвращает копию массива элементов стека.
     */
    public Object[] toArray() {
        return stackArray.clone();
    }

    /**
     * Метод для представления содержимого стека в виде строки в правильном виде (без null).
     *
     * @return Возвращает строку вида "{@code Stack{элементы стека через запятую}}".
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Stack{");
        if (isEmpty()) {
            return builder.append("}").toString();
        }

        for (int i = 0; i <= topIndex; i++) {
            builder.append(stackArray[i]);
            if (i == topIndex) {
                builder.append("}");
            }
            else {
                builder.append(", ");
            }
        }

        return builder.toString();
    }
}
