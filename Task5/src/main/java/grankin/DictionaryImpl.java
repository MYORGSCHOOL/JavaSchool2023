package grankin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Реализация словаря
 * @param <G>   - хранимый элемент
 */
public class DictionaryImpl<G> implements Dictionary<G> {

    /**
     * Список на основе массива для хранения элементов словаря
     */
    private final ArrayList<G> arrayList = new ArrayList<>();

    /**
     * Структура данных на основе получения хеша объекта для хранения индексов элемендов
     * соответсвуюхих индексам в списке
     */
    private final HashMap<G, Integer> hashMap = new HashMap<>();

    /**
     * Конструктор по-умолчанию
     */
    public DictionaryImpl() {

    }

    /**
     * Вставка элемента в словарь
     * @param element   - вставляемый элемент
     */
    @Override
    public void insert(G element) {
        arrayList.add(element);
        hashMap.put(element, arrayList.size() - 1);
    }

    /**
     * Получить случайное значение из словаря
     * @return      - случайный элемент словаря
     */
    @Override
    public G getRandom() {
        if (arrayList.size() > 0) {
            int index = (int) (Math.random() * arrayList.size());
            return arrayList.get(index);
        }
        return null;
    }

    /**
     * Удалить элемент из словаря по значению
     * @param element   - удаляемый элемент
     */
    @Override
    public void delete(G element) {
        if (arrayList.size() > 0) {
            int index = hashMap.get(element);
            if (index < arrayList.size() - 1) {
                G copyElement = arrayList.get(index);
                arrayList.set(index, arrayList.get(arrayList.size() - 1));
                arrayList.set(arrayList.size() - 1, copyElement);
            }
            arrayList.remove(arrayList.size() - 1);
            hashMap.remove(element);
        }
    }

    /**
     * Проверить, содержится ли элемент в словаре
     * @param element   - элемент
     * @return          - результат проверки
     */
    @Override
    public boolean consist(G element) {
        return hashMap.get(element) != null;
    }

    /**
     * Nнформация о внутреннем состоянии списка и хеш карты (вспомогательный метод)
     */
    public void displayInfo() {
        System.out.println("ArrayList size " + arrayList.size());
        System.out.println("HashMap size " + hashMap.size());

        System.out.println("ArrayList");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("index " + i + " element " + arrayList.get(i));
        }

        System.out.println("HashMap");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("key " + arrayList.get(i) + " element(index) " + hashMap.get(arrayList.get(i)));
        }
    }

    /**
     * Текущий размер словаря (вспомогательный метод)
     * @return  - текущая заполненность
     */
    public int sizeDictionary() {
        return arrayList.size();
    }
}
