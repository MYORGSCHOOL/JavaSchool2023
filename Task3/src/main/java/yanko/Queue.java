package yanko;

/**
 * класс, реализующий очередь
 */
public class Queue {
    /**
     * элементы, хранящиеся в очереди
     */
    private final Object [] elements;
    /**
     * количество элементов в очереди
     */
    private int elementsNum;
    /**
     * максимально допустимое количество элементов в очереди
     */
    private static final int MAX_SIZE = 100;

    public Queue() {
        this.elements = new Object[MAX_SIZE];
        this.elementsNum = 0;
    }

    /**
     * возвращает копию массива очереди
     *
     * @return копия массива очереди
     */
    public Object[] toArray() {
        Object [] objects = elements;
        return objects;
    }

    public int size() {
        return elementsNum;
    }

    /**
     * вставляет элемент в конец очереди
     *
     * @param element новый элемент
     * @throws ArrayIndexOutOfBoundsException если элемент вставляется в заполненную очередь
     */
    public void enqueue(Object element) {
        try {
            this.elements[elementsNum] = element;
            this.elementsNum++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("the queue is already full!");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * удаляет элемент из начала очереди
     *
     * @throws ArrayIndexOutOfBoundsException если элемент удаляется из пустой очереди
     */
    public Object dequeue() {
        Object object = null;
        try {
            object = this.elements[0];
            for (int i = 0; i < elementsNum - 1; i++) {
                this.elements[i] = this.elements[i+1];
            }
            this.elements[elementsNum - 1] = null;
            this.elementsNum--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("the queue is already empty!");
            throw new ArrayIndexOutOfBoundsException();
        }
        return object;
    }

    /**
     * проверяет, пуста ли очередь
     *
     * @return true - очередь пуста, false - очередь не пуста
     */
    public boolean isEmpty() {
        if(elementsNum > 0) {
            return false;
        }
        return true;
    }

    /**
     * возвращает первый элемент очереди
     *
     * @return первый элемент
     */
    public Object top() {
        if(elementsNum > 0) {
            return this.elements[0];
        }
        System.out.println("the queue is empty! nothing to return");
        return null;
    }

    /**
     * выводит элементы очереди и их количество
     *
     * @return строка с информацией об очереди
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Object object : this.elements) {
            if(object != null) {
                result.append(object);
                result.append(" ");
            }
        }
        return "queue\nelements = " + result.toString().trim() +
                ",\nelementsNum = " + elementsNum +
                "\n";
    }
}
