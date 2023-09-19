package karmanchikova;

/**
 * Интерфейс справочника
 *
 * @param <G> тип элементов в справочнике
 */
public interface Dictionary<G> {
    /**
     * добавление элемента в справочник
     *
     * @param element добавляемый элемент
     */
    void insert(G element);

    /**
     * Получение случайного элемента из справочника
     *
     * @return случайный элемент
     */
    G getRandom();

    /**
     * удаление элемента из справочника
     *
     * @param element удаляемый элемент
     */
    void delete(G element);

    /**
     * Поиск элемента в справочнике
     *
     * @param element элемент, который необходимо найти
     * @return true - найден, false-элемент не найден
     */
    boolean consist(G element);
}