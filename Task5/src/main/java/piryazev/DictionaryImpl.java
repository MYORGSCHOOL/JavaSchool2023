package piryazev;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * Класс реализации интерфейса словаря
 *
 * @param <G> любой принимаемый тип данных
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * Словарь HashMap с хранением индексов и значений
     */
    private final HashMap<G, Integer> hashMap;
    /**
     * Список для хранения элементов. Нужен для получения случайного элемента за О(1)
     */
    private final List<G> array;

    public DictionaryImpl() {
        this.hashMap = new HashMap<>();
        this.array = new ArrayList<>();
    }

    /**
     * Метод вставки элемента в конец словаря за О(1)
     *
     * @param element - вставляемый элемент
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new KeyAlreadyExistsException("Element already in dictionary");
        }
        this.array.add(element);
        this.hashMap.put(element, array.size());
    }

    /**
     * Метод получения случайного элемента из словаря
     *
     * @return возвращает случайный элемент из словаря за О(1)
     */
    @Override
    public G getRandom() {
        if (this.hashMap.isEmpty()) {
            throw new NoSuchElementException("Dictionary is empty");
        }
        return this.array.get((int) (Math.random() * array.size()));
    }

    /**
     * Метод удаления элемента из словаря за О(1)
     *
     * @param element - удаляемый элемент
     */
    @Override
    public void delete(G element) {
        if (!consist(element)) {
            throw new NoSuchElementException("There is no such element");
        }
        int index = this.hashMap.remove(element);
        swap(array, index);
        hashMap.put(array.get(index), index);
        array.remove(array.size() - 1);
    }

    /**
     * Метод проверки на существование элемента в словаре за О(1)
     *
     * @param element проверяемый элемент
     * @return True - элемент есть, False - нет элемента
     */
    @Override
    public boolean consist(G element) {
        return this.hashMap.containsKey(element);
    }

    /**
     * Меняем местами последний элемент и удаленный элемент, чтобы не было пропусков внутри словаря
     *
     * @param array массив, в котором меняются элементы
     * @param index индекс элемента для замены
     */
    public void swap(List<G> array, int index) {
        Collections.swap(array, index, array.size() - 1);
    }

    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "array=" + array +
                '}';
    }

    public List<G> getArray() {
        return array;
    }
}
