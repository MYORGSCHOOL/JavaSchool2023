package savinskiy;

import java.util.Arrays;

/**
 * Класс Стэк
 */
public class Stack {
    /**
     * Массив для хранения элементов стэка
     */
    private Object[] stack;
    /**
     * Верхний элемент стэка
     */
    private int top;
    /**
     * Объем стэка по умолчанию
     */
    private static final int CAPACITY = 5;

    public Stack() {
        this.stack = new Object[CAPACITY];
        this.top = -1;
    }

    /**
     * Метод вставляет элемент на вершину стэка и увеличивает объем при необходимости
     *
     * @param element который будет вствавлен в вершину
     */
    public void push(Object element) {
        increaseCapacity(top + 2);
        stack[++top] = element;
    }

    /**
     * Метод возвращает вверхний элемент и удаляет его
     *
     * @return верхний элемент
     */
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стэк пуст");
        }
        Object element = stack[top];
        stack[top--] = null;
        return element;
    }

    /**
     * Метод проверяет пуст ли стэк
     *
     * @return true - если пуст, либо false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Метод возвращает верхний элемент без удалания
     *
     * @return верхний элемент
     */
    public Object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Стэк пуст");
        }
        return stack[top];
    }

    /**
     * Метод вовращает копию массива объектов в стэке
     *
     * @return копию массива объектов
     */
    public Object[] getAll() {
        return Arrays.copyOfRange(stack, 0, top + 1);
    }

    /**
     * Метод увеличивает объем массива
     *
     * @param requiredCapacity размер массива
     */
    private void increaseCapacity(int requiredCapacity) {
        if (requiredCapacity > stack.length) {
            int increasedCapacity = stack.length * 2;
            stack = Arrays.copyOf(stack, Math.max(increasedCapacity, requiredCapacity));
        }
    }
}
