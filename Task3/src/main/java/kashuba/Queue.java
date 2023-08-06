package kashuba;

import java.util.Arrays;

public class Queue {

    /**
     * arrayOfObjects - массив Object, необходимый для хранения элементов очереди
     * final т.к. массив неизменяемый, т.е. ограничен
     */
    private final Object[] arrayOfObjects;

    /**
     * numberOfElements содержит в себе кол-во не null-елементов в поле arrayOfObjects
     */
    private int numberOfElements;

    public Queue() {
        this(10);
    }

    public Queue(int arrayCapacity) {
        arrayOfObjects = new Object[arrayCapacity];
    }

    /**
     * Функция которая вставляет элемент в конец очереди
     *
     * @param element элемент который вставляют
     * @return element
     * @Throws ArrayIndexOutOfBoundsException, если очередь заполнена
     * @see #isFull() функция проверки очередь на заполненность
     */
    public Object enqueue(Object element) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Очередь заполнена");
        }
        arrayOfObjects[numberOfElements++] = element;
        return element;
    }

    /**
     * Функция, возвращающая и удаляющая первый элемент в очереди
     *
     * @return первый элемент в очереди
     * @Throws IllegalStateException если очередь пуста
     * @see #top() функция взятия первого элемента в очереди
     */
    public Object dequeue() {
        Object element = top();
        System.arraycopy(arrayOfObjects, 1, arrayOfObjects, 0, --numberOfElements);
        arrayOfObjects[numberOfElements] = null;
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
     * Функция которая возвращает первый элемент в очереди
     *
     * @return первый элемент в очереди
     * @Throws IllegalStateException если очередь пуста
     * @see #isEmpty() функция проверки очереди на пустоту
     */
    public Object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return arrayOfObjects[0];
    }

    /**
     * Функция проверяющая содержимое очереди на заполненность
     *
     * @return true - заполнена, false - если можно поместить еще элементы
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
