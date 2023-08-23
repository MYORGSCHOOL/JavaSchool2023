package malakhova;

/**
 * Класс, создающий стек. Он основан на массиве, работающем по правилу LIFO. Класс включает в себя
 * поля (start-начало стека, counter-положение до которого стек заполнен, maxSize-максимальный размер стека), конструктор, методы:
 * push-добавление элемента в конец стека,
 * pop-удаление элемента с конца стека и возвращение значения этого элемента из метода,
 * top-возвращает элемент из вершины стека,
 * isEmpty-возвращает значение true, если стек пуст, и false, если в нем находится хотя бы один элемент,
 * getStack-возвращает массив, содержащий элементы стека
 */
public class Stack {
    private Object[] arrayStack;
    private int counter;
    private int start;
    private int maxSize;
    private static final int INCREASE_STACK = 5;

    public Stack() {
        this.start = 0;
        this.counter = 0;
        this.maxSize = 10;
        this.arrayStack = new Object[10];
    }

    /**
     * Метод, который добавляет элемент в стек
     * Если стек заполнен возвращается исключение
     *
     * @param element - новый элемент стека
     */
    public void push(Object element) {
        if (counter == maxSize) {
            increase();
        }
        System.out.println("Добавление элемента");
        arrayStack[counter] = element;
        counter++;
    }

    /**
     * Метод, который удаляет верхний элемент стека и возвращает значение этого элемента
     * Если удалить элемент не удалось возвращается исключение
     *
     * @return - удаленный элемент стека
     */
    public Object pop() {
        if (arrayStack[start] == null) {
            throw new IllegalArgumentException();
        }
        Object a = arrayStack[counter - 1];
        counter--;
        arrayStack[counter] = null;
        return a;
    }

    /**
     * Метод, который проверяет пустой ли стек
     * Если стек пустой метод возвращает true, иначе возвращается false
     *
     * @return - заполненность стека
     */
    public boolean isEmpty() {
        return arrayStack[start] == null;
    }

    /**
     * Метод, который возвращает верхний элемент стека без удаления
     * Метод проверяет наличие элементов в стеке и возвращает верхний элемент, иначе возвращает исключение
     *
     * @return - верхний элемент стека
     */
    public Object top() {
        if (start == counter) {
            throw new IllegalArgumentException();
        }
        return arrayStack[counter - 1];
    }

    /**
     * Метод, который возвращает содержимое стека
     * В случае если вернуть содержимое невозможно(стек пуст) появляется исключение
     *
     * @return - массив, в который записывается содержимое стека
     */
    public Object[] getStack() {
        if (arrayStack[start] == null) {
            throw new IllegalArgumentException();
        }
        Object[] array = new Object[maxSize];
        for (int i = start; i < counter; i++) {
            array[i] = arrayStack[i];
        }
        return array;
    }

    /**
     * Метод для увеличения размера стека
     */
    private void increase() {
        Object[] arrayCopy = arrayStack;
        arrayStack = new Object[maxSize + INCREASE_STACK];
        for (int i = 0; i < maxSize; i++) {
            arrayStack[i] = arrayCopy[i];
        }
        maxSize += INCREASE_STACK;
    }
}

