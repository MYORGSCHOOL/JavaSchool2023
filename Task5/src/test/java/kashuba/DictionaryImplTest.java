package kashuba;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {
    private static final int ITERATION_COUNT = 1_000_000;
    private static final int MAX_TIME = 1000;

    @Test
    @DisplayName("Проверка вставки элемента")
    public void testCheckingForThePresenceOfElementIfIsInDirectory() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("123");
        Assertions.assertTrue(dictionary.consist("123"));
    }

    @Test
    @DisplayName("Проверка вставки элемента, который есть")
    public void testCheckingForThePresenceOfElementIfIsNotInDirectory() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("123");
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.insert("123"));
    }

    @Test
    @DisplayName("Проверка времени метода insert")
    public void testCheckingTheInsertionTime() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.insert(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка получения случайного элемента")
    public void testCheckingTheReceiptOfARandomElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(1);
        dictionary.insert(2);
        dictionary.insert(3);
        int element = dictionary.getRandom();
        Assertions.assertTrue(dictionary.consist(element));
    }

    @Test
    @DisplayName("Проверка получения случайного элемента из пустого справочника")
    public void testCheckingTheReceiptOfARandomElementFromEmptyDirectory() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.getRandom());
    }

    @Test
    @DisplayName("Проверка времени метода getRandom")
    public void testCheckingTheTimeOfTheGetRandom() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        fillingDictionary(dictionary);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.getRandom();
        }
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка удаления элемента")
    public void testCheckingTheDeletionOfAnElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(123);
        dictionary.insert(1234);
        dictionary.delete(123);
        Assertions.assertFalse(dictionary.consist(123));
    }

    @Test
    @DisplayName("Проверка удаления элемента, которого нет")
    public void testCheckingTheDeletionOfAnElementThatDoesNotExist() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.delete(123));
    }

    @Test
    @DisplayName("Проверка времени метода delete")
    public void testCheckingTheTimeOfTheDelete() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        fillingDictionary(dictionary);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.getRandom();
        }
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка наличия элемента")
    public void testCheckingThePresenceOfAnElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        Assertions.assertFalse(dictionary.consist(123));
    }

    @Test
    @DisplayName("Проверка времени метода consist")
    public void testCheckingTheTimeOfTheConsist() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        fillingDictionary(dictionary);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.consist(i);
        }
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    /**
     * Вспомогательная функция для проверки методов
     *
     * @param dictionary массив элементов
     */
    private void fillingDictionary(DictionaryImpl dictionary) {
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.insert(i);
        }
    }
}
