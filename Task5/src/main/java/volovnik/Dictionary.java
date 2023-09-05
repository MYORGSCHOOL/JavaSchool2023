package volovnik;

/**
 * Интерфейс справочника
 *
 * @param <T> тип данных
 */
public interface Dictionary<T> {

    /**
     * Метод вставки нового элемента в словарь
     *
     * @param element добавляемый элемент
     */
    void insert(T element);

    /**
     * Метод получения случайного элемента
     *
     * @return случайный элемент
     */
    T getRandom();

    /**
     * Метод удаления элемента из справочника
     *
     * @param element удаляемый элемент
     */
    void delete(T element);

    /**
     * Метод проверки на наличие элемента в справочнике
     *
     * @param element проверяемый элемент
     * @return true - если элемент есть в справочнике, иначе false
     */
    boolean consist(T element);
}
