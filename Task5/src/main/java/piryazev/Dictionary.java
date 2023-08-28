package piryazev;

/**
 * Интерфейс реализации словаря
 *
 * @param <G> любой принимаемый тип данных
 */
public interface Dictionary<G> {
    /**
     * Метод вставки элемента в конец словаря
     *
     * @param element - вставляемый элемент
     */
    void insert(G element);

    /**
     * Метод получения случайного элемента
     *
     * @return возвращает случайный элемент из словаря
     */
    G getRandom();

    /**
     * Метод удаления элемента
     *
     * @param element - элемент, который нужно удалить
     */
    void delete(G element);

    /**
     * Метод проверки есть ли элемент в словаре
     *
     * @param element - проверяемый элемент
     * @return True - есть элемент False - нет элемента
     */
    boolean consist(G element);
}
