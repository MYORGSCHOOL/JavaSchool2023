package kashuba;


import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {

    /**
     * arrayOfObjects - массив Object, необходимый для хранения элементов очереди
     * final т.к. массив неизменяемый, т.е. ограничен
     */
    private final Object[] arrayOfObjects;

    /**
     * numberOfElements содержит в себе кол-во не null-елементов в поле arrayOfObjects
     */
    private int numberOfElements;

    public Stack() {
        this(10);
    }

    public Stack(int arrayCapacity) {
        arrayOfObjects = new Object[arrayCapacity];
    }

    /**
     * Функция которая вставляет элемент в стек
     *
     * @param element вставляемый объект в стек
     * @return element
     * @Throws ArrayIndexOutOfBoundsException если стек заполнен
     * @see #isFull() функция проверки стека на заполненность
     */
    public Object push(Object element) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Стек заполнен");
        }
        arrayOfObjects[numberOfElements++] = element;
        return element;
    }

    /**
     * Функция, которая возвращает верхний элемент в стеке
     *
     * @return верхний элемент
     * @Throws EmptyStackException если стек пустой
     * @see #isEmpty() функция проверки стека на пустоту
     */
    public Object top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arrayOfObjects[numberOfElements - 1];
    }

    /**
     * Функция которая возвращает верхний элемент из стека после удаления
     *
     * @return удаленный верхний элемент
     * @Throws EmptyStackException если стек пустой
     * @see #top() функция взятия верхнего элемента
     */
    public Object pop() {
        Object element = top();
        arrayOfObjects[--numberOfElements] = null;
        return element;
    }

    /**
     * Функция, проверяющая очередь на пустоту
     *
     * @return true - пустая, false - нет
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Функция проверяющая содержимое стека на заполненность
     *
     * @return true - заполнен, false - если можно поместить еще элементы
     */
    public boolean isFull() {
        return numberOfElements == arrayOfObjects.length;
    }

    /**
     * Функция, вырезающая null-елменты из массива
     *
     * @return строковое представление массива
     */
    @Override
    public String toString() {
        Object[] array = new Object[numberOfElements];
        System.arraycopy(arrayOfObjects, 0, array, 0, numberOfElements);
        return Arrays.toString(array);
    }
}
