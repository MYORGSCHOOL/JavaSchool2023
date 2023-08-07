package proskurina;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Класс динамического стека Object, основанный на LIFO
 * (last in first out).
 */
public class Stack {
    /**
     * Массив элементов стека.
     */
    private Object[] elements;
    /**
     * Количество элементов в стеке.
     */
    private int count;
    /**
     * Стандартный размер стека.
     */
    private static final int STANDARD_CAPACITY = 10;
    /**
     * Пороговый процент заполнения стека для вызова trim.
     */
    private final static double TRIM_CONDITION = 0.5d;
    
    public Stack() {
        this(STANDARD_CAPACITY);
    }
    
    /**
     * Создает стек заданного размера.
     * Размер должен быть в даипазоне [0; Integer.MAX_VALUE).
     *
     * @param initialCapacity размер стека
     * @throws OutOfMemoryError         когда размер равен Integer.MAX_VALUE
     * @throws IllegalArgumentException когда размер меньше 0
     */
    public Stack(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размер стека меньше нуля");
        }
        elements = new Object[initialCapacity];
        count = 0;
    }
    
    /**
     * Добавляет элемент в стек и возвращает его. Размер стека увеличится,
     * если количество элементов превышает его.
     *
     * @param obj добавляемый элемент
     * @return добавленный элемент
     */
    public Object push(Object obj) {
        if (count == elements.length) {
            grow();
        }
        elements[count] = obj;
        count++;
        return obj;
    }
    
    /**
     * Возвращает верхний элемент стека и удалает его из стека.
     *
     * @return верхний элемент стека
     * @throws EmptyStackException если в стеке нет элементов
     */
    public Object pop() {
        Object obj = top();
        count--;
        elements[count] = null;
        if ((double) count / elements.length < TRIM_CONDITION) {
            trim();
        }
        return obj;
    }
    
    /**
     * Возвращает верхний элемент стека, не удаляя его из стека.
     *
     * @return верхний элемент стека
     * @throws EmptyStackException если в стеке нет элементов
     */
    public Object top() {
        if (count == 0) {
            throw new EmptyStackException();
        }
        return elements[count - 1];
    }
    
    /**
     * Возвращает true, если в стеке нет элементов.
     *
     * @return true, если в стеке нет элементов
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    @Override
    public String toString() {
        return "Stack{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
    
    public Object[] toArray() {
        return elements.clone();
    }
    
    /**
     * Увеличивает размер стека в два раза.
     * Если размер равен 0, то увеличит его до STANDARD_CAPACITY.
     *
     * @throws OutOfMemoryError когда новый размер больше или равен Integer.MAX_VALUE
     */
    private void grow() {
        int oldCapacity = elements.length;
        int newCapacity = (oldCapacity == 0) ? STANDARD_CAPACITY
                                             : oldCapacity << 1;
        elements = Arrays.copyOf(elements, newCapacity);
    }
    
    /**
     * Делает размер стека равным количеству его элементов.
     */
    private void trim() {
        elements = Arrays.copyOf(elements, count);
    }
}