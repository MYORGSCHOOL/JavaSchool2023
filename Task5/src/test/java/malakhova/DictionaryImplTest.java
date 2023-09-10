package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {
    private static final long TIME_TEST = 1000;

    @Test
    @DisplayName("Проверка добавления элемента в словарь")
    public void testSuccessInsertToDictionaryImpl() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("one");
        Object testDictionary = "[one]";
        Assertions.assertEquals(dictionary.toString(), testDictionary);
    }

    @Test
    @DisplayName("Проверка получения случайного элемента словаря")
    public void testSuccessGetRandomFromDictionaryImpl() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.getRandom());
        dictionary.insert("two");
        Object testDictionary = "[two]";
        Assertions.assertEquals(dictionary.toString(), testDictionary);
    }

    @Test
    @DisplayName("Проверка получения случайного элемента словаря")
    public void testSuccessConsistInDictionaryImpl() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("two");
        Assertions.assertTrue(dictionary.consist("two"));
        Assertions.assertFalse(dictionary.consist("one"));
    }

    @Test
    @DisplayName("Проверка удаления элемента из словаря")
    public void testSuccessDeleteFromDictionaryImpl() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.delete("one"));
        dictionary.insert("one");
        dictionary.insert("two");
        dictionary.delete("one");
        Object testDictionary = "[two]";
        Assertions.assertEquals(dictionary.toString(), testDictionary);
    }

    @Test
    @DisplayName("Проверка времени выполнения добавления элемента в словарь")
    public void testTimeInsertToDictionaryImpl() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.insert(i);
        }
        Assertions.assertTrue(TIME_TEST > System.currentTimeMillis() - start1);
    }

    @Test
    @DisplayName("Проверка времени выполнения удаления элемента из словаря")
    public void testTimeDeleteFromDictionaryImpl() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.insert(i);
        }
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.delete(i);
        }
        Assertions.assertTrue(TIME_TEST > System.currentTimeMillis() - start1);
    }

    @Test
    @DisplayName("Проверка времени выполнения получения случайного элемента из словаря")
    public void testTimeGetRandomFromDictionaryImpl() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.insert(i);
        }
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.getRandom();
        }
        Assertions.assertTrue(TIME_TEST > System.currentTimeMillis() - start1);
    }

    @Test
    @DisplayName("Проверка времени выполнения метода, проверяющего наличие элемента в словаре")
    public void testTimeConsistInDictionaryImpl() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.insert(i);
        }
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1_500_000; i++) {
            dictionary.consist(i);
        }
        Assertions.assertTrue(TIME_TEST > System.currentTimeMillis() - start1);
    }
}
