package skrebkov;

import java.util.HashMap;
import java.util.Random;

/**
 * Класс реализующий интерфейс Dictionary
 * Представляющий собой словарь хранящий уникальные элементы
 *
 * @param <G> тип данных хранящиеся в словаре
 */
public class DictionaryImpl<G> implements DictionaryI<G> {
    /**
     * HashMap хранит словарь для реализации поиска по элементу за константное время.
     * Элементы словаря являются ключем, а в качестве значения выступает номер элемента во втором хешмапе
     */
    private final HashMap<G, Integer> firstDictionaryMap;
    /**
     * HashMap в качестве ключа выступает номер элемента, а в качестве значения элемент.
     * Используется для реализации взятия рандомного элемента за константное время
     */
    private final HashMap<Integer, G> secondDictionaryMap;

    public DictionaryImpl() {
        this.firstDictionaryMap = new HashMap<>();
        this.secondDictionaryMap = new HashMap<>();
    }

    /**
     * Метод вставляющий element в словарь
     *
     * @param element элемент для словаря
     * @throws DictionaryException если в словаре уже есть этот элемент
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new DictionaryException("The dictionary already has this element");
        }
        this.firstDictionaryMap.put(element, this.firstDictionaryMap.size());
        this.secondDictionaryMap.put(this.secondDictionaryMap.size(), element);
    }

    /**
     * Метод берёт слусайный элемент из словаря
     *
     * @return возвращает элемент из словаря
     * @throws DictionaryException если словарь пустой
     */
    @Override
    public G getRandom() {
        if (this.firstDictionaryMap.isEmpty()) {
            throw new DictionaryException("The dictionary is empty");
        }
        Random random = new Random();
        return this.secondDictionaryMap.get(random.nextInt(this.secondDictionaryMap.size()));
    }

    /**
     * Метод удаляет элемент из словаря
     *
     * @param element элемент для удаления в словаре
     * @throws DictionaryException если элемента нет в словаре
     */
    @Override
    public void delete(G element) {
        if (!this.consist(element)) {
            throw new DictionaryException("The element is not in the dictionary");
        }
        int lastIndex = this.firstDictionaryMap.size() - 1;
        int indexInList = this.firstDictionaryMap.remove(element);
        G lastElement = this.secondDictionaryMap.remove(lastIndex);
        this.secondDictionaryMap.replace(indexInList, lastElement);
        this.firstDictionaryMap.replace(lastElement, indexInList);
    }

    /**
     * Метод проверяет наличие элемента в словаре
     *
     * @param element элемент для поиска в словаре
     * @return true - если в словаре есть элемент, false - если нет
     */
    @Override
    public boolean consist(G element) {
        return this.firstDictionaryMap.containsKey(element);
    }

    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "dictionaryMap=" + this.firstDictionaryMap +
                ", dictionaryList=" + this.secondDictionaryMap +
                '}';
    }
}
