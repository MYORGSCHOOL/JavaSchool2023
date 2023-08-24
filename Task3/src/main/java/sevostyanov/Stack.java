package sevostyanov;

public class Stack {
    /**
     * Массив для хранения элементов cтека
     */
    private Object[] stackArray;
    /**
     * Верхний элемент стека
     */
    private int top;
    /**
     * Максимальное значение размера стека
     */
    private int maxSize;
    /**
     * Количество элементов на сколько будет увеличиваться массив
     */
    private final static int EXTENSION_ARRAY_SIZE = 2;

    public Stack(int s) {
        maxSize = s;
        stackArray = new Object[EXTENSION_ARRAY_SIZE];
        top = -1;
    }

    /**
     * Метод который добавляет элемент в стек сверху, при необходимости расширяет массив
     *
     * @param item элемент который будет добавлен в стек
     */
    public void push(Object item) {
        if (top == stackArray.length - 1) {
            Object[] newArray = new Object[stackArray.length + EXTENSION_ARRAY_SIZE];
            System.arraycopy(stackArray, 0, newArray, 0, top + 1);
            stackArray = newArray;
        } else {
            top++;
            stackArray[top] = item;
        }
    }

    /**
     * Метод который удаляет верхний элемент из стека, если стек пуст и нечего удалять
     * пишет в терминальный вывод сообщение о том что стек пуст
     *
     * @return удаленный элемент
     */
    public Object pop() {
        if (top == -1) {
            System.out.println("Стек пуст. Нет элементов для удаления.");
            return null;
        } else {
            Object item = stackArray[top];
            top--;
            return item;
        }
    }

    /**
     * Метод проверяет заполненность стека
     *
     * @return Возвращает тру если стек пуста
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Метод который возвращает верхний элемент стека, если стек пуст
     * выводит в терминальный вывод сообщение о том что стек пуст
     *
     * @return Возвращает верхний элемент стека
     */
    public Object top() {
        if (top == -1) {
            System.out.println("Стек пуст. Нет верхнего элемента.");
            return null;
        } else {
            return stackArray[top];
        }
    }
}
