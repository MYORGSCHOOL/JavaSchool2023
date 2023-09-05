package volovnik;

/**
 * Интерфейс справочника
 * @param <T> тип данных
 */
public interface Dictionary<T> {
    void insert(T element);
    T getRandom();
    void delete(T element);
    boolean consist(T element);
}
