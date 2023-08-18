package yanko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * реализация интерфейса словаря
 *
 * @param <G> тип объектов, хранящихся в словаре
 */
public class DictionaryImpl <G> implements Dictionary <G> {
    /**
     * мапа,в котрой элементы словаря являются ключами, а индексы элементов в массиве - значениями ключей
     */
    private final HashMap<G, Integer> hashMap;
    /**
     * массив, в котором хранятся элементы словаря
     */
    private final List<G> list;

    public DictionaryImpl() {
        this.hashMap = new HashMap<>();
        this.list = new ArrayList<>();
    }

    /**
     * вставка элемента в словарь
     *
     * @param e элемент
     */
    @Override
    public void insert(G e) {
        if(!this.list.isEmpty() && !this.hashMap.isEmpty()) {
            if(consist(e)) {
                throw new ArrayStoreException("cannot insert element! element is already in dictionary");
            }
        }
        this.list.add(e);
        this.hashMap.put(e, list.size() - 1);
    }

    /**
     * получение случайного элемента из словаря
     */
    @Override
    public G getRandom() {
        if (this.list.isEmpty() || this.hashMap.isEmpty()) {
            throw new ArrayStoreException("cannot get random element! dictionary is empty");
        }
        return this.list.get((int) (Math.random() * this.list.size()));
    }

    /**
     * удаление элемента из словаря
     *
     * @param e элемент
     */
    @Override
    public void delete(G e) {
        if (this.list.isEmpty() || this.hashMap.isEmpty()) {
            throw new ArrayStoreException("cannot remove element! dictionary is empty");
        }
        if(!this.consist(e)) {
            throw new ArrayStoreException("cannot remove element! no such element in dictionary");
        }
        int index = this.hashMap.get(e);
        swap(index);
        this.hashMap.remove(this.list.get(this.list.size() - 1));
        this.list.remove(this.list.get(this.list.size() - 1));
    }

    /**
     * проверка на то, что элемент содержится в словаре
     *
     * @param e элемент
     */
    @Override
    public boolean consist(G e) {
        if (this.list.isEmpty() || this.hashMap.isEmpty()) {
            throw new ArrayStoreException("cannot check element! dictionary is empty");
        }
        return this.hashMap.containsKey(e);
    }

    /**
     * перестановка двух элементов в словаре
     * (элемент с конца вставляется на место элемента с заданным индексом,
     * элемент с заданным индексом вставляется в конец)
     *
     * @param index индекс элемента
     */
    private void swap(int index) {
        G element1 = this.list.get(index);
        G element2 = this.list.get(this.list.size() - 1);
        swapInList(element1, element2, index);
        swapInMap(element1, element2, index);
    }

    /**
     * перестановка двух элементов в списке
     *
     * @param index индекс элемента
     */
    private void swapInList(G element1, G element2, int index) {
        this.list.set(this.list.size() - 1, element1);
        this.list.set(index, element2);
    }

    /**
     * перестановка двух элементов в мапе
     *
     * @param index индекс элемента
     */
    private void swapInMap(G element1, G element2, int index) {
        this.hashMap.put(element1, this.list.size() - 1);
        this.hashMap.put(element2, index);
    }


    /**
     * преобразование словаря к строке для вывода
     *
     * @return строка
     */
    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "hashMap=" + this.hashMap +
                ",\nlist=" + this.list +
                '}';
    }
}
