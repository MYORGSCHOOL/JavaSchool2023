package tumbaev;

import tumbaev.exception.DictionaryDeletionException;
import tumbaev.exception.DictionaryInsertionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryImpl<G> implements Dictionary<G> {

    /**
     * The mapping from elements to their indexes in {@link #elements} array.
     */
    private final Map<G, Integer> elementsToIndexes;

    /**
     * Array of elements. It is required to get random element in O(1) time.
     */
    private final List<G> elements;

    /**
     * Constructs an empty dictionary.
     */
    public DictionaryImpl() {
        this.elementsToIndexes = new HashMap<>();
        this.elements = new ArrayList<>();
    }

    /**
     * Constructs an empty dictionary with specified initial capacity.
     *
     * @param initialCapacity initial capacity of this dictionary
     * @throws IllegalArgumentException initial capacity is negative
     */
    public DictionaryImpl(int initialCapacity) {
        this.elementsToIndexes = new HashMap<>(initialCapacity);
        this.elements = new ArrayList<>(initialCapacity);
    }

    @Override
    public void insert(G element) {
        if (contains(element)) {
            throw new DictionaryInsertionException("Cannot insert element, because it's already in the dictionary.");
        }
        elements.add(element);
        elementsToIndexes.put(element, elements.size() - 1);
    }

    @Override
    public G getRandom() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get((int) (Math.random() * elements.size()));
    }

    @Override
    public void delete(G element) {
        if (!contains(element)) {
            throw new DictionaryDeletionException("Cannot delete element, because it's not in the dictionary.");
        }
        int index = elementsToIndexes.get(element);
        swapWithLastElement(index);
        removeLastElement();
    }

    private void swapWithLastElement(int index) {
        G curr = elements.get(index);
        G last = elements.get(elements.size() - 1);

        elementsToIndexes.put(curr, elements.size() - 1);
        elementsToIndexes.put(last, index);

        elements.set(index, last);
        elements.set(elements.size() - 1, curr);
    }

    private void removeLastElement() {
        elementsToIndexes.remove(elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);
    }

    @Override
    public boolean contains(G element) {
        return elementsToIndexes.containsKey(element);
    }
}
