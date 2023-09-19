package karmanchikova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {
    private static final int ITERATION = 1_000_000;
    private static final int ALLOWABLE_TIME = 1_000_000_000;

    @Test
    @DisplayName("вставка элемента в словарь")
    public void testSuccessInsertElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("12");
        Assertions.assertTrue(dictionary.consist("12"));
    }

    @Test
    @DisplayName("вставка дубликата в словарь")
    public void testExceptionInsertElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("12");
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.insert("12"));
    }

    @Test
    @DisplayName("время работы метода вставки")
    public void testTimeInsertElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        for (int i = 0; i < ITERATION; i++) {
            start = System.nanoTime();
            dictionary.insert(String.valueOf(i));
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(ALLOWABLE_TIME >= tempVal);
        }
    }

    @Test
    @DisplayName("удаление элемента из словаря")
    public void testSuccessDeleteElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("12");
        dictionary.delete("12");
        Assertions.assertFalse(dictionary.consist("12"));
    }

    @Test
    @DisplayName("удаление несуществующего элемента")
    public void testExceptionDeleteElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("12");
        dictionary.delete("12");
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.delete("12"));
    }

    @Test
    @DisplayName("время работы метода удаления")
    public void testTimeDeleteElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        fillingDictionary(dictionary);
        for (int i = 0; i < ITERATION; i++) {
            int element = dictionary.getRandom();
            start = System.nanoTime();
            dictionary.delete(element);
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(ALLOWABLE_TIME >= tempVal);
        }
    }

    @Test
    @DisplayName("получение случайного элемента")
    public void testSuccessGetRandomElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        dictionary.insert(21);
        dictionary.insert(122);
        int element = dictionary.getRandom();
        Assertions.assertTrue(dictionary.consist(element));
    }

    @Test
    @DisplayName("получение случайного элемента из пустого словаря")
    public void testExceptionGetRandomElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        Assertions.assertNull(dictionary.getRandom());
    }

    @Test
    @DisplayName("время работы метода рандома")
    public void testTimeGetRandomElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        fillingDictionary(dictionary);
        for (int i = 0; i < ITERATION; i++) {
            start = System.nanoTime();
            dictionary.getRandom();
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(ALLOWABLE_TIME >= tempVal);
        }
    }

    @Test
    @DisplayName("время работы метода поиска")
    public void testTimeConsistElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        fillingDictionary(dictionary);
        for (int i = 0; i < ITERATION; i++) {
            int element = dictionary.getRandom();
            start = System.nanoTime();
            dictionary.consist(element);
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(ALLOWABLE_TIME >= tempVal);
        }
    }

    private void fillingDictionary(DictionaryImpl<Integer> dictionary) {
        for (int i = 0; i < ITERATION; i++) {
            dictionary.insert(i);
        }
    }
}