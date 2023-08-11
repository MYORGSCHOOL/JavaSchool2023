package skrebkov;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Класс Stack - является реализацией стэка работающего по принципу
 * "последним пришёл, первым вышел" (LIFO - Last-In-First-Out)
 */
public class Stack {
    /**
     * Коллекция для хранения стэка
     */
    private final ArrayList<Object> stackArray;

    public Stack() {
        stackArray = new ArrayList<>();
    }

    /**
     * Метод для проверки наличия объектов в стэке
     *
     * @return true если стак пустое false если в нём есть объекты
     */
    public boolean isEmpty() {
        return stackArray.size() == 0;
    }

    /**
     * Добовляет объект в стэк
     *
     * @param object - объект для добавления
     */
    public void push(Object object) {
        stackArray.add(object);
    }

    /**
     * Удаляет и возвращает последний объект из стэка
     *
     * @return Последний объект добавленный в стак
     */
    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray.remove(topElement());
    }

    /**
     * возвращяет верхний объект из стака
     *
     * @return Последний объект добавленный в стэка
     */
    public Object top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray.get(topElement());
    }

    /**
     * Метод для подсчета индекса верхнего элемента в стэке
     *
     * @return индекс верхнего элемента
     */
    private int topElement() {
        return stackArray.size() - 1;
    }

    /**
     * Метод возвращает копию массив всех объектов
     *
     * @return массив объектов в стэке
     */
    public Object[] getAll() {
        return stackArray.toArray();
    }

    /**
     * Форматирует строку чутка ближе к JSON
     *
     * @return форматируемую строку
     */
    @Override
    public String toString() {

        return "{" + "\n" +
                "\"stackArray\": " + stackArray + ",\n" +
                "\"stackSize\": " + stackArray.size() + ",\n" +
                "\"topElement\": " + this.top() + "\n" +
                "}";
    }
}
