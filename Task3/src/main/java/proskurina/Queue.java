package proskurina;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Класс динамической циклической очереди Object, основанный на FIFO
 * (first in first out).
 */
public class Queue {
    /**
     * Массив элементов стека.
     */
    private Object[] elements;
    /**
     * Стандартный размер очереди.
     */
    private final static int STANDARD_CAPACITY = 16;
    /**
     * Пороговый процент заполнения очереди для вызова trim.
     */
    private final static double TRIM_CONDITION = 0.5d;
    /**
     * Указатель на начало очереди.
     */
    private int head;
    /**
     * Указатель на конец очереди.
     */
    private int tail;
    
    /**
     * Количество элементов в очереди.
     */
    private int count;
    
    public Queue() {
        this(STANDARD_CAPACITY);
    }
    
    public Queue(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размер очереди меньше 0");
        }
        elements = new Object[initialCapacity];
        count = head = tail = 0;
    }
    
    /**
     * Добавляет элемент в конец очереди и возвращает его.
     * Размер очереди увеличится, если количество элементов превышает его.
     *
     * @return добавленный элемент
     */
    public Object enqueue(Object obj) {
        if (count == elements.length) {
            grow();
        }
        elements[tail] = obj;
        count++;
        tail = increase(tail);
        return obj;
    }
    
    /**
     * Удаляет элемент из начала очереди и возвращает его.
     *
     * @return первый элемент в очереди
     */
    public Object dequeue() {
        Object obj = top();
        elements[head] = null;
        count--;
        head = increase(head);
        if ((double) count / elements.length < TRIM_CONDITION) {
            trim();
        }
        return obj;
    }
    
    /**
     * Возвращает элемент из начала очереди.
     *
     * @return первый элемент в очереди
     * @throws NoSuchElementException когда очередь пуста
     */
    public Object top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        return elements[head];
    }
    
    /**
     * Возвращает true, если в очереди нет элементов.
     *
     * @return true, если в очереди нет элементов
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    @Override
    public String toString() {
        return "Queue{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
    
    public Object[] toArray() {
        return elements.clone();
    }
    
    /**
     * Увеличивает размер очереди в два раза.
     * Если размер равен 0, то увеличит его до STANDARD_CAPACITY.
     *
     * @throws OutOfMemoryError когда новый размер больше или равен Integer.MAX_VALUE
     */
    private void grow() {
        int oldCapacity = elements.length;
        int newCapacity = (oldCapacity == 0) ? STANDARD_CAPACITY
                                             : oldCapacity << 1;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, head, newArray, 0, oldCapacity - head);
        System.arraycopy(elements, 0, newArray, oldCapacity - head, tail);
        reset(newArray);
    }
    
    /**
     * Циклически увеличивает указатель на единицу по модулю размера очереди.
     * pointer в диапазоне [0; размер очереди - 1].
     *
     * @param pointer увеличиваемый указатель
     * @return результат увеличения
     */
    private int increase(int pointer) {
        return (pointer + 1) % elements.length;
    }
    
    /**
     * Делает размер очереди равным количеству ее элементов.
     */
    private void trim() {
        int oldCapacity = elements.length;
        Object[] newArray = new Object[count];
        if (count != 0) {
            // Если внутри массива есть "дыра" из пустых элементов,
            // по частям копируем живые элементы справа и слева в новый массив.
            if (head > tail) {
                System.arraycopy(elements, head, newArray, 0, oldCapacity - head);
                System.arraycopy(elements, 0, newArray, oldCapacity - head, tail);
            } else {
                System.arraycopy(elements, head, newArray, 0, tail - head);
            }
        }
        reset(newArray);
    }
    
    /**
     * Устанавливает новый массив для очереди.
     * В соответствии с ним устанавливает новые значение для указателей.
     *
     * @param newArray новый массив
     */
    private void reset(Object[] newArray) {
        elements = newArray;
        head = 0;
        tail = count;
    }
}