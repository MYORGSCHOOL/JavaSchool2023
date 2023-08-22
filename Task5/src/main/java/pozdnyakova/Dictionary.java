package pozdnyakova;

/**
 * интерфейс справочника
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
     * получение случайного элемента из справочника
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
     * метод для проверки наличия элемента в справочнике
     *
     * @param element искомый элемент
     * @return true - элемент есть в справочнике, false - элемента нет в справочнике
     */
    boolean consist(G element);
}
