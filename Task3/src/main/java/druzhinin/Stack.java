package druzhinin;

import java.util.EmptyStackException;

/**
 * Класс для стека на массиве элементов Object.
 *
 * @author Дружинин Артем.
 */
public class Stack {
    /**
     * Массив объектов (элементов), находящихся в стеке.
     */
    private final Object[] stackArray;

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

    /**
     * Метод для вставки элемента на верх стека.
     *
     * @param newElement Новый элемент.
     * @throws IndexOutOfBoundsException при попытке вставки элемента в заполненный стек.
     */
    public void push(Object newElement) {
        if (topIndex == stackArray.length - 1) {
            throw new IndexOutOfBoundsException(topIndex + 1);
        }

        stackArray[++topIndex] = newElement;
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
