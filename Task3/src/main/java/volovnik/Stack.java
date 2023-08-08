package volovnik;

import volovnik.exceptions.StackIsEmptyException;

import java.util.Arrays;

public class Stack {

    private int currentStackEnd = -1;
    private int objectsInStack = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int EXPAND_MULTIPLIER = 2;
    private Object[] stack;

    public Stack() {
        stack = new Object[DEFAULT_SIZE];
    }

    public Stack(int initialSize) {
        stack = new Object[initialSize];
    }

    /**
     * Метод добавляет элемент на верх стека
     * @param object добавляемый элемент
     */
    public void push(Object object) {
        if (objectsInStack == stack.length) {
            int expandedSize = EXPAND_MULTIPLIER * stack.length;
            stack = Arrays.copyOf(stack, expandedSize);
        }
        currentStackEnd += 1;
        stack[currentStackEnd] = object;
        objectsInStack += 1;
    }

    /**
     * Метод удаляет верхний элемент стека и возвращает его
     * @return удалённый элемент
     * @throws StackIsEmptyException стек пустой
     */
    public Object pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException();
        }
        Object deletedObject = stack[currentStackEnd];
        stack[currentStackEnd] = null;
        currentStackEnd -= 1;
        objectsInStack -= 1;
        return deletedObject;
    }

    /**
     * Метод проверяет на пустой стек
     * @return true - если стек пустой, иначе false
     */
    public boolean isEmpty() {
        return objectsInStack == 0;
    }

    /**
     * Метод возвращает верхний элемент стека
     * @return верхний элемент стека
     */
    public Object top() {
        return isEmpty() ? null : stack[currentStackEnd];
    }

    public Object[] getAll() {
        return Arrays.copyOf(stack, stack.length);
    }
}
