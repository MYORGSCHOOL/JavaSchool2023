package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;

/**
 * Класс тестирования реализации справочника
 */

public class DictionaryImplTest {
    /**
     * Допустимое время выполнение методов
     */
    private static final long ALLOWABLE_TIME = 1000;
    /**
     * Количество итераций методов
     */
    private static final long ITERATION = 1_000_000;
    /**
     * время начала выполнения методов
     */
    private long startExecution;
    /**
     * Первая строка для добавления в справочник
     */
    private static final String FIRST_STRING = "hello";
    /**
     * Вторая строка для добавления в справочник
     */
    private static final String SECOND_STRING = "world";

    @Test
    @DisplayName("Тест проверки наличия элемента в справочнике - элемент есть в справочнике")
    public void testConsistElementInDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert(FIRST_STRING);
        Assertions.assertTrue(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест проверки наличия элемента в справочнике - элемента нет в справочнике")
    public void testConsistElementNotInDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertFalse(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на успешное добавление элементов в справочник")
    public void testSuccessInsertElements() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert(FIRST_STRING);
        dictionary.insert(SECOND_STRING);
        Assertions.assertTrue(dictionary.consist(SECOND_STRING));
        Assertions.assertTrue(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на неудачное добавление повторяющегося элемента в справочник")
    public void testFailInsertRepeatElements() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert(FIRST_STRING);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.insert(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на успешное удаление элемента из справочника")
    public void testSuccessDeleteElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert(FIRST_STRING);
        dictionary.delete(FIRST_STRING);
        Assertions.assertFalse(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления - справочник пустой")
    public void testFailDeleteElementDictionaryIsEmpty() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.delete(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления - элемента нет в справочнике")
    public void testFailDeleteElementNotInDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert(SECOND_STRING);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.delete(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на успешное получение случайного элемента")
    public void testSuccessGetRandomElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert(FIRST_STRING);
        dictionary.insert(SECOND_STRING);
        Assertions.assertTrue(dictionary.consist(dictionary.getRandom()));
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения случайного элемента - справочник пустой")
    public void testFailGetRandomElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.getRandom());
    }

    /**
     * Метод для вставки в справочник элементов от 0 до ITERATION
     *
     * @param dictionary справочник, в который происходит добавление
     */
    private void fillingDictionary(DictionaryImpl dictionary) {
        for (int i = 0; i != ITERATION; i++) {
            dictionary.insert(i);
        }
    }

    @Test
    @DisplayName("Тест на вставку элементов - проверка времени выполнения")
    public void testByTimeInsertElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        startExecution = System.currentTimeMillis();
        fillingDictionary(dictionary);
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }

    @Test
    @DisplayName("Тест проверки наличия элемента в справочнике - проверка времени выполнения")
    public void testByTimeConsistElementsInDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        fillingDictionary(dictionary);
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.consist(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }

    @Test
    @DisplayName("Тест на получение случайных элементов - проверка времени выполнения")
    public void testByTimeGetRandomElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        fillingDictionary(dictionary);
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.getRandom();
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }

    @Test
    @DisplayName("Тест на удаление элементов - проверка времени выполнения")
    public void testByTimeDeleteElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        fillingDictionary(dictionary);
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.delete(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }
}