package grossu;


import java.util.Arrays;

/**
 * Класс Stack реализует ограниченный массив объектов, построенному в виде стека по протоколу LIFO
 */
public class Stack {
    private Object[] stack;
    private int maxCount;

    private int top;

    public Stack(int maxCount) {
        try {
            this.maxCount = maxCount;
            this.stack = new Object[this.maxCount];
            this.top = -1;
        } catch (java.lang.NegativeArraySizeException e) {
            System.out.println("Negative array size");
        }
    }

    public Object[] getStack() {
        return stack;
    }

    /**
     * Функция добавляет элемент в стек сверху
     * Если стек переполнен, то выбрасывается исключение ArrayIndexOutOfBoundsException
     *
     * @param o элемент, который необходимо добавить в стек
     */
    public void push(Object o) {
        try {
            top++;
            stack[top] = o;
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("The stack is full");
        }
    }

    /**
     * Функция возвращает первый элемент после удаления из стека
     *
     * @return первый элемент или null в случае, если стек пуст
     */
    public Object pop() {
        if (!this.isEmpty()) {
            Object temp = stack[top];
            stack[top] = null;
            top--;
            return temp;
        }
        System.out.println("The stack is empty");
        return null;
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
        if (!isEmpty()) {
            return stack[top];
        }
        return null;
    }

    /**
     * Функция для предоставления информации о состоянии стека
     *
     * @return строку с полями класса Stack
     */
    @Override
    public String toString() {
        return "Stack{" +
                "stack=" + Arrays.toString(stack) +
                ", maxCount=" + maxCount +
                ", top=" + top +
                '}';
    }
}
