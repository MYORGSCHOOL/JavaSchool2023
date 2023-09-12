package malakhova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Класс, реализующий словарь объектов.
 * В данном классе используется ArrayList для хранения в нем элементов словаря и HashMap,
 * в котором в качестве ключа хранятся элементы словаря,
 * а в качестве значений - индексы элементов словаря в ArrayList.
 * Класс расширяет методы интерфейса Dictionary:
 * insert - добавление элемента в конец словаря
 * getRandom - получение рандомного элемента, находящегося в словаре
 * delete - удалить элемент из словаря
 * consist - проверить наличие элемента в словаре
 * Также в классе содержится метод replaceAndRemove, используемый в методе delete, который перемещает элементы с конца словаря
 * в пустые ячейки и удаляет повтрояющиеся значения в конце словаря
 *
 * @param <G> - generic для определения типа элементов словаря
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    private final HashMap<G, Integer> hashMap;
    private final ArrayList<G> arrayList;

    public DictionaryImpl() {
        this.arrayList = new ArrayList<>();
        this.hashMap = new HashMap<>();
    }

    /**
     * Метод, добавляющий элемент в словарь
     *
     * @param element - элемент, который необходимо сохранить в словаре
     */
    @Override
    public void insert(G element) {
        hashMap.put(element, arrayList.size());
        arrayList.add(element);
    }

    /**
     * Метод, возвращающий случайный элемент словаря
     *
     * @return - элемент словаря
     */
    @Override
    public G getRandom() {
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Random r = new Random(System.currentTimeMillis());
        return arrayList.get(r.nextInt(arrayList.size()));
    }

    /**
     * Метод, удаляющий указанный элемент из словаря
     *
     * @param element - элемент, который необходимо удалить
     */
    @Override
    public void delete(G element) {
        if (!hashMap.containsKey(element) || arrayList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Integer index = hashMap.get(element);
        replaceAndRemove(index);
        hashMap.remove(element);
    }

    /**
     * Метод, проверяющий наличие в словаре указанного элемента
     *
     * @param element - элемент, который необходимо проверить
     * @return - true если элемент есть в словаре, false если нет
     */
    @Override
    public boolean consist(G element) {
        return hashMap.containsKey(element);
    }

    @Override
    public String toString() {
        return arrayList.toString();
    }

    /**
     * Метод, перемещающий элементы с конца словаря в пустые ячейки
     * и удаляющий повтрояющиеся значения в конце словаря
     *
     * @param index - индекс пустой ячейки в массиве
     */
    private void replaceAndRemove(Integer index) {
        arrayList.set(index, arrayList.get(arrayList.size() - 1));
        hashMap.put(arrayList.get(index), index);
        arrayList.remove(arrayList.size() - 1);
    }
}
