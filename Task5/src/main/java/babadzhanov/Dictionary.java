package babadzhanov;

/**
 * Интерфейс справочника.
 *
 * @param <G> тип даннных элементов в справочнике.
 */
public interface Dictionary<G> {

    /**
     * Метод добавляет элемент в справочник.
     *
     * @param element элемент для вставки.
     */
    void insert(G element);

    /**
     * Метод удаляет элемент из справочника.
     *
     * @param element элемент для удаления.
     */
    void delete(G element);

    /**
     * Метод возвращает случайный элемент из справочника.
     *
     * @return случайный элемент.
     */
    G getRandom();

    /**
     * Метод проверяет наличие элемента в справочнике.
     *
     * @param element элемент проверяемый на наличие.
     * @return true - если элемент содержится в справочнике, false - если не содержится.
     */
    boolean contains(G element);


}
