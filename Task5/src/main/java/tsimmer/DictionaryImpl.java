package tsimmer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Класс реализующий интерфейс справочника
 *
 * @param <G> тип объектов в справочника
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * Поле для хранения массива и ключей
     */
    private final HashMap<G, Integer> hashMap;
    /**
     * Поле для хранения списка
     */
    private final ArrayList<G> array;

    public DictionaryImpl() {
        this.hashMap = new HashMap<>();
        this.array = new ArrayList<>();
    }

    /**
     * Метод добавляющий элементы в справочник
     *
     * @param element добавляемый элемент
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new DictionaryException("Такой элемент в справочнике существует.");
        }
        this.array.add(element);
        this.hashMap.put(element, array.size() - 1);
    }

    /**
     * Метод получающий случайный элемент из справочника
     *
     * @return случайный элемент
     */
    @Override
    public G getRandom() {
        if (array.isEmpty() && hashMap.isEmpty()) {
            throw new DictionaryException("Справочник пустой.");
        }
        return array.get(new Random().nextInt(hashMap.values().size()));
    }

    /**
     * Метод удаляющий элементы в справочнике
     *
     * @param element удаляемый элемент
     */
    @Override
    public void delete(G element) {
        if (!consist(element)) {
            throw new DictionaryException("Такого элемента в справочнике нет");
        }
        Integer index = hashMap.remove(element);
        changeElementIndex(index);
        this.array.remove(array.size() - 1);
    }

    /**
     * Метод проверяющий наличие элемента в справочнике
     *
     * @param element искомый элемент
     * @return true, если элемент найден
     */
    @Override
    public boolean consist(G element) {
        return hashMap.containsKey(element);
    }

    /**
     * Метод переставляющий элемент с указанного индекса в конец справочника
     *
     * @param index индекс откуда нужно переставить элемент
     */
    private void changeElementIndex(int index) {
        final int finalIndex = array.size() - 1;
        if (index != finalIndex) {
            array.set(index, array.get(array.size() - 1));
            this.hashMap.replace(array.get(finalIndex), index);
        }
    }

    /**
     * Метод выводящий элементы словаря
     *
     * @return элементы словаря
     */
    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "hashMap=" + hashMap +
                ", array=" + array +
                '}';
    }
}

