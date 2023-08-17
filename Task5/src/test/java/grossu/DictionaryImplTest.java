package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Класс для тестирования метолов класса DictionaryImpl
 */
public class DictionaryImplTest {
    /**
     * Емкость для тестирования времени выполнения метод
     */
    private final int INITIAL_CAPACITY = 1_000_000;

    @Test
    @DisplayName("checking for successful insertion of an element into a dictionary")
    void testSuccessInsertElementIntoDictionary() {
        DictionaryImpl<String> actual = new DictionaryImpl<>();
        Assertions.assertDoesNotThrow(() -> actual.insert("1"));
        Assertions.assertTrue(actual.consist("1"));

    }

    @Test
    @DisplayName("Checking for get exception when inserting two identical elements")
    void testGetThrowWhenInsertTwoIdenticalElements() {
        DictionaryImpl<String> actual = new DictionaryImpl<>();
        String element = "1";
        actual.insert(element);
        Assertions.assertThrows(DictionaryException.class, () -> actual.insert(element));
    }

    @Test
    @DisplayName("Checking that the function - insert works in O(1)")
    void testInsertRunningTimeInConstant() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>(INITIAL_CAPACITY);
        long startTime = System.nanoTime();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.insert(i);
        }
        long estimatedTime = System.nanoTime() - startTime;
        Assertions.assertTrue(1 > TimeUnit.SECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS));
    }

    @Test
    @DisplayName("Checking for successful removed element from dictionary")
    void testSuccessDeleteElementFromDictionary() {
        DictionaryImpl<String> actual = new DictionaryImpl<>();
        actual.insert("1");
        Assertions.assertDoesNotThrow(() -> actual.delete("1"));
    }

    @Test
    @DisplayName("Checking for get exception when deleting non-existent elements in dictionary")
    void testGetThrowWhenTryDeleteNonExistentElementInDictionary() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, () -> actual.delete(1));
        Assertions.assertFalse(actual.consist(1));
    }

    @Test
    @DisplayName("Checking that the function - delete works in O(1)")
    void testDeleteRunningTimeInConstant() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.insert(i);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.delete(i);
        }
        long estimatedTime = System.nanoTime() - startTime;
        Assertions.assertTrue(1 > TimeUnit.SECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS));
    }

    @Test
    @DisplayName("Checking that the function - consist works correctly when element exist in dictionary")
    void testGetTrueWhenElementExistInDictionary() {
        DictionaryImpl<String> actual = new DictionaryImpl<>();
        actual.insert("1");
        Assertions.assertTrue(actual.consist("1"));
    }

    @Test
    @DisplayName("Checking that the function - consist works correctly when element non-existent in dictionary")
    void testGetFalseWhenElementNonExistInDictionary() {
        DictionaryImpl<String> actual = new DictionaryImpl<>();
        Assertions.assertFalse(actual.consist("1"));
    }

    @Test
    @DisplayName("Checking that the function - consist works in O(1)")
    void testConsistRunningTimeInConstant() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.insert(i);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.consist(i);
        }
        long estimatedTime = System.nanoTime() - startTime;
        Assertions.assertTrue(1 > TimeUnit.SECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS));
    }

    @Test
    @DisplayName("Check of getting a random element from a dictionary")
    void testGetRandomElementFromDictionary() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>();
        actual.insert(1);
        actual.insert(2);
        actual.insert(3);
        Assertions.assertTrue(actual.consist(actual.getRandom()));
    }

    @Test
    @DisplayName("Checking for get exception when dictionary is empty")
    void testGetThrowWhenTryGetRandomAndDictionaryIsEmpty() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, actual::getRandom);
    }

    @Test
    @DisplayName("Checking that the function - getRandom works in O(1)")
    void testGetRandomRunningTimeInConstant() {
        DictionaryImpl<Integer> actual = new DictionaryImpl<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.insert(i);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            actual.getRandom();
        }
        long estimatedTime = System.nanoTime() - startTime;
        Assertions.assertTrue(1 > TimeUnit.SECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS));
    }
}
