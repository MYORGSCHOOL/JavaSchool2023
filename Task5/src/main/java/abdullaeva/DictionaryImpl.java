package abdullaeva;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Класс реализации интерфейса Словарь
 */
public class DictionaryImpl<E> implements Dictionary<E> {
    /**
     * Hashmap, выступающая в роли Словаря, который хранит уникальные элементы и их индексы
     */
    private final HashMap<E, Integer> hashmap;

    /**
     * Лист, выступающий в роли массива, в котором хранятся элементы словаря
     * array необходим для получения случайного элемента словаря
     */
    private final ArrayList<E> array;

    public DictionaryImpl() {
        this.hashmap = new HashMap<>();
        this.array = new ArrayList<>();
    }

    /**
     * Метод для вставки элемента в конец Словаря за O(1).
     * Сначала происходит проверка на существование уникального элемента в Словаре.
     * Если элемент уже есть в Словаре, выкидывается исключение и сообщение об ошибке вставки.
     * Затем вставляем элемент в массив элементов Словаря. После кладём
     * тот же элемент и индекс этого элемента в массиве элементов в Словарь.
     *
     * @param element - элемент, который необходимо вставить.
     */
    @Override
    public void insert(E element) {
        if (consist(element)) {
            throw new KeyAlreadyExistsException("Error: This element is already consist in the Dictionary");
        }
        this.array.add(element);
        this.hashmap.put(element, array.size() - 1);
    }

    /**
     * Метод для удаления элемента из Словаря за O(1).
     * Сначала происходит проверка на существование уникального элемента в Словаре.
     * Если элемента еще нет в Словаре, выкидывается исключение и сообщение об ошибке удаления.
     * Затем из Словаря удаляется элемент и сохраняется его индекс в массиве элементов Словаря.
     * Если индекс не совпадает с индексом конца массива Словаря, то выполняется метод swap,
     * который меняет последний элемент с элементом, имеющим текущий индекс.
     * Если словарь не пустой после удаления элемента, в Словарь перезаписывается
     * последний элемент массива Словаря с обновлённым индексом.
     * После происходит удаление нужного элемента, уже перемещённого в конец, в массиве Словаря.
     *
     * @param element - элемент, который необходимо удалить.
     */
    @Override
    public void delete(E element) {
        if (!consist(element)) {
            throw new NoSuchElementException("Error: This element isn't in the Dictionary");
        }
        int index = this.hashmap.remove(element);
        if (index != array.size() - 1) {
            swap(array, index);
        }
        if (!hashmap.isEmpty()) {
            this.hashmap.put(array.get(index), index);
        }
        this.array.remove(array.size() - 1);
    }

    /**
     * Метод для проверки существования элемента в Словаре за O(1).
     * С помощью метода containsKey проверяем существование уникального элемента в Словаре.
     *
     * @param element - элемент, который необходимо найти в Словаре.
     * @return - true, если элемент содержится в Словаре, и false, если элемента в Словаре нет.
     */
    @Override
    public boolean consist(E element) {
        return this.hashmap.containsKey(element);
    }

    /**
     * Метод для получения случайного элемента из Словаря за O(1).
     * Сначала проходит проверка на пустоту Словаря.
     * Если словарь пустой, выкидывается исключение и сообщение об ошибке
     * получения рандомного элемента.
     * С помощью метода nextInt класса Random вычисляется случайный индекс элемента
     * Словаря. По этому индексу с помощью метода get из массива Словаря достается
     * случайный элемент.
     *
     * @return - случайный элемент из Словаря.
     */
    @Override
    public E getRandom() {
        if (this.hashmap.isEmpty()) {
            throw new RuntimeException("Error: Dictionary is empty. Couldn't get a random element");
        }
        return this.array.get(new Random().nextInt(array.size()));
    }

    /**
     * Метод, с помощью которого меняется последний элемент массива Словаря с элементом, имеющим текущий индекс.
     * Используется для реализации удаления элементов из Словаря без образования "пустот" в структуре.
     * Применяется также, чтобы корректно реализовать работу метода getRandom.
     * Вызывается метод swap класса Collection, который меняет местами элементы в коллекции,
     * стоящие на указанных в параметрах метода индексах.
     *
     * @param array - массив элементов Словаря.
     * @param index - индекс элемента, который необходимо поменять местами с последним элементом.
     */
    private void swap(ArrayList<E> array, int index) {
        Collections.swap(array, index, array.size() - 1);
    }

    @Override
    public String toString() {
        return "Словарь: " + hashmap + " и массив Словаря: " + array;
    }
}
