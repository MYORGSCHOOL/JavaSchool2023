package babadzhanov;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс расширяемого стека Object, основанный на LIFO
 */
public class Stack {

    /**
     * Массив объектов в стеке
     */
    private Object[] stackArray;
    /**
     * Размер стека
     */
    private int capacity;
    /**
     * Верхний элемент в стеке
     */
    private int top;

    /**
     * Коэффициент расширния стэка
     */
    private static final int EXPAND_RATE = 2;


    public Stack(int size) {
        capacity = size;
        stackArray = new Object[capacity];
        top = -1;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Метод возвращает копию массива стэка
     *
     * @return Копия массива стэка
     */
    public Object[] toArray() {
        return stackArray.clone();
    }

    /**
     * Метод проверяет, что стэк пустой
     *
     * @return true - стэк пустой, false - стэк не пустой
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Метод проверяет, что стэк полностью заполнен
     *
     * @return true - стэк заполнен, false - стэк не заполнен
     */
    public Boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * Метод вставляет элемент сверху.
     *
     * @param object объект вставляемый сверху
     * @return Массив объектов
     * @throws IndexOutOfBoundsException, если стэк полностью заполнен
     */
    public Object[] push(Object object) {
        if (isFull()) {
            expandStack(object);
            return stackArray;
        }
        stackArray[++top] = object;
        return stackArray;
    }

    /**
     * Метод возвращает верхний элемент после удаления из стека.
     *
     * @return удаленный элемент
     * @throws NoSuchElementException, если стэк пустой
     */
    public Object pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Стэк пустой - нет элементов для удаления");
        }
        Object deletedElement = stackArray[top];
        stackArray[top] = null;
        --top;
        return deletedElement;
    }

    /**
     * Метод возвращает верхний элемент без удаления из стека.
     *
     * @return верхний элемент стека
     * @throws NoSuchElementException, если стэк пустой
     */
    public Object top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Стэк пустой - элемент не найден");
        }
        return stackArray[top];
    }

    /**
     * Метод расширяет стэк.
     *
     * @param objectToExpand объект вставляемый сверху при расширении стэка
     */
    public void expandStack(Object objectToExpand) {
        int newCapacity = capacity * EXPAND_RATE;
        Object[] newArray = new Object[newCapacity];
        newArray[++top] = objectToExpand;
        System.arraycopy(stackArray, 0, newArray, 0, stackArray.length);
        capacity = newCapacity;
        stackArray = newArray;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stackArray=" + Arrays.toString(stackArray) +
                ", capacity=" + capacity +
                ", top=" + top +
                '}';
    }
}
