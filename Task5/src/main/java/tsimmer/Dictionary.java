package tsimmer;

/**
 * Интерфейс справочника
 *
 * @param <G> тип элементов в справочнике
 */
public interface Dictionary<G> {
    /**
     * Метод добавляющий элементы в справочник
     *
     * @param element добавляемый элемент
     */
    void insert(G element);

    /**
     * Метод получающий случайный элемент из справочника
     *
     * @return случайный элемент
     */
    G getRandom();

    /**
     * Метод удаляющий элементы в справочнике
     *
     * @param element удаляемый элемент
     */
    void delete(G element);

    /**
     * Метод проверяющий наличие элемента в справочнике
     *
     * @param element искомый элемент
     * @return true, если элемент найден
     */
    boolean consist(G element);
}
