package savinskiy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Реализация интерфейса Dictionary
 *
 * @param <G> тип объектов справочника
 */
public class DictionaryImpl<G> implements Dictionary<G>{
    private final Random random;
    private final List<G> elements;
    private final Map<G, Integer> indexOfElements;

    public DictionaryImpl() {
        this.random = new Random();
        this.elements = new ArrayList<>();
        this.indexOfElements = new HashMap<>();
    }

    /**
     * Метод вставляет объект в справочник
     *
     * @param element для вставки
     */
    @Override
    public void insert(G element) {
        if (!indexOfElements.containsKey(element)) {
            indexOfElements.put(element, elements.size());
            elements.add(element);
        } else {
            throw new IllegalStateException("Element already in dictionary");
        }
    }

    /**
     * Метод возвращает случайный объект в справочнике
     *
     * @return случайный элемент из справочника
     */
    @Override
    public G getRandom() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Dictionary is empty");
        }
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }

    /**
     * Метод удалает конкретный объект в справочнике
     *
     * @param element для удаления
     */
    @Override
    public void delete(G element) {
        Integer index = indexOfElements.get(element);
        if (index != null) {
            int lastIndex = elements.size() - 1;
            G lastElement = elements.get(lastIndex);
            elements.set(index, lastElement);
            indexOfElements.put(lastElement, index);

            elements.remove(lastIndex);
            indexOfElements.remove(element);
        }
    }

    /**
     * Метод проверяет есть ли объект в справочнике
     *
     * @param element для проверки
     * @return true - если объект есть в справочнике, либо false
     */
    @Override
    public boolean consist(G element) {
        return indexOfElements.containsKey(element);
    }
}
