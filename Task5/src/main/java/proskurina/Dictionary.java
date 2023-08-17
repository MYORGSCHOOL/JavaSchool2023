package proskurina;

import proskurina.exceptions.EmptyDictionaryException;

/**
 * интерфейс коллекции справочника, который не поддерживает дублирующиеся элементы.
 *
 * @param <G> тип элементов в справочнике
 */
public interface Dictionary<G> {
    /**
     * Добавляет элемент в справочник.
     *
     * @param element добавляемый элемент.
     */
    void insert(G element);
    
    /**
     * Возвращает случайный элемент справочника.
     *
     * @return случайный элемент
     * @throws EmptyDictionaryException если справочник пуст
     */
    G getRandom();
    
    /**
     * Удаляет элемент из справочника. Если в справочнике нет такого элемента,
     * то вернет null.
     *
     * @param element удаляемый элемент
     */
    void delete(G element);
    
    /**
     * Возвращает true, если элемент есть в справочнике.
     *
     * @param element искомый элемент
     * @return true если элемент есть в справочнике
     */
    boolean consist(G element);
}