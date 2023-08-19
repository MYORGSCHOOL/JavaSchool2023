package proskurina;

import proskurina.exceptions.AlreadyExistsException;
import proskurina.exceptions.EmptyDictionaryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.NoSuchElementException;


/**
 * Класс, реализующий динамическую коллекцию справочник.
 * имплементирует интерфейс {@code Dictionary}.
 * Не поддерживает дублирующиеся элементы.
 *
 * @param <G> тип элементов справочника
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    /**
     * Содержит все элементы справочника.
     * Нужен для получения случайного элемента справочника
     * за константное время.
     */
    private final ArrayList<G> array;
    
    /**
     * Содержит элемент справочника как ключь и
     * его индекс в массиве {@code array} как значение.
     */
    private final HashMap<G, Integer> hashMap;
    /**
     * Нужен для получения случайного индекса в {@code array}.
     */
    private final Random random;
    /**
     * Стандартный размер справочника.
     */
    private final static int STANDARD_CAPACITY = 16;
    
    public DictionaryImpl() {
        this(STANDARD_CAPACITY);
    }
    
    public DictionaryImpl(int initialCapacity) {
        this.hashMap = new HashMap<>(initialCapacity);
        this.array = new ArrayList<>(initialCapacity);
        this.random = new Random();
    }
    
    /**
     * Добавляет элемент в справочник.
     *
     * @param element добавляемый элемент
     * @throws AlreadyExistsException если элемент уже есть в справочнике
     */
    @Override
    public void insert(G element) {
        if (this.hashMap.containsKey(element)) {
            throw new AlreadyExistsException();
        }
        this.array.add(element);
        this.hashMap.put(element, this.array.size() - 1);
    }
    
    /**
     * Возвращает случайный элемент справочника.
     *
     * @return случайный элемент
     * @throws EmptyDictionaryException если справочник пуст
     */
    @Override
    public G getRandom() {
        if (this.array.isEmpty()) {
            throw new EmptyDictionaryException();
        }
        final int randIndex = this.random.nextInt(array.size());
        return this.array.get(randIndex);
    }
    
    /**
     * Удаляет элемент из справочника.
     *
     * @param element удаляемый элемент
     * @throws NoSuchElementException если элемента нет в справочнике
     */
    @Override
    public void delete(G element) {
        final Integer removeIndex = this.hashMap.remove(element);
        if (removeIndex == null) {
            throw new NoSuchElementException();
        }
        final int lastIndex = this.array.size() - 1;
        // Если удаляется не последний элемент из array
        if (removeIndex != lastIndex) {
            replaceByLastElement(removeIndex);
            // Обновляем индекс последнего элемента после перестановки
            this.hashMap.replace(this.array.get(lastIndex), removeIndex);
        }
        removeLastFromArray();
    }
    
    /**
     * Возвращает {@code true}, если элемент есть в справочнике.
     *
     * @param element искомый элемент
     * @return {@code true} если элемент есть в справочнике
     */
    @Override
    public boolean consist(G element) {
        return this.hashMap.containsKey(element);
    }
    
    /**
     * Возвращает количество элементов справочника.
     *
     * @return количество элементов справочника
     */
    public int size() {
        return this.array.size();
    }
    
    /**
     * Возвращает массив с элементами справочника.
     *
     * @return массив с элементами справчника
     */
    public Object[] toArray() {
        return this.hashMap.keySet().toArray();
    }
    
    @Override
    public String toString() {
        return "DictionaryImpl{" + "elements=" + this.hashMap.keySet() + '}';
    }
    
    /**
     * Удаляет все элементы из справочника.
     */
    public void clear() {
        this.hashMap.clear();
        this.array.clear();
    }
    
    /**
     * Удаляет последний элемент из {@code array}.
     *
     * @throws IndexOutOfBoundsException если {@code array} пуст
     */
    private void removeLastFromArray() {
        this.array.remove(this.array.size() - 1);
    }
    
    /**
     * Копирует последний элемент {@code array} и помещает по указанному индексу.
     *
     * @param index индекс заменяемого элемента
     * @throws IndexOutOfBoundsException если {@code index} меньше 0
     *                                   или больше или равен {@code size()}
     */
    private void replaceByLastElement(int index) {
        final G lastValue = this.array.get(this.array.size() - 1);
        this.array.set(index, lastValue);
    }
}