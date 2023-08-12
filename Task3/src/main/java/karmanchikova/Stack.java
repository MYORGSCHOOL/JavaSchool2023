package karmanchikova;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Класс для реализации стека на массивах Object
 */
public class Stack {
    /**
     * Коэффициент увеличения стека
     */
    private static final int EXPAND = 2;

    /**
     * Начальный размер стека
     */
    private static final int INITIAL_VALUE = 5;

    /**
     * Массив для хранения элементов очереди
     */
    private Object[] arrStack;

    /**
     * Указывает на верхний элемент в стеке
     */
    private int top;

    /**
     * Указывает на текущий размер стека
     */
    private int count;

    /**
     * Если не указан размер стека, то он устанавливается автоматически
     */
    public Stack() {
        this(INITIAL_VALUE);
    }

    public Stack(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.arrStack = new Object[size];
        this.top = -1;
        this.count = 0;
    }

    /**
     * Функция для добавления элемента в queue
     *
     * @param newElement элемент который необходимо добавить в конец очереди
     */
    public void push(Object newElement) {
        if (count == arrStack.length) {
            expand();
        }
        System.out.println("Добавлен элемент: " + newElement);
        arrStack[++top] = newElement;
        count++;
    }

    /**
     * Функция для удаления первого элемента из очереди
     */
    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Object deleteElement = arrStack[top];
        System.out.println("Удален элемент: " + deleteElement);
        arrStack[top] = null;
        top--;
    }

    /**
     * Функция для возврата верхнего элемента стека
     *
     * @return возвращает верхний элемент в стеке
     */
    public Object top() {
        if (isEmpty()) {
            throw new NegativeArraySizeException();
        }
        return arrStack[top];
    }

    /**
     * Функция для возврата объектов стека
     *
     * @return возвращает объекты в стека
     */
    public Object[] getAll() {
        return arrStack.clone();
    }

    /**
     * Функция для проверки опустошенности стека
     *
     * @return в зависимости от результата либо true, либо false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Функция для проверки заполненности стека
     *
     * @return в зависимости от результата либо true, либо false
     */
    public boolean isFull() {
        return count == INITIAL_VALUE;
    }

    /**
     * Метод для увеличения размера стека
     */
    private void expand() {
        int newSize = isFull() ? INITIAL_VALUE : count * EXPAND;
        Object[] newStack = new Object[newSize];
        System.arraycopy(arrStack, 0, newStack, 0, count);
        arrStack = newStack;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "arrStack=" + Arrays.toString(arrStack) +
                ", top=" + top +
                ", count=" + count +
                '}';
    }
}