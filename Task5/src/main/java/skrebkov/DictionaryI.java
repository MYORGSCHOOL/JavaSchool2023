package skrebkov;

/**
 * Интерфейс описывающий словарь
 *
 * @param <G> тип данных хранящийся в словаре
 */
public interface DictionaryI<G> {
    /**
     * Метод вставляющий element в словарь
     *
     * @param element - элемент для словаря
     */
    void insert(G element);

    /**
     * Метод берёт слусайный элемент из словаря
     *
     * @return возвращает элемент из словаря
     */
    G getRandom();

    /**
     * Метод удаляет элемент из словаря
     *
     * @param element - элемент для удаления в словаре
     */
    void delete(G element);

    /**
     * Метод проверяет наличие элемента в словаре
     *
     * @param element - элемент для поиска в словаре
     * @return true - если в словаре есть элемент, false - если нет
     */
    boolean consist(G element);
}
