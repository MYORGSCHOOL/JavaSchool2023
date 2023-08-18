package yanko;

/**
 * интерфейс словаря
 *
 * @param <G> тип объектов, хранящихся в словаре
 */
public interface Dictionary <G> {
    /**
     * вставка элемента в словарь
     *
     * @param e элемент
     */
    void insert(G e);
    /**
     * получение случайного элемента из словаря
     */
    G getRandom();
    /**
     * удаление элемента из словаря
     *
     * @param e элемент
     */
    void delete(G e);
    /**
     * проверка на то, что элемент содержится в словаре
     *
     * @param e элемент
     */
    boolean consist(G e);
}
