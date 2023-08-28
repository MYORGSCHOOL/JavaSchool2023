package piryazev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class DictionaryImplTest {
    /**
     * Максимальное время для исполнения теста
     */
    public static final long EXECUTABLE_TIME = 500;

    @Test
    @DisplayName("check insert with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testInsertElementsInHalfSecond() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }
    }

    @Test
    @DisplayName("check insert same element in dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testInsertSameElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }

        Assertions.assertThrows(KeyAlreadyExistsException.class, () -> dictionary.insert(1));
    }

    @Test
    @DisplayName("check delete element with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testDeleteElementsInHalfSecond() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }

        dictionary.delete(5);
        dictionary.delete(100000);
        dictionary.delete(777);
    }

    @Test
    @DisplayName("check delete element in empty dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testDeleteElementInEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.delete(1));
    }

    @Test
    @DisplayName("check get random element with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testGetRandomElementInHalfSecond() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }

        int prim = dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();
        dictionary.getRandom();

        Assertions.assertEquals(dictionary.getArray().get(10200), 10200);
    }

    @Test
    @DisplayName("trying to get random element in empty dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testGetRandomElementInEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        Assertions.assertThrows(NoSuchElementException.class, dictionary::getRandom);
    }

    @Test
    @DisplayName("check consist element with timeout")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testConsistElementInHalfSecond() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }

        Assertions.assertTrue(dictionary.consist(99_999));
        Assertions.assertFalse(dictionary.consist(1_000_000));
    }

    @Test
    @DisplayName("check is consist element in empty dictionary")
    @Timeout(value = EXECUTABLE_TIME, unit = TimeUnit.MILLISECONDS)
    public void testConsistElementInEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        Assertions.assertFalse(dictionary.consist(1));
    }
}
