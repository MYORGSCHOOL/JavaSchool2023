package pozdnyakova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий интерфейс справочника
 *
 * @param <G> тип объектов в справочнике
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * Мапа для сохранения объекта и его индекса в массиве
     */
    private final HashMap<G, Integer> hashMap;
    /**
     * Массив объектов справочника для получения случайного элемента за константное время
     */
    private final ArrayList<G> array;

    public DictionaryImpl() {
        this.array = new ArrayList<>();
        this.hashMap = new HashMap<>();
    }

    /**
     * Реализация метода добавления элемента в справочник
     *
     * @param element добавляемый элемент
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new IllegalArgumentException("Такой элемент уже есть в справочнике!");
        }
        array.add(element);
        hashMap.put(element, array.size() - 1);

    }

    /**
     * Реализация метода получения случайного элемента из справочника
     *
     * @return случайный элемент
     */

    @Override
    public G getRandom() {
        if (hashMap.isEmpty() && array.isEmpty()) {
            throw new NoSuchElementException("Справочник пустой!");
        }
        int randomNumber = (int) (Math.random() * array.size());
        return array.get(randomNumber);
    }

    /**
     * Реализация метода удаления элемента из справочника
     *
     * @param element удаляемый элемент
     */

    @Override
    public void delete(G element) {
        if (hashMap.isEmpty() && array.isEmpty()) {
            throw new NoSuchElementException("Справочник пустой!");
        }
        if (!consist(element)) {
            throw new IllegalArgumentException("Такого элемента нет в справочнике!");
        }
        int index = hashMap.remove(element);
        if (index == array.size() - 1) {
            array.remove(index);
        } else {
            swap(index);
            hashMap.put(array.get(index), index);
        }

    }

    /**
     * Реализация метода для проверки наличия элемента в справочнике
     *
     * @param element искомый элемент
     * @return true - элемент есть в справочнике, false - элемента нет в справочнике
     */
    @Override
    public boolean consist(G element) {
        return hashMap.containsKey(element);
    }

    /**
     * Метод для перестановки в массиве последнего элемента на место удаляемого элемента
     *
     * @param index индекс удаляемого элемента
     */
    private void swap(int index) {
        G element = array.get(array.size() - 1);
        array.set(index, element);
        array.remove(array.size() - 1);
    }
}
