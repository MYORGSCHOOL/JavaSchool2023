package malakhova;

/**
 * Интерфейс, для создания словаря объектов, с использованием generics
 *
 * @param <G> - generic определяющий тип объектов в словаре
 */
public interface Dictionary<G> {
    /**
     * Метод для добавления элементов в словарь
     *
     * @param element - новый элемент
     */
    void insert(G element);

    /**
     * Метод для получения случайного элемента словаря
     *
     * @return - элемент словаря
     */
    G getRandom();

    /**
     * Метод для удаления элемента из словаря
     *
     * @param element - удаляемый элемент словаря
     */
    void delete(G element);

    /**
     * Метод, проверяющий наличие элемента в словаре
     *
     * @param element - проверяемый элемент
     * @return - true(элемент находится в словаре) или false(такого элемента в словаре нет)
     */
    boolean consist(G element);
}
