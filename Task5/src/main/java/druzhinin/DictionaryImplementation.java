package druzhinin;

import druzhinin.exceptions.EmptyDictionaryException;

import java.util.*;

/**
 * Класс, реализующий интерфейс словаря Dictionary.<br/>
 * Методы вставки, удаления, проверки на наличие элемента<br/>
 * и получения случайного элемента выполняются в среднем с временной сложностью O(1).
 *
 * @author Дружинин Артем.
 * @param <T> Тип хранимых в справочнике элементов.
 */
public class DictionaryImplementation<T> implements Dictionary<T> {
    /**
     * Список элементов словаря.<br/>
     * Используется для выполнения метода получения случайного элемента за константное время.
     */
    private final List<T> elementsArrayList;

    /**
     * Словарь элементов.<br/>
     * Ключами словаря является собственно элементы,<br/>
     * а значениями - индексы этих элементов в списке элементов {@code elementsArrayList}.
     */
    private final Map<T, Integer> elementAndIndexHashMap;

    public DictionaryImplementation() {
        elementsArrayList = new ArrayList<>();
        elementAndIndexHashMap = new HashMap<>();
    }

    public DictionaryImplementation(int initialCapacity) {
        elementsArrayList = new ArrayList<>(initialCapacity);
        elementAndIndexHashMap = new HashMap<>(initialCapacity);
    }

    /**
     * Метод для вставки элемента в словарь.<br/>
     * В связи с использованием в своей основе {@code Map} и {@code List} выполняется с временной сложностью O(1).<br/>
     * Вставка элемента происходит только в том случае, если этот элемент не содержится в словаре.
     *
     * @param element Элемент для вставки в словарь.
     */
    @Override
    public void insert(T element) {
        if (!contains(element)) {
            elementsArrayList.add(element);
            elementAndIndexHashMap.put(element, elementsArrayList.size() - 1);
        }
    }

    /**
     * Метод для получения случайного элемента словаря.<br/>
     * Выполняется с временной сложностью O(1) за счет обращения к методу получения по индексу у {@code List}.
     *
     * @return Возвращает случайный элемент словаря.
     * @throws EmptyDictionaryException если метод вызван для пустого словаря.
     */
    @Override
    public T getRandom() {
        if (elementsArrayList.isEmpty()) {
            throw new EmptyDictionaryException();
        }

        return elementsArrayList.get(new Random().nextInt(elementsArrayList.size()));
    }

    /**
     * Метод для удаления элемента из словаря (по значению).
     *
     * @param element Элемент, который нужно удалить.
     * @return Возвращает true, если элемент в словаре был найден и успешно удален,
     *         иначе - false.
     */
    @Override
    public boolean delete(T element) {
        if (!contains(element)) {
            return false;
        }

        Integer deletedElementIndex = elementAndIndexHashMap.remove(element);
        elementsArrayList.set(deletedElementIndex, elementsArrayList.get(elementsArrayList.size() - 1));
        elementsArrayList.remove(elementsArrayList.size() - 1);
        elementAndIndexHashMap.replace(elementsArrayList.get(deletedElementIndex), deletedElementIndex);
        return true;
    }

    /**
     * Метод для проверки, содержится ли элемент в словаре.
     *
     * @param element Элемент, наличие в словаре которого необходимо проверить.
     * @return Возвращает true, если элемент в словаре найден, иначе - false.
     */
    @Override
    public boolean contains(T element) {
        return elementAndIndexHashMap.containsKey(element);
    }

    /**
     * Метод для получения количества элементов в словаре.
     *
     * @return Возвращает количество элементов.
     */
    @Override
    public int size() {
        return elementAndIndexHashMap.size();
    }

    @Override
    public String toString() {
        return elementsArrayList.toString();
    }
}
