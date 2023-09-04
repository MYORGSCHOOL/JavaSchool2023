package savinskiy;

/**
 * Интерфейс для справочника объектов
 *
 * @param <G> тип объектов справочника
 */
public interface Dictionary <G> {
    /**
     * Метод вставляет объект в справочник
     *
     * @param element для вставки
     */
    void insert(G element);
    /**
     * Метод возвращает случайный объект в справочнике
     *
     * @return случайный элемент из справочника
     */
    G getRandom();
    /**
     * Метод удалает конкретный объект в справочнике
     *
     * @param element для удаления
     */
    void delete(G element);
    /**
     * Метод проверяет есть ли объект в справочнике
     *
     * @param element для проверки
     * @return true - если объект есть в справочнике, либо false
     */
    boolean consist(G element);
}
