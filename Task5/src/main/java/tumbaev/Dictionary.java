package tumbaev;

/**
 * A generic data structure that stores only unique elements. All the operations work in O(1) time.
 */
public interface Dictionary<G> {

    /**
     * Inserts an element into the dictionary.
     *
     * @param element the element to be inserted
     * @throws tumbaev.exception.DictionaryInsertionException if the element is already in the dictionary
     */
    void insert(G element);

    /**
     * Retrieves a random element of type G.
     *
     * @return a random element of type G
     */
    G getRandom();

    /**
     * Deletes the specified element.
     *
     * @param element the element to be deleted
     * @throws tumbaev.exception.DictionaryDeletionException if the element is not in the dictionary
     */
    void delete(G element);

    /**
     * Determines if the specified element is present in the dictionary.
     *
     * @param element the element to search for
     * @return true if the element is present, false otherwise
     */
    boolean contains(G element);
}
