package sevostyanov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

/**
 * Класс реализует коллекцию справочника
 * имплементирует интерфейс Dictionary
 *
 * @param <G>
 */
public class DictionaryImpl<G> implements Dictionary<G> {

    private final Random random;
    private final List<G> elements;
    private final Map<G, Integer> indexOfElements;


    public DictionaryImpl() {
        this.random = new Random();
        this.elements = new ArrayList<>();
        this.indexOfElements = new HashMap<>();
    }

    /**
     * Добавляет элемент в справочник
     *
     * @param element элемент вставки
     */
    @Override
    public void insert(G element) {
        if (!indexOfElements.containsKey(element)) {
            indexOfElements.put(element, elements.size());
            elements.add(element);
        } else {
            throw new IllegalStateException("D");
        }
    }

    /**
     * Возвращает случайный элемент
     *
     * @return случайный элемент
     */
    @Override
    public G getRandom() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("asd");
        }
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }

    /**
     * Метод удаляет элемент из справочника
     *
     * @param element удаляет элеменгт из справочника
     */
    @Override
    public void delete(G element) {
        Integer index = indexOfElements.get(element);
        if (index != null) {
            int lastIndex = elements.size() - 1;
            G lastElement = elements.get(index);
            elements.set(index, lastElement);
            indexOfElements.put(lastElement, index);
            elements.remove(lastIndex);
            indexOfElements.remove(element);

        }
    }

    /**
     * Метод проверки, если есть элемент в справочнике
     *
     * @param element элемент для провекрки
     * @return тру если есть элемент
     */
    @Override
    public boolean consist(Object element) {
        return indexOfElements.containsKey(element);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DictionaryImpl{");
        sb.append("random=").append(random);
        sb.append(", elements=").append(elements);
        sb.append(", indexOfElements=").append(indexOfElements);
        sb.append('}');
        return sb.toString();
    }
}

