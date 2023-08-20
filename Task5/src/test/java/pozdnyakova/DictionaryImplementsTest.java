package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;

/**
 * Класс тестирования реализации справочника
 */

public class DictionaryImplementsTest {
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
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        dictionary.insert(FIRST_STRING);
        Assertions.assertTrue(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест проверки наличия элемента в справочнике - элемента нет в справочнике")
    public void testConsistElementNotInDictionary() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        Assertions.assertFalse(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на успешное добавление элементов в справочник")
    public void testSuccessInsertElements() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        dictionary.insert(FIRST_STRING);
        dictionary.insert(SECOND_STRING);
        Assertions.assertTrue(dictionary.consist(SECOND_STRING));
        Assertions.assertTrue(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на неудачное добавление повторяющегося элемента в справочник")
    public void testFailInsertRepeatElements() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        dictionary.insert(FIRST_STRING);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.insert(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на успешное удаление элемента из справочника")
    public void testSuccessDeleteElement() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        dictionary.insert(FIRST_STRING);
        dictionary.delete(FIRST_STRING);
        Assertions.assertFalse(dictionary.consist(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления - справочник пустой")
    public void testFailDeleteElementDictionaryIsEmpty() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.delete(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на неудачную попытку удаления - элемента нет в справочнике")
    public void testFailDeleteElementNotInDictionary() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        dictionary.insert(SECOND_STRING);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.delete(FIRST_STRING));
    }

    @Test
    @DisplayName("Тест на успешное получение случайного элемента")
    public void testSuccessGetRandomElement() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        dictionary.insert(FIRST_STRING);
        dictionary.insert(SECOND_STRING);
        Assertions.assertTrue(dictionary.consist(dictionary.getRandom()));
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения случайного элемента - справочник пустой")
    public void testFailGetRandomElement() {
        DictionaryImplements<String> dictionary = new DictionaryImplements<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.getRandom());
    }

    @Test
    @DisplayName("Тест на вставку элементов - проверка времени выполнения")
    public void testByTimeInsertElements() {
        DictionaryImplements<Integer> dictionary = new DictionaryImplements<>();
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.insert(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }

    @Test
    @DisplayName("Тест проверки наличия элемента в справочнике - проверка времени выполнения")
    public void testByTimeConsistElementsInDictionary() {
        DictionaryImplements<Integer> dictionary = new DictionaryImplements<>();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.insert(i);
        }
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.consist(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }

    @Test
    @DisplayName("Тест на получение случайных элементов - проверка времени выполнения")
    public void testByTimeGetRandomElements() {
        DictionaryImplements<Integer> dictionary = new DictionaryImplements<>();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.insert(i);
        }
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.getRandom();
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }

    @Test
    @DisplayName("Тест на удаление элементов - проверка времени выполнения")
    public void testByTimeDeleteElements() {
        DictionaryImplements<Integer> dictionary = new DictionaryImplements<>();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.insert(i);
        }
        startExecution = System.currentTimeMillis();
        for (int i = 0; i != ITERATION; i++) {
            dictionary.delete(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startExecution < ALLOWABLE_TIME);
    }
}