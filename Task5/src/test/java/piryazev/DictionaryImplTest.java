package piryazev;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Assertions;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class DictionaryImplTest {
    /**
     * Максимальное время для исполнения теста
     */
    public static final long EXECUTABLE_TIME = 500;
    /**
     * Число элементов для вставки
     */
    public static final long TEST_MILLION_ELEMENTS = 1_000_000;
    /**
     * Словарь для вставки элементов BeforeAll.
     * Не используется в тестах, где нужно проверить методы с пустым словарем
     */
    static DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

    @Disabled
    @DisplayName("check insert with timeout")
    @BeforeAll
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public static void testInsertElementsInHalfSecond() {
        for (int i = 0; i < TEST_MILLION_ELEMENTS; i++) {
            dictionary.insert(i);
        }
    }

    @Test
    @Disabled
    @DisplayName("check insert same element in dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testInsertSameElement() {
        Assertions.assertThrows(KeyAlreadyExistsException.class, () -> dictionary.insert(1));
    }

    @Test
    @Disabled
    @DisplayName("check delete element with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testDeleteElementsInHalfSecond() {
        dictionary.delete(5);
        dictionary.delete(100000);
        dictionary.delete(777);
    }

    @Test
    @Disabled
    @DisplayName("check delete element in empty dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testDeleteElementInEmptyDictionary() {
        DictionaryImpl<Integer> dictionaryLocal = new DictionaryImpl<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionaryLocal.delete(1));
    }

    @Test
    @Disabled
    @DisplayName("check get random element with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testGetRandomElementInHalfSecond() {
        dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();
        Assertions.assertEquals(dictionary.getArray().get(10200), 10200);
    }

    @Test
    @Disabled
    @DisplayName("trying to get random element in empty dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testGetRandomElementInEmptyDictionary() {
        DictionaryImpl<Integer> dictionaryLocal = new DictionaryImpl<>();
        Assertions.assertThrows(NoSuchElementException.class, dictionaryLocal::getRandom);
    }

    @Test
    @Disabled
    @DisplayName("check consist element with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testConsistElementInHalfSecond() {
        Assertions.assertTrue(dictionary.consist(99_999));
        Assertions.assertFalse(dictionary.consist(1_000_000));
    }

    @Test
    @Disabled
    @DisplayName("check is consist element in empty dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testConsistElementInEmptyDictionary() {
        DictionaryImpl<Integer> dictionaryLocal = new DictionaryImpl<>();
        Assertions.assertFalse(dictionaryLocal.consist(1));
    }
}
