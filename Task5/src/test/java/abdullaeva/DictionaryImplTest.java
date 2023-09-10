package abdullaeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class DictionaryImplTest {

    /**
     * Допустимое время выполнения тестов - 1 секунда
     */
    private static final long EXECUTION_TIME = 1000;
    /**
     * Количество элементов в Словаре для выполнения тестов на время
     */
    private static final long TEST_ELEMENTS_BOUND = 1_000_000;

    @Test
    @DisplayName("Тест для проверки скорости работы метода вставки элемента в Словарь")
    @Timeout(value = EXECUTION_TIME, unit = TimeUnit.MILLISECONDS)
    public void testInsertInDictionaryForSecond() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < TEST_ELEMENTS_BOUND; i++) {
            dictionary.insert(String.valueOf(i));
        }
    }

    @Test
    @DisplayName("Тест для проверки метода вставки элемента в Словарь")
    public void testInsertInDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("test");
        Assertions.assertTrue(dictionary.consist("test"));
    }

    @Test
    @DisplayName("Тест для проверки метода вставки при добавлении одинаковых элементов в Словарь")
    public void testInsertSameElementsInDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("test");
        Assertions.assertThrows(KeyAlreadyExistsException.class, () -> dictionary.insert("test"));
    }

    @Test
    @DisplayName("Тест для проверки скорости работы метода удаления элемента из Словаря")
    @Timeout(value = 2 * EXECUTION_TIME, unit = TimeUnit.MILLISECONDS)
    public void testDeleteFromDictionaryForSecond() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < TEST_ELEMENTS_BOUND; i++) {
            dictionary.insert(String.valueOf(i));
        }
        for (int i = 0; i < TEST_ELEMENTS_BOUND; i++) {
            dictionary.delete(String.valueOf(i));
        }
    }

    @Test
    @DisplayName("Тест для проверки метода удаления элемента из Словаря")
    public void testDeleteFromDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("a");
        dictionary.delete("a");
        Assertions.assertFalse(dictionary.consist("a"));
    }

    @Test
    @DisplayName("Тест для проверки метода удаления при удалении не содержащегося в Словаре элемента")
    public void testDeleteNotConsistsElementFromDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("a");
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.delete("test"));
    }

    @Test
    @DisplayName("Тест для проверки скорости работы метода поиска элемента в Словаре")
    @Timeout(value = 2 * EXECUTION_TIME, unit = TimeUnit.MILLISECONDS)
    public void testConsistInDictionaryForSecond() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < TEST_ELEMENTS_BOUND; i++) {
            dictionary.insert(String.valueOf(i));
        }
        for (int i = 0; i < TEST_ELEMENTS_BOUND; i++) {
            dictionary.consist(String.valueOf(i));
        }
    }

    @Test
    @DisplayName("Тест для проверки метода поиска элемента в Словаре")
    public void testConsistInDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("a");
        dictionary.insert("b");
        Assertions.assertFalse(dictionary.consist("c"));
        Assertions.assertTrue(dictionary.consist("a"));
    }

    @Test
    @DisplayName("Тест для проверки скорости работы метода получения случайного элемента из Словаря")
    @Timeout(value = EXECUTION_TIME, unit = TimeUnit.MILLISECONDS)
    public void testGetRandomFromDictionaryForSecond() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 100; i++) {
            dictionary.insert(String.valueOf(i));
        }
        for (int i = 0; i < TEST_ELEMENTS_BOUND; i++) {
            dictionary.getRandom();
        }
    }

    @Test
    @DisplayName("Тест для проверки метода получения случайного элемента из Словаря")
    public void testGetRandomFromDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("a");
        dictionary.insert("b");
        dictionary.insert("c");
        Assertions.assertTrue(dictionary.consist(dictionary.getRandom()));
    }

    @Test
    @DisplayName("Тест для проверки метода получения случайного элемента при пустом Словаре")
    public void testGetRandomFromEmptyDictionary() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(RuntimeException.class, dictionary::getRandom);
    }

}