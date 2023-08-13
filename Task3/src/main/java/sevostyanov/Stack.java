package sevostyanov;

public class Stack {
    /**
     * Массив для хранения элементов cтека
     */
    private Object[] stackArray;
    /**
     * Верхниц элмемент стека
     */
    private int top;
    /**
     * Максимальное значение размера стека
     */
    private int maxSize;

    public Stack(int s) {
        maxSize = s;
        stackArray = new Object[maxSize];
        top = -1;
    }

    /**
     * Метод который добавляет элемент в стек сверху, если  стек заполнен
     * выводит в терминальное окно сообщение.
     *
     * @param item элемент который будет добавлен в стек
     */
    public void push(Object item) {
        if (top == maxSize - 1) {
            System.out.println("Стек полон. Невозможно добавить элемент.");
        } else {
            top++;
            stackArray[top] = item;
        }
    }

    /**
     * Метод который удалает верхний элемент из стека, если стек пуст и нечего удалять
     * пишет в терминальый вывод сообщение о том что стек пуст
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
     * выводти в терминальный вывод сообщение о том что стек пуст
     *
     * @return Возвращает верхний элемнт стека
     */
    public Object top() {
        if (top == -1) {
            System.out.println("Стек пуст. Нет верхнего элемента.");
            return null;
        } else {
            return stackArray[top];
        }
    }

    /**
     * В методе создается новый массив copyarray размером top + 1, где top - это индекс верхнего элемента в стеке.
     * Затем, с помощью статического метода arraycopy из класса System, копируются все элементы из stackarray (исходного массива стека) в copyarray
     *
     * @return метод возвращает полученную копию массива
     */
    public Object[] getAll() {
        Object[] copyArray = new Object[top + 1];
        System.arraycopy(stackArray, 0, copyArray, 0, top + 1);
        return copyArray;
    }
}
