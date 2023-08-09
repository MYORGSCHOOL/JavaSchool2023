package grankin;

/**
 * Nнтерфейс для работы со словарем
 * @param <G>   - хранимый элемент словаря
 */
public interface Dictionary<G> {
    void insert(G element);
    G getRandom();
    void delete(G element);
    boolean consist(G element);
}