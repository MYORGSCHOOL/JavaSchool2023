package grossu;


import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Класс Stack реализует ограниченный массив объектов, построенному в виде стека по протоколу LIFO
 */
public class Stack {
    /**
     * Поле для хранения стека объектов
     */
    private final Object[] stack;
    /**
     * Максимальное число объектов в стеке
     */
    private final int maxCount;
    /**
     * Указатель на верхний элемент стека
     */
    private int top;

    public Stack(int maxCount) {
        if (maxCount >= 0) {
            this.maxCount = maxCount;
            this.stack = new Object[this.maxCount];
            this.top = -1;
        } else {
            throw new NegativeArraySizeException();
        }
    }

    public Object[] toArray() {
        return stack.clone();
    }

    /**
     * Функция добавляет элемент в стек сверху
     *
     * @param o элемент, который необходимо добавить в стек
     * @throws IndexOutOfBoundsException, если стек будет переполнен
     */
    public void push(Object o) {
        if (top != maxCount) {
            top++;
            stack[top] = o;
        } else {
            throw new IndexOutOfBoundsException(top);
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
            Object temp = stack[top];
            stack[top] = null;
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
