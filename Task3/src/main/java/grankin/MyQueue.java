package grankin;

/**
 * Класс с реализация структуры данных очередь
 */
public class MyQueue {

    /**
     * Внутренний массив с элементами
     */
    private final Object[] array;

    /**
     * Максимальный размер очереди
     */
    private final int maxSize;

    /**
     * Текущий размер очереди
     */
    private int currentSize;

    /**
     * Начальный элемент очереди
     */
    private int start;

    /**
     * Конечный элемент очереди
     */
    private int end;

    /**
     * Конструктор с параметрами
     * @param maxSize   - Максимальный размер очереди
     */
    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
        currentSize = 0;
        start = 0;
        end = -1;
    }

    /**
     * Добавить элемент в конец очереди
     * @param anyObject     - Новый элемент
     * @return              - флаг возврата (1 - добавлен, -1 - нет)
     */
    public int enqueue(Object anyObject) {
        int result = -1;

        if (currentSize + 1 <= maxSize) {
            if (end == maxSize - 1) {  // циклический перенос
                end = -1;
            }

            array[++end] = anyObject;  //увеличение Rear и вставка
            currentSize++;  // увеличение количества элементов в очереди
            result = 1;
        }

        return result;
    }

    /**
     * Убрать элемент из начала очереди
     * @return              - флаг возврата (1 - удален, -1 - нет)
     */
    public int dequeue() {
        int result = -1;

        if (currentSize > 0) {

            array[start] = null;
            start++;
            if (start == maxSize) { // циклический перенос
                start = 0;
            }

            currentSize--; // уменьшаем количество элементов в очереди
            result = 1;
        }

        return result;
    }

    /**
     * Получить элемент из начала очереди
     * @return      - элемент или null
     */
    public Object top() {
        Object result = null;
        if (currentSize > 0){
            result = array[start];
        }
        return result;
    }


    /**
     * Отобразить внутренний массив на экран
     */
    public void displayMyQueue() {

        System.out.println("Текущая очередь:");

        for (int i = 0; i < maxSize; i++)
        {
            if (array[i] != null){
                System.out.println(array[i]);
            }
            else{
                System.out.println("null");
            }
        }

        System.out.println();
    }

    /**
     * Вернуть внутренний массив
     * @return      - копия внутреннего массива
     */
    public Object[] getArray() {
        Object[] result = new Object[maxSize];
        for (int i = 0; i < maxSize; i++){
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Проверка, что очередь заполнена
     * @return      - флаг (true - заполнена, false - нет)
     */
    public boolean isFull() {
        return (currentSize == maxSize);
    }

    /**
     * Проверка, что очередь пустая
     * @return      - флаг (true - пустая, false - нет)
     */
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    /**
     * Получить начальный индекс очереди
     * @return      - начальный индекс
     */
    public Object getStart() {
        return array[start];
    }

    /**
     * Получить конечный индекс очереди
     * @return      - конечный индекс очереди
     */
    public Object getEnd() {
        return array[start];
    }

    /**
     * Получить максимальный размер очереди
     * @return      - максимальный размер
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Получить текущий размер очереди
     * @return      - текущий размер очереди
     */
    public int getCurrentSize() {
        return currentSize;
    }
}
