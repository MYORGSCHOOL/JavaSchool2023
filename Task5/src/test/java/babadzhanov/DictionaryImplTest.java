package babadzhanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;


class DictionaryImplTest {

    private static final int ITERATION_COUNT = 5_000_000;

    private static final int MAX_TIME = 100;

    @Test
    @DisplayName("Проверка успешного добавления элемента в справочник")
    void testSuccessInsertToDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Test");
        dictionary.insert("Me");
        Assertions.assertTrue(dictionary.contains("Test"));
        Assertions.assertTrue(dictionary.contains("Me"));
    }

    @Test
    @DisplayName("Проверка вставки дубля в справочник")
    void checkExceptionForRecurringInsert() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Test");
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.insert("Test"));
    }

    @Test
    @DisplayName("Проверка времени выполнения вставки в справочник")
    void testTimeForInsertToDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.insert(i);
        }
        long startTime = System.currentTimeMillis();
        dictionary.insert(ITERATION_COUNT);
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка удаления элемента из справочника")
    void testSuccessDeletingElementFromDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Test");
        dictionary.insert("Me");
        dictionary.insert("More");
        dictionary.delete("Me");
        Assertions.assertFalse(dictionary.contains("Me"));
    }

    @Test
    @DisplayName("Проверка удаления несуществующего элемента из справочника")
    void checkExceptionForDeletingNotFoundElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Test");
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.delete("Me"));
    }

    @Test
    @DisplayName("Проверка времени удаления из справочника")
    public void testTimeForDeletingFromDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.insert(i);
        }
        int random = dictionary.getRandom();
        long startTime = System.currentTimeMillis();
        dictionary.delete(random);
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка получения рандомного элемента из справочника")
    void testSuccessGetRandomFromDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Maybe");
        dictionary.insert("Enough");
        Assertions.assertTrue(dictionary.contains(dictionary.getRandom()));
    }

    @Test
    @DisplayName("Проверка времени получения рандомного элемента из справочника")
    public void testTimeForGetRandomFromDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.insert(i);
        }
        long startTime = System.currentTimeMillis();
        dictionary.getRandom();
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Поверка времени, на поиск наличия элемента в справочнике")
    public void testTimeForFindContainsElementInDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            dictionary.insert(i);
        }
        long startTime = System.currentTimeMillis();
        dictionary.contains(99);
        Assertions.assertTrue(System.currentTimeMillis() - startTime < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка, что справочник содержит элемент")
    void testSuccessContainsElementInDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Please, stop");
        Assertions.assertTrue(dictionary.contains("Please, stop"));
    }
}