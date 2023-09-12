package volovnik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Реализация интерфейса справочника Dictionary
 *
 * @param <T>
 */
public class DictionaryImpl<T> implements Dictionary<T> {

    /**
     * HashMap для хранения значений справочника (ключи) и их индексов в массиве (значения)
     */
    private final HashMap<T, Integer> hashMap;

    /**
     * ArrayList для хранения значений справочника
     */
    private final ArrayList<T> array;

    public DictionaryImpl() {
        this.hashMap = new HashMap<>();
        this.array = new ArrayList<>();
    }

    /**
     * Метод для вставки элемента в справочник
     *
     * @param element добавляемый элемент
     */
    @Override
    public void insert(T element) {
        if (consist(element)) {
            throw new DictionaryException("Данный элемент уже добавлен в словарь");
        }
        array.add(element);
        hashMap.put(element, array.size() - 1);
    }

    /**
     * Метод для получения случайного элемента из справочника
     *
     * @return случайный элемент
     */
    @Override
    public T getRandom() {
        if (hashMap.isEmpty()) {
            throw new DictionaryException("Словарь пуст");
        }
        Random random = new Random();
        return array.get(random.nextInt(array.size() - 1));
    }

    /**
     * Метод для удаления элемента из справочника
     *
     * @param element удаляемый элемент
     */
    @Override
    public void delete(T element) {
        if (!hashMap.containsKey(element)) {
            throw new DictionaryException("Данный элемент отсутствует");
        }
        int index = hashMap.get(element);
        swap(index);
        hashMap.remove(element);
        array.remove(array.size() - 1);
    }

    /**
     * Метод проверяет, существует ли элемент в справочнике
     *
     * @param element проверяемый элемент
     * @return true - если элемент есть в справочнике, иначе false
     */
    @Override
    public boolean consist(T element) {
        return hashMap.containsKey(element);
    }

    /**
     * Метод меняет местами элемент с последним
     *
     * @param index индекс элемента
     */
    private void swap(int index) {
        int lastIndex = array.size() - 1;
        array.set(index, array.get(lastIndex));
        hashMap.replace(array.get(lastIndex), index);
    }
}
