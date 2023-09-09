package dushkina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Класс - тестирование методов класса DictionaryImpl
 */
public class DictionaryImplTest {
    private static final int ITERATION = 10000;
    private static final int MAX_TIME = 1000;

    @Test
    @DisplayName("Проверка вставки элемента в справочник")
    public void testInsert() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("Hello");
        Assertions.assertTrue(dictionaryImpl.consist("Hello"));
    }

    @Test
    @DisplayName("Проверка вставки элемента в справочник, если такой уже есть")
    public void testInsertThrow() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("Hello");
        assertThrows(DictionaryException.class, () -> dictionaryImpl.insert("Hello"));
    }

    @Test
    @DisplayName("Проверка времени вставки элемента в справочник")
    public void testInsertTime() {
        DictionaryImpl<Integer> dictionaryImpl = new DictionaryImpl<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.insert(i);
        }
        long finish = System.currentTimeMillis();
        Assertions.assertTrue(finish - start < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка удаления элемента в справочнике")
    public void testDelete() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("Hello");
        dictionaryImpl.delete("Hello");
        Assertions.assertFalse(dictionaryImpl.consist("Hello"));
    }

    @Test
    @DisplayName("Проверка удаления элемента в справочнике, если такого элемента нет")
    public void testDeleteThrow() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("Hello");
        assertThrows(DictionaryException.class, () -> dictionaryImpl.delete("1"));
    }

    @Test
    @DisplayName("Проверка времени удаления элемента из справочника")
    public void testDeleteTime() {
        DictionaryImpl<Integer> dictionaryImpl = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.insert(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.delete(i);
        }
        long finish = System.currentTimeMillis();
        Assertions.assertTrue(finish - start < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка метода, возвращающего рандомный элемент из справочника")
    public void testGetRandom() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("1");
        dictionaryImpl.insert("2");
        dictionaryImpl.insert("3");
        Assertions.assertTrue(dictionaryImpl.consist(dictionaryImpl.getRandom()));
    }

    @Test
    @DisplayName("Проверка метода, возвращающего рандомный элемент из справочника, если справочник пуст")
    public void testGetRandomThrow() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        assertThrows(DictionaryException.class, () -> dictionaryImpl.getRandom());
    }

    @Test
    @DisplayName("Проверка времени метода, возвращающего рандомный элемент из справочника")
    public void testGetRandomTime() {
        DictionaryImpl<Integer> dictionaryImpl = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.insert(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.getRandom();
        }
        long finish = System.currentTimeMillis();
        Assertions.assertTrue(finish - start < MAX_TIME);
    }

    @Test
    @DisplayName("Проверка метода, проверяющего наличие объекта в справочнике")
    public void testConsist() {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("1");
        dictionaryImpl.insert("2");
        dictionaryImpl.insert("3");
        Assertions.assertTrue(dictionaryImpl.consist("2"));
    }

    @Test
    @DisplayName("Проверка времени метода, проверяющего наличие объекта в справочнике")
    public void testConsistTime() {
        DictionaryImpl<Integer> dictionaryImpl = new DictionaryImpl<>();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.insert(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATION; i++) {
            dictionaryImpl.consist(i);
        }
        long finish = System.currentTimeMillis();
        Assertions.assertTrue(finish - start < MAX_TIME);
    }
}
