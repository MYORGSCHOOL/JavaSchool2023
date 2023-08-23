package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {

    private static final int MAX_EXECUTION_TIME = 1_000_000_000;
    private static final int MAX_ELEMENT_NUM = 1000;
    private static final int HALF_ELEMENT_NUM = 500;
    @Test
    @DisplayName("вставка в словарь одного элемента")
    public void testSuccessInsertIntoDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        Assertions.assertEquals(true, dictionary.consist(12));
    }

    @Test
    @DisplayName("вставка в словарь дубликата элемента")
    public void testFailInsertIntoDictionaryDuplicate() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        Assertions.assertThrows(ArrayStoreException.class, () -> dictionary.insert(12));
    }

    @Test
    @DisplayName("проверка времени выполнения метода вставки")
    public void testInsertIntoDictionaryConstantTime() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        for (int i = 0; i < MAX_ELEMENT_NUM; i++) {
            start = System.nanoTime();
            dictionary.insert(i);
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(MAX_EXECUTION_TIME >= tempVal);
        }
    }

    @Test
    @DisplayName("получение случайного элемента")
    public void testSuccessGetRandomFromNotEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        dictionary.insert(13);
        dictionary.insert(14);
        int element = dictionary.getRandom();
        Assertions.assertEquals(true, dictionary.consist(element));
    }

    @Test
    @DisplayName("получение случайного элемента из пустого словаря")
    public void testFailGetRandomFromEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(ArrayStoreException.class, () -> dictionary.getRandom());
    }

    @Test
    @DisplayName("проверка времени выполнения метода получения случайного элемента")
    public void testGetRandomElementConstantTime() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        initializeDictionary(dictionary);
        for (int i = 0; i < MAX_ELEMENT_NUM; i++) {
            start = System.nanoTime();
            dictionary.getRandom();
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(MAX_EXECUTION_TIME >= tempVal);
        }
    }

    @Test
    @DisplayName("удаление существующего элемента")
    public void testSuccessRemovePresentElementFromNotEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        dictionary.insert(13);
        dictionary.delete(12);
        Assertions.assertEquals(false, dictionary.consist(12));
    }

    @Test
    @DisplayName("удаление несуществующего элемента")
    public void testFailRemoveAbsentElementFromNotEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        dictionary.insert(13);
        Assertions.assertThrows(ArrayStoreException.class, () -> dictionary.delete(15));
    }

    @Test
    @DisplayName("удаление из пустого словаря")
    public void testFailRemoveFromEmptyDictionary() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(ArrayStoreException.class, () -> dictionary.delete(12));
    }

    @Test
    @DisplayName("проверка времени выполнения метода удаления элемента")
    public void testRemoveElementConstantTime() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        initializeDictionary(dictionary);
        for (int i = 0; i < HALF_ELEMENT_NUM; i++) {
            int element = dictionary.getRandom();
            start = System.nanoTime();
            dictionary.delete(element);
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(MAX_EXECUTION_TIME >= tempVal);
        }
    }

    @Test
    @DisplayName("проверка времени выполнения метода поиска элемента")
    public void testConsistElementConstantTime() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = 0;
        long tempVal = 0;
        long end = 0;
        initializeDictionary(dictionary);
        for (int i = 0; i < HALF_ELEMENT_NUM; i++) {
            int element = dictionary.getRandom();
            start = System.nanoTime();
            dictionary.consist(element);
            end = System.nanoTime();
            tempVal = end - start;
            Assertions.assertTrue(MAX_EXECUTION_TIME >= tempVal);
        }
    }

    public void initializeDictionary(DictionaryImpl<Integer> dictionary) {
        for (int i = 0; i < MAX_ELEMENT_NUM; i++) {
            dictionary.insert(i);
        }
    }
}
