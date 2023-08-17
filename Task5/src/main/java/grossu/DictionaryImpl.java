package grossu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Класс для реализации словаря
 * Класс реализует интерфейс Dictionary
 *
 * @param <G> тип данных, хранимых в словаре
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * Поле для хранения списка элементов
     * Введено для быстрого получения случайного элемента в словаре
     */
    private final ArrayList<G> arrayOfElements;
    /**
     * Поле для хранения массива элементов и их индексов в списке
     */
    private final HashMap<G, Integer> hashMapOfElements;
    /**
     * Поле для получения случайного индекса из ArrayList
     */
    private final static Random rand = new Random();

    public DictionaryImpl() {
        this.arrayOfElements = new ArrayList<>();
        this.hashMapOfElements = new HashMap<>();
    }

    public DictionaryImpl(int initialCapacity) {
        this.arrayOfElements = new ArrayList<>(initialCapacity);
        this.hashMapOfElements = new HashMap<>(initialCapacity);
    }

    /**
     * Метод позволяет вставить элемент в конец списка ArrayList и HashMap.
     * Вставка проходит только в том случае, если передаваемого элемента нет в словаре.
     * Метод отрабатывает за O(1)
     *
     * @param element объект, который будет добавлен в словарь
     * @throws DictionaryException - если передаваемый элемент есть в словаре
     */
    @Override
    public void insert(G element) {
        if (!consist(element)) {
            arrayOfElements.add(element);
            hashMapOfElements.put(element, arrayOfElements.size() - 1);
        } else {
            throw new DictionaryException("This element already exist in dictionary");
        }

    }

    /**
     * Метод для удаления элемента в словаре.
     * Сначала проверяется наличие элемента в словаре,
     * если есть, то удаляется элемент из HashMap и возвращается его индекс в index,
     * затем местами меняются элементы в ArrayList со следующими индексами (index <-> size()-1),
     * после из arrayOfElements с конца удаляется передаваемый элемент.
     * В HashMap переписывается индекс у объекта, который был переставлен с конца в ArrayList.
     * Если элемент в словаре отсутствует, будет выброшено исключение DictionaryException
     * Метод отрабатывает за O(1)
     *
     * @param element элемент, который необходимо удалить из словаря
     * @throws DictionaryException, если в словаре нет передаваемого элемент
     */
    @Override
    public void delete(G element) {
        if (consist(element)) {
            int index = hashMapOfElements.remove(element);
            swap(arrayOfElements, index);
            hashMapOfElements.put(arrayOfElements.get(index), index);
            arrayOfElements.remove(arrayOfElements.size() - 1);
        } else {
            throw new DictionaryException("Dictionary doesn't contain element to be removed");
        }
    }

    /**
     * Метод проверяет наличие передаваемого объекта в словаре.
     * Если элемент в словаре есть, метод возвращает true, если нет false
     * Метод отрабатывает за O(1)
     *
     * @param element элемент, наличие которого необходимо проверить
     * @return true - элемент есть, false - объекта нет
     */
    @Override
    public boolean consist(G element) {
        return hashMapOfElements.containsKey(element);
    }

    /**
     * Метод получает рандомный элемент из существующих в словаре
     * Метод отрабатывает за O(1)
     *
     * @return рандомный элемент из словаря
     * @throws DictionaryException, если словарь пуст
     */
    @Override
    public G getRandom() {
        if (!arrayOfElements.isEmpty()) {
            return arrayOfElements.get(rand.nextInt(arrayOfElements.size()));
        } else {
            throw new DictionaryException("Dictionary is empty");
        }
    }

    /**
     * Метод меняет местами элементы в ArrayList,
     * объект с передаваемым индексом меняется с объектом стоящим в конце
     * Метод отрабатывает за O(1)
     *
     * @param array ArrayList, в котором необходимо поменять местами элементы
     * @param index индекс элемента, который будет переставлен
     */
    private void swap(ArrayList<G> array, int index) {
        Collections.swap(array, index, array.size() - 1);
    }

    /**
     * Метод для вывода информации о словаре
     * Метод отрабатывает за O(1)
     *
     * @return информация о словаре
     */
    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "arrayOfElements=" + arrayOfElements +
                ",\n hashMapOfElements=" + hashMapOfElements +
                '}';
    }
}
