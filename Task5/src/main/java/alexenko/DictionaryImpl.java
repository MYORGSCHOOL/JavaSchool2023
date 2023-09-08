package alexenko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс реализует интерфейс словаря.
 * Все методы работают за константное время.
 *
 * @param <K> обобщённый тип ключа в dictionaryOfElements
 */
public class DictionaryImpl<K> implements Dictionary<K> {

    /**
     * dictionaryOfElements хранит элементы и их индексы в массиве.
     */
    private final HashMap<K, Integer> dictionaryOfElements;

    /**
     * arrayOfElements хранит элементы.
     */
    private final ArrayList<K> arrayOfElements;

    /**
     * Конструктор инициализирует поля.
     */
    public DictionaryImpl() {
        this.dictionaryOfElements = new HashMap<>();
        this.arrayOfElements = new ArrayList<>();
    }

    /**
     * Метод делает вставку элемента в dictionaryOfElements и arrayOfElements.
     *
     * @param element вставляемый элемент
     */
    @Override
    public void insert(K element) {
        if (dictionaryOfElements.containsKey(element)) {
            return;
        }
        arrayOfElements.add(element);
        dictionaryOfElements.put(element, arrayOfElements.size() - 1);
    }

    /**
     * Метод удаляет элемент из dictionaryOfElements и arrayOfElements.
     * Сначала идёт проверка того, что элемент есть в dictionaryOfElements.
     * Переменная index хранит индекс удаляемого элемента.
     * Происходит перестановка крайнего элемента на место удаляемого.
     * Получение крайнего элемента.
     * Замена индекса крайнего элемента в dictionaryOfElements.
     * Удаление элемента из dictionaryOfElements и arrayOfElements.
     *
     * @param element удаляемый элемент
     */
    @Override
    public void delete(K element) {
        if (!consist(element)) {
            throw new IllegalArgumentException("Попытка удалить элемент, которого нет в словаре.");
        }
        int index = dictionaryOfElements.get(element);
        setPositionEndElement(arrayOfElements, index);
        K swappedElement = arrayOfElements.get(index);
        dictionaryOfElements.put(swappedElement, index);
        arrayOfElements.remove(arrayOfElements.size() - 1);
        dictionaryOfElements.remove(element);
    }

    /**
     * Метод переставляет крайний элемент на место удаляемого.
     *
     * @param list  список, в котором переставляется элемент
     * @param index индекс места, куда надо установить крайний элемент
     * @param <K>   обобщённый тип ключа в dictionaryOfElements
     */
    private static <K> void setPositionEndElement(ArrayList<K> list, int index) {
        K endElement = list.get(list.size() - 1);
        list.set(index, endElement);
    }

    /**
     * Метод осуществляет поиск элемента в dictionaryOfElements.
     *
     * @param element искомый элемент
     * @return true - элемент найден, false - элемент не найден
     */
    @Override
    public boolean consist(K element) {
        return dictionaryOfElements.containsKey(element);
    }

    /**
     * Метод возвращает случайный элемент из arrayOfElements.
     *
     * @return случайный элемент
     */
    @Override
    public K getRandom() {
        arrayOfElements.trimToSize();
        int index = ThreadLocalRandom.current().nextInt(0, arrayOfElements.size());
        return arrayOfElements.get(index);
    }

    /**
     * Строковое представление класса DictionaryImpl.
     *
     * @return экземпляр класса DictionaryImpl в виде строки
     */
    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "dictionaryOfElements=" + dictionaryOfElements +
                ", arrayOfElements=" + arrayOfElements +
                '}';
    }
}
