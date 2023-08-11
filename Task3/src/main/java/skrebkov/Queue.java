package skrebkov;

import java.util.ArrayList;

/**
 * Класс Queue - предоставляет реализацию очереди работающей по принцепу
 * "первым пришёл, первым вышел" (FIFO - First-In-First-Out)
 */
public class Queue {
    /**
     * Номер первого элемента в очереде
     */
    private static final int FIRST_ELEMENT = 0;
    /**
     * Коллекция для хранения очереди
     */
    private final ArrayList<Object> queueArray;

    public Queue() {
        queueArray = new ArrayList<>();
    }

    /**
     * Метод для проверки пустая ли очередь
     *
     * @return true если в очереди нет объектов
     */
    public boolean isEmpty() {
        return queueArray.size() == 0;
    }

    /**
     * Метод для добавления объектов в очередь
     *
     * @param object - объект для добавления в очередь
     */
    public void enqueue(Object object){
        queueArray.add(object);
    }

    /**
     * Удаление первого элемента из очереди,
     * при неудачи бросает исключение
     */
    public void dequeue() {
        queueArray.remove(FIRST_ELEMENT);
    }

    /**
     * Взять первый элемент из очереди, если очередь пустая бросает исключение
     *
     * @return первый объект в очереди
     */
    public Object top() {
        return queueArray.get(FIRST_ELEMENT);
    }

    /**
     * Метод возвращает копию массив всех объектов
     *
     * @return массив объектов в очереде
     */
    public Object[] getAll(){
        return queueArray.toArray();
    }

    /**
     * Форматирует строку чутка ближе к JSON
     *
     * @return форматируемую строку
     */
    @Override
    public String toString() {
        return "{" + "\n" +
                "\"queueArray\": " + queueArray + ",\n" +
                "\"queueSize\": " + queueArray.size() + ",\n" +
                "\"topElement\": " + this.top() + "\n" +
                "}";
    }
}
