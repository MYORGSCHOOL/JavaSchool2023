package sevostyanov;

/**
 * Интерфейс для справочника объектов
 *
 * @param <G> тип объектов справочника
 */
public interface Dictionary<G> {
    /**
     * Метод вставляет объект в справочник
     *
     * @param element элемент вставки
     */
    void insert(G element);

    /**
     * Метод возвращает случайный элемент в справочнике
     *
     * @return возвращает случайный элемент из справочника
     */
    G getRandom();

    /**
     * Метод удаляет случайный элемент из справочника
     *
     * @param element удаляет элеменгт из справочника
     */
    void delete(G element);

    /**
     * Метод для проверки наличия объекта
     *
     * @param element элемент для провекрки
     * @return true если есть объект, иначе false
     */
    boolean consist(G element);
}
