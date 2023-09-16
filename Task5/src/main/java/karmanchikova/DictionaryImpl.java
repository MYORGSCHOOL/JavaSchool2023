package karmanchikova;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Класс реализующий интерфейс Dictionary
 *
 * @param <G> тип данных, хранимых в словаре
 */
public class DictionaryImpl<G> implements Dictionary<G> {
    private final ArrayList<G> arrayList;
    private final HashMap<G, Integer> hashMap;

    public DictionaryImpl() {
        this.arrayList = new ArrayList<>();
        this.hashMap = new HashMap<>();
    }

    /**
     * Метод позволяет вставить элемент в конец списка ArrayList и HashMap.
     * Если вводимый элемент уже существует выводится сообщение об ошибке.
     *
     * @param element элемент, который добавляется в словарь.
     * @throws DictionaryException если элемент уже существует в справочнике
     */
    @Override
    public void insert(G element) {
        if (consist(element)) {
            throw new DictionaryException("Данный элемент уже существует в справочнике");
        }
        arrayList.add(element);
        hashMap.put(element, arrayList.size() - 1);
    }

    /**
     * Метод возвращяет случайный элмент из словаря
     *
     * @return случайный элмент из словаря
     */
    @Override
    public G getRandom() {
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.get((int) (Math.random() * arrayList.size()));
    }

    /**
     * Метод для удаления элемента из словаря
     * Если передаваемый элемент есть в словаре, то данный элемент
     * меняется местами с посленим элементом.
     * После чего происходит удаление послений ячейки из словаря
     * Если элемент не был найдет выводится сообщение об ошибке
     *
     * @param element элемент который необходимо удалить
     * @throws DictionaryException выводится, если элемент не был найден
     */
    @Override
    public void delete(G element) {
        if (!consist(element)) {
            throw new DictionaryException("Данный элемент отсутствует в справочнике");
        }
        int index = hashMap.remove(element);
        if (index == arrayList.size() - 1) {
            arrayList.remove(index);
        } else {
            swap(arrayList, index);
            hashMap.put(arrayList.get(index), index);
            arrayList.remove(arrayList.size() - 1);
        }
    }

    /**
     * Метод проверяет наличие передаваемого элемента в словаре.
     * При нахождении возвращет true, иначе false.
     *
     * @param element элемент, наличие которого проверяется
     * @return true/false в зависимости от результата поиска
     */
    @Override
    public boolean consist(G element) {
        return hashMap.containsKey(element);
    }

    /**
     * Метод для замены передаваемого элемента с последним
     *
     * @param array массив в котором необходимо переставить элементы
     * @param index индекс элемента, который необходимо переставить
     */
    private void swap(ArrayList<G> array, int index) {
        Collections.swap(array, index, array.size() - 1);
    }

    /**
     * Метод для вывода иформации о словаре
     *
     * @return информация о словаре
     */
    @Override
    public String toString() {
        return "DictionaryImpl{" +
                "arrayList=" + arrayList +
                ", hashMap=" + hashMap +
                '}';
    }
}