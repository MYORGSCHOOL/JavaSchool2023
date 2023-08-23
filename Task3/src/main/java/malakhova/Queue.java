package malakhova;

/**
 * Класс, создающий очередь. Он основан на массиве, работающем по правилу FIFO. Класс включает в себя
 * поля (start-начало очереди, counter-положение до которого очередь заполнена, maxSize-максимальный размер очереди), конструктор, методы:
 * enqueue-добавление элемента в конец очереди,
 * dequeue-удаление элемента из начала очереди,
 * top-возвращает элемент из начала очереди,
 * isEmpty-возвращает значение true, если очередь пуста, и false, если в ней находится хотя бы один элемент,
 * getQueue-возвращает массив, содержащий элементы очереди
 */
public class Queue {
    private final int maxSize;
    private final Object[] arrayQueue;
    private int counter;
    private int start;

    public Queue(int s) {
        this.counter = 0;
        this.start = 0;
        this.maxSize = s;
        this.arrayQueue = new Object[s];
    }

    /**
     * Метод, который возвращает содержимое очереди.
     * В случае если вернуть содержимое невозможно(очередь пуста) появляется исключение
     *
     * @return - массив, в который записывается содержимое очереди
     */
    public Object[] getQueue() {
        if (arrayQueue[start] == null) {
            throw new IllegalArgumentException();
        }
        Object[] array = new Object[maxSize];
        int j = 0;
        for (int i = start; i < maxSize; i++) {
            if (arrayQueue[i] != null) {
                array[j] = arrayQueue[i];
                j++;
            }
        }
        if (start != 0 && arrayQueue[0] != null) {
            for (int k = 0; k < start; k++) {
                if (arrayQueue[k] != null) {
                    array[j] = arrayQueue[k];
                    j++;
                }
            }
        }
        return array;
    }

    /**
     * Метод, который добавляет элемент в конец очереди
     * Если очередь уже заполнена, то метод возвращает исключение
     *
     * @param element - новый элемент очереди
     */
    public void enqueue(Object element) {
        if (start == counter && arrayQueue[start] != null) {
            throw new IllegalArgumentException();
        }
        System.out.println("Добавление элемента");
        arrayQueue[counter] = element;
        if ((counter + 1) != maxSize) {
            counter++;
        } else {
            counter = 0;
        }
    }

    /**
     * Метод, который удаляет элемент из начала очереди
     * Если удалить элемент не удалось возвращается исключение
     */
    public void dequeue() {
        if (arrayQueue[start] == null) {
            throw new IllegalArgumentException();
        }
        System.out.println("Удаление элемента");
        arrayQueue[start] = null;
        if ((start + 1) != maxSize) {
            start++;
        } else {
            start = 0;
        }
    }

    /**
     * Метод, который проверяет пустая ли очередь
     * Если очередь пустая метод возвращает true, иначе возвращается false
     *
     * @return - заполненность очереди
     */
    public boolean isEmpty() {
        return arrayQueue[start] == null;
    }

    /**
     * Метод, который возвращает первый элемент очереди
     * Метод проверяет наличие элементов в очереди и возвращает первый элемент, иначе возвращает исключение
     *
     * @return - первый элемент очереди
     */
    public Object top() {
        if (arrayQueue[start] == null) {
            throw new IllegalArgumentException();
        }
        return arrayQueue[start];
    }
}
