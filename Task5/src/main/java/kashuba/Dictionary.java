package kashuba;

/**
 * интерфейс справочника
 *
 * @param <G> тип элементов справочника
 */
public interface Dictionary<G> {
    /**
     * добавление элемента в справочник
     *
     * @param element добавляемый элемент
     */
    void insert(G element);

    /**
     * получение рандомного элемента из справочника
     *
     * @return рандомный элемент
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
     * @return true если элемент есть в справочнике
     */
    boolean consist(G element);
}
