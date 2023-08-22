package dushkina;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий стек (на массиве Object!) с основными операциями
 */
public class Stack {
    /**
     * Вместимость стека по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Размер увеличения вместимости, т.е. во сколько раз увеличится вместимость
     */
    private static final int SIZE_CAPACITY_EXPANSION = 2;
    /**
     * Поле для хранения стека объектов
     */
    private Object[] stackElements;
    /**
     * Максимальное количество элементов в стеке
     */
    private int maxCount;
    /**
     * Текущее количество элементов в очереди
     */
    private int count;
    /**
     * Указатель на верхний элемент стека
     */
    private int front;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int maxCount) {
        if (maxCount < 0) {
            throw new NegativeArraySizeException();
        }
        this.maxCount = maxCount;
        this.stackElements = new Object[maxCount];
        this.count = 0;
        this.front = -1;
    }

    /**
     * Метод, вставляющий элемент сверху стека
     * если стек заполнен, вызывается метод для расширения
     *
     * @param o - элемент, который нужно вставить сверху
     */
    public void push(Object o) {
        if (maxCount < count || maxCount == count || maxCount == 0) {
            capacityExpansion();
        }
        front++;
        stackElements[front] = o;
        count++;
    }

    /**
     * Метод расширяющий стек
     */
    private void capacityExpansion() {
        if (maxCount == 0) {
            maxCount++;
        }
        maxCount *= SIZE_CAPACITY_EXPANSION;
        Object[] array = new Object[maxCount];
        System.arraycopy(stackElements, 0, array, 0, count);
        stackElements = array;
    }

    /**
     * Метод, проверяющий пустой ли стек
     *
     * @return boolean, в зависимости от результата
     */
    public Boolean isEmpty() {
        return count == 0;
    }

    /**
     * Метод, возвращающий верхний элемент стека для просмотра
     *
     * @return верхний элемент очереди, если очередь не пуста, иначе null
     */
    public Object top() {
        if (!this.isEmpty()) {
            return stackElements[front];
        } else {
            return null;
        }
    }

    /**
     * Метод, возвращающий верхний элемент и удаляющий его из стека
     *
     * @return элемент, который вывели из стека
     * @throws NoSuchElementException - выброс исключения, когда стек пуст
     */
    public Object pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Object deleteElement = top();
        System.arraycopy(stackElements, 0, stackElements, 0, --count);
        stackElements[front] = null;
        front--;
        return deleteElement;
    }

    /**
     * Метод, показывающий, что находится в стеке
     *
     * @return массив элементов, находящихся в стеке
     */
    public Object[] getAll() {
        Object[] data = new Object[count];
        System.arraycopy(stackElements, 0, data, 0, count);
        return data;
    }

    /**
     * Метод, выводящий данные класса в строковом виде
     *
     * @return строка с полями класса и их значениями
     */
    @Override
    public String toString() {
        return "Stack{" +
                "stackElements=" + Arrays.toString(stackElements) +
                ", maxCount=" + maxCount +
                ", count=" + count +
                ", front=" + front +
                '}';
    }
}
