package dushkina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Класс, который представляет справочник обьектов
 * Реализация интерфейса Dictionary <G>
 *
 * @param <G> - тип данных, хранимых в словаре
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * Поле для хранения списка элементов типа G
     */
    private final ArrayList<G> arrayOfElements;
    /**
     * Поле для хранения элемент-индекс в списке
     */
    private final HashMap<G, Integer> hashMapOfElements;
    /**
     * Поле для получения рандомного индекса из ArrayList
     */
    private final Random random;

    public DictionaryImpl() {
        this.arrayOfElements = new ArrayList<>();
        this.hashMapOfElements = new HashMap<>();
        this.random = new Random();
    }

    /**
     * Метод, вставляющий элемент в справочник
     *
     * @param element - элемент, который необходимо вставить в справочник
     * @throws DictionaryException - если такой элемент в списке есть
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new DictionaryException("Такой элемент в словаре есть");
        }
        arrayOfElements.add(element);
        hashMapOfElements.put(element, arrayOfElements.size() - 1);
    }

    /**
     * Метод, удаляющий элемент из справочника
     * т.к. у нас ArrayList, после удаления образуются пустоты,
     * чтобы этого избежать, удаляемый элемент меняем местами с крайним элементом(swap())
     * и удаляем с конца
     *
     * @param element - элемент, который необходимо удалить
     * @throws DictionaryException - если элемента нет в справочнике
     */
    @Override
    public void delete(G element) {
        if (!consist(element)) {
            throw new DictionaryException("Словарь не содержит данного элемента");
        }
        Integer removeInteger = hashMapOfElements.remove(element);
        swap(arrayOfElements, removeInteger);
        hashMapOfElements.put(arrayOfElements.get(removeInteger), removeInteger);
        arrayOfElements.remove(arrayOfElements.size() - 1);
    }

    /**
     * Метод, проверяющий наличие объекта в справочнике
     *
     * @param element - элемент, который необходимо проверить на наличие в справочнике
     * @return - true, если элемент находится в справочнике, иначе false
     */
    @Override
    public boolean consist(G element) {
        return arrayOfElements.contains(element);
    }

    /**
     * Метод, возвращающий рандомный элемент из справочника
     *
     * @return - рандомный элемент из справочника
     * @throws DictionaryException - если справочник пуст
     */
    @Override
    public G getRandom() {
        if (arrayOfElements.isEmpty()) {
            throw new DictionaryException("Справочник пуст");
        }
        return arrayOfElements.get(random.nextInt(arrayOfElements.size()));
    }

    /**
     * Метод, меняющий местами элемент под индексом index и крайний элемент
     *
     * @param list  - ArrayList, в котором меняем месами элементы
     * @param index - индекс элемента, который нужно поменять с крайним
     */
    private void swap(ArrayList<G> list, int index) {
        Collections.swap(list, index, list.size() - 1);
    }

    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "arrayOfElements=" + arrayOfElements +
                ", hashMapOfElements=" + hashMapOfElements +
                '}';
    }
}