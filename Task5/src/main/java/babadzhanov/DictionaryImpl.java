package babadzhanov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Класс реализующий интерфейс Dictionary
 */
public class DictionaryImpl<G> implements Dictionary<G> {

    /**
     * Мапа состоящая из объектов и их индексов.
     */
    private final HashMap<G, Integer> hashMap;

    /**
     * Список объектов, для поиска случайного объекта в справочнике за константное время.
     */
    private final ArrayList<G> arrayList;

    public DictionaryImpl() {
        this.hashMap = new HashMap<>();
        this.arrayList = new ArrayList<>();
    }

    /**
     * Реализация метода добавления элемента в справочник.
     *
     * @param element элемент для вставки.
     */
    @Override
    public void insert(G element) {
        if (contains(element)) {
            throw new IllegalArgumentException("Данный элемент уже существует в списке");
        }
        arrayList.add(element);
        hashMap.put(element, arrayList.size() - 1);
    }

    /**
     * Реализация метода удаляния элемента из справочника.
     *
     * @param element элемент для удаления.
     */
    @Override
    public void delete(G element) {
        if (hashMap.isEmpty() && arrayList.isEmpty() || !contains((element))) {
            throw new NoSuchElementException("Элемент для удаления не найден");
        }
        Integer index = hashMap.remove(element);
        deleteFromListWithSwap(index);
        hashMap.put(arrayList.get(index), index);
    }

    /**
     * Реализация метода для возврата случайного элемента из справочника.
     *
     * @return случайный элемент.
     */
    @Override
    public G getRandom() {
        if (arrayList.isEmpty()) {
            throw new NoSuchElementException("Справчник пустой");
        }
        return arrayList.get((int) (Math.random() * arrayList.size()));
    }

    /**
     * Реализация метода проверки наличия элемента в справочнике.
     *
     * @param element элемент проверяемый на наличие.
     * @return true - если элемент содержится в справочнике, false - если не содержится.
     */
    @Override
    public boolean contains(G element) {
        return hashMap.containsKey(element);
    }

    /**
     * Метод переставляет удаляемый элемент в конец списка и удаляет его
     *
     * @param index индекс удаляемого элемента
     */
    private void deleteFromListWithSwap(int index) {
        G element = arrayList.get(arrayList.size() - 1);
        arrayList.set(index, element);
        arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "hashMap=" + hashMap +
                ", arrayList=" + arrayList +
                '}';
    }
}
