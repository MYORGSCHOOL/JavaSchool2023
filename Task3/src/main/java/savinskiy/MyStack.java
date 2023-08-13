package savinskiy;

/**
 * Класс Стэк
 */
public class MyStack {
    /**
     * Массив для хранения элементов стэка
     */
    private final Object[] stack;
    /**
     * Верхний элемент стэка
     */
    private int top;

    public MyStack(int capacity) {
        this.stack = new Object[capacity];
        this.top = -1;
    }

    /**
     * Метод вставляет элемент на вершину стэка
     *
     * @param element который будет вствавлен в вершину
     */
    public void push(Object element) {
        if (isFull()) {
            throw new IllegalStateException("Стэк заполнен");
        }
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
     * Метод проверяет полон ли стэк
     *
     * @return true - если стэк заполнен, либо false
     */
    public boolean isFull() {
        return top == stack.length - 1;
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
        Object[] allElements = new Object[top + 1];
        System.arraycopy(stack, 0, allElements, 0,  top + 1);
        return allElements;
    }
}
