package alexenko;

/**
 * Interface с обобщённым типом.
 * Является абстракцией словаря.
 *
 * @param <G> обобщённый тип
 */
public interface Dictionary<G> {

    /**
     * Вставка элемента
     *
     * @param element вставляемый элемент
     */
    void insert(G element);

    /**
     * Удаление элемента
     *
     * @param element удаляемый элемент
     */
    void delete(G element);

    /**
     * Поиск элемента
     *
     * @param element искомый элемент
     * @return true - элемент найден, false - элемент не найден
     */
    boolean consist(G element);

    /**
     * Получение случайного элемента
     *
     * @return случайный элемент
     */
    G getRandom();
}
