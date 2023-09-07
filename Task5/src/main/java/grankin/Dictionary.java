package grankin;

/**
 * Nнтерфейс для работы со словарем
 * @param <G>   - хранимый элемент словаря
 */
public interface Dictionary<G> {
    /**
     * Добавить элемент в словарь
     * @param element   - новый элемент
     */
    void insert(G element);

    /**
     * Получить случайное значение из словаря
     * @return      - случайное значение из словаря, или null, если словарь пустой
     */
    G getRandom();

    /**
     * Удалить элемент из словаря по значению
     * @param element   - удаляемый элемент
     */
    void delete(G element);

    /**
     * Проверка, содержится ли элемент в словаре или нет
     * @param element   - проверяемый элемент
     * @return          - true если есть, false если нет
     */
    boolean consist(G element);
}