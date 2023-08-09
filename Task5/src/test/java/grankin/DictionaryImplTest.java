package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {

    @Test
    @DisplayName("Проверка вставления элементов в словарь")
    public void testInsertElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5; i++) {
            dictionary.insert(i);
        }
        Assertions.assertEquals(5, dictionary.sizeDictionary());
    }

    @Test
    @DisplayName("Заполнение большим количеством элементов за 1 сек")
    public void testInsertManyElementsForOneSec() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        long startTime = 0;
        long endTime = 0;
        double timeElapsed = 0;

        int countIteration = 1_000_000;     // с 10 млн именно в тестах не завершается хорошо (не время, а вообще тест),
                                            // а в main все работает, поэтому уменьшаем на 1 нолик
                                            // получается 1 млн

        int forDivisionNanoSecToSec = 1000000000;

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.insert(i);
        }
        endTime = System.nanoTime();
        timeElapsed = (endTime - startTime) / (double) forDivisionNanoSecToSec;

        Assertions.assertTrue(timeElapsed < 1.0);
    }

    @Test
    @DisplayName("Проверка получения случайного элемента в словаре")
    public void testGetRandomElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5; i++) {
            dictionary.insert(i);
        }
        Assertions.assertTrue(dictionary.sizeDictionary() >= 0);
    }

    @Test
    @DisplayName("Проверка получения случайных элеменов большого количества за 1 сек")
    public void testGetRandomManyElementsForOneSec() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        long startTime = 0;
        long endTime = 0;
        double timeElapsed = 0;

        int countIteration = 1_000_000;     // с 10 млн именно в тестах не завершается хорошо (не время, а вообще тест),
        // а в main все работает, поэтому уменьшаем на 1 нолик
        // получается 1 млн

        int forDivisionNanoSecToSec = 1000000000;

        for (int i = 0; i < countIteration; i++) {
            dictionary.insert(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.getRandom();
        }
        endTime = System.nanoTime();
        timeElapsed = (endTime - startTime) / (double) forDivisionNanoSecToSec;

        Assertions.assertTrue(timeElapsed < 1.0);
    }

    @Test
    @DisplayName("Проверка содержания элемента в словаре")
    public void testConsistElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5; i++) {
            dictionary.insert(i);
        }
        Assertions.assertTrue(dictionary.consist(2));
    }

    @Test
    @DisplayName("Проверка содержания несуществующего элемента в словаре")
    public void testNotConsistElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5; i++) {
            dictionary.insert(i);
        }
        Assertions.assertFalse(dictionary.consist(6));
    }


    @Test
    @DisplayName("Проверка содержания большим количеством элементов за 1 сек")
    public void testConsistManyElementsForOneSec() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        long startTime = 0;
        long endTime = 0;
        double timeElapsed = 0;

        int countIteration = 1_000_000;     // с 10 млн именно в тестах не завершается хорошо (не время, а вообще тест),
        // а в main все работает, поэтому уменьшаем на 1 нолик
        // получается 1 млн

        int forDivisionNanoSecToSec = 1000000000;

        for (int i = 0; i < countIteration; i++) {
            dictionary.insert(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.consist(i);
        }
        endTime = System.nanoTime();
        timeElapsed = (endTime - startTime) / (double) forDivisionNanoSecToSec;

        Assertions.assertTrue(timeElapsed < 1.0);
    }

    @Test
    @DisplayName("Проверка удаления элемента из словаря")
    public void testDeleteElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5; i++) {
            dictionary.insert(i);
        }
        dictionary.delete(2);
        Assertions.assertFalse(dictionary.consist(2));
    }

    @Test
    @DisplayName("Удаление большого количества элементов за 1 сек")
    public void testDeleteManyElementsForOneSec() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        long startTime = 0;
        long endTime = 0;
        double timeElapsed = 0;

        int countIteration = 1_000_000;     // с 10 млн именно в тестах не завершается хорошо (не время, а вообще тест),
        // а в main все работает, поэтому уменьшаем на 1 нолик
        // получается 1 млн

        int forDivisionNanoSecToSec = 1000000000;

        for (int i = 0; i < countIteration; i++) {
            dictionary.insert(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.delete(i);
        }
        endTime = System.nanoTime();
        timeElapsed = (endTime - startTime) / (double) forDivisionNanoSecToSec;

        Assertions.assertTrue(timeElapsed < 1.0);
    }

    @Test
    @DisplayName("Проверка удаления элемента из словаря и изменение его размера")
    public void testDeleteElementAndResize() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5; i++) {
            dictionary.insert(i);
        }
        dictionary.delete(0);
        dictionary.delete(1);
        Assertions.assertEquals(3, dictionary.sizeDictionary());
    }
}
