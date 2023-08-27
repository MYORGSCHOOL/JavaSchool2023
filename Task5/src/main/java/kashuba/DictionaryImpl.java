package kashuba;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * класс для реализации справочника
 *
 * @param <G> тип данных в справочнике
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * массив в котором хранятся все элементы справочника
     */
    private final ArrayList<G> arrayOfElements;
    /**
     * мапа в которой хранится объект и его индекс в массиве
     */
    private final HashMap<G, Integer> hashNapOfElements;

    public DictionaryImpl() {
        this.arrayOfElements = new ArrayList<>();
        this.hashNapOfElements = new HashMap<>();
    }

    /**
     * Метод добавления элемента в справочник
     *
     * @param element добавляемый элемент
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new DictionaryException("Елемент уже есть в справочнике");
        }
        arrayOfElements.add(element);
        hashNapOfElements.put(element, arrayOfElements.size() - 1);
    }

    /**
     * Метод возвращающий рандомный элемент справочника
     *
     * @return рандомный элемент
     */

    @Override
    public G getRandom() {
        if (arrayOfElements.isEmpty()) {
            throw new DictionaryException("Справочник пустой!");
        }
        return arrayOfElements.get((int) (Math.random() * arrayOfElements.size()));
    }

    /**
     * Метод удаляющий элемент в справочнике
     *
     * @param element удаляемый элемент
     */
    @Override
    public void delete(G element) {
        if (consist(element)) {
            int index = hashNapOfElements.remove(element);
            swap(arrayOfElements, index);
            hashNapOfElements.put(arrayOfElements.get(index), index);
        } else {
            throw new DictionaryException("Элемент отсутствует в справочнике");
        }
    }

    /**
     * Метод проверяющий наличие элемента в справочнике
     *
     * @param element который проверяем
     * @return true - элемент есть
     */
    @Override
    public boolean consist(G element) {
        return hashNapOfElements.containsKey(element);
    }

    /**
     * Метод перестоновки последнего элемента массива на место удаляемоего элемента
     *
     * @param arrayOfElements
     * @param index индекс удаляемого элемента
     */
    private void swap(ArrayList<G> arrayOfElements, int index) {
        G element = this.arrayOfElements.get(this.arrayOfElements.size() - 1);
        this.arrayOfElements.set(index, element);
        this.arrayOfElements.remove(this.arrayOfElements.size() - 1);
    }
}
