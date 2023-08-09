package grankin;

/**
 * Класс с реализацией структуры данных стек
 */
public class MyStack {

    /**
     * Массив для хранения данных
     */
    private final Object[] array;

    /**
     * Максимальный размер массива
     */
    private final int maxSize;

    /**
     * Текущая заполненность массива
     */
    private int currentSize;

    /**
     * Конструктор с параметрами
     * @param maxSize - максимальный размер будущего стека
     */
    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
        currentSize = -1;
    }

    /**
     * Положить элемент в стек
     * @param anyObject     - Передаваемый объект
     * @return              - -1 - если стек переполнен, 1 - если стек не заполнен и объект добавился втек
     */
    public int push(Object anyObject) {
        int result = -1;

        if (array != null){
            if (currentSize < maxSize - 1) {

                currentSize += 1;
                array[currentSize] = anyObject;
                result = 1;
            }
        }

        return result;
    }

    /**
     * Забрать вершину из стека
     * @return      - возвращается объект, если все хорошо, или null
     */
    public Object pop() {

        Object result = null;

        if (array != null) {
            if (currentSize > -1){
                result = array[currentSize];
                array[currentSize] = null;
                currentSize -= 1;
                //return array[currentSize--];
            }
        }

        return result;
    }

    /**
     * Получить вершину из стека
     * @return      - возвращается объект, если все хорошо, или null
     */
    public Object top() {
        Object result = null;

        if (array != null) {
            if (currentSize > -1) {
                result = array[currentSize];
                //return array[currentSize];
            }
        }

        return result;
    }

    /**
     * Проверка, что стек пуст
     * @return      - возвращается true, если стек пуст, иначе - false
     */
    public boolean isEmpty() {
        if(currentSize == -1){
            return true;
        }
        return false;
    }

    /**
     * Проверка, что стек полон
     * @return      - возвращается true, если стек полон, иначе - false
     */
    public boolean isFull() {
        if(currentSize == maxSize - 1){
            return true;
        }
        return false;
    }

    /**
     * Отображение текущего стека
     */
    public void displayMyStack() {

        System.out.println("Текущий стек:");
        for (int i = 0; i < currentSize + 1; i++){
            System.out.println(array[i]);
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
     * Текущая заполненность стека
     * @return      - возвращается текущая заполненность
     */
    public int currentSize(){
        return currentSize + 1;
    }

    /**
     * Максимальный размер стека
     * @return      - возвращается максимальный размер стека
     */
    public int maxSize(){
        return maxSize;
    }
}
