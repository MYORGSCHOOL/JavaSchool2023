package alexenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DictionaryImplTest {

    private static final int LIMIT_TIME_WORK = 1000;

    @Test
    @DisplayName("Вставка одного элемента")
    public void testInsertOneElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(1);

        Assertions.assertTrue(dictionary.consist(1));
    }

    @Test
    @DisplayName("Вставка трёх миллионов элементов меньше 1 секунды")
    public void testTimeWorkInsertThreeMillionElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 3_000_000; i++) {
            dictionary.insert(i);
        }
        long end = System.currentTimeMillis();
        long realTimeWork = end - start;

        Assertions.assertTrue(LIMIT_TIME_WORK > realTimeWork);
    }

    @Test
    @DisplayName("Удаление одного элемента")
    public void testDeleteOneElement() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(1);
        dictionary.delete(1);

        Assertions.assertFalse(dictionary.consist(1));
    }

    @Test
    @DisplayName("Удаление трёх миллионов элементов меньше 1 секунды")
    public void testTimeWorkDeleteThreeMillionElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5_000_000; i++) {
            dictionary.insert(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 1_000_000; i < 4_000_000; i++) {
            dictionary.delete(i);
        }
        long end = System.currentTimeMillis();
        long realTimeWork = end - start;

        Assertions.assertTrue(LIMIT_TIME_WORK > realTimeWork);
    }

    @Test
    @DisplayName("Поиск одного элемента")
    public void testConsistOneElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("string 1");
        dictionary.insert("string 2");
        dictionary.insert("string 3");

        Assertions.assertTrue(dictionary.consist("string 2"));
    }

    @Test
    @Disabled
    @DisplayName("Поиск трёх миллионов элементов меньше 1 секунды")
    public void testTimeWorkConsistThreeMillionElements() {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5_000_000; i++) {
            dictionary.insert(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 1_000_000; i < 4_000_000; i++) {
            dictionary.delete(i);
        }
        long end = System.currentTimeMillis();
        long realTimeWork = end - start;

        Assertions.assertTrue(LIMIT_TIME_WORK > realTimeWork);
    }

    @Test
    @DisplayName("Получение одного случайного элемента")
    public void testGetOneRandomElement() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("string 1");
        dictionary.insert("string 2");
        dictionary.insert("string 3");
        String randomElement = dictionary.getRandom();

        Assertions.assertTrue(dictionary.consist(randomElement));
    }

    @Test
    @DisplayName("Получение трёх миллионов случайных элементов меньше 1 секунды")
    public void testTimeWorkGetRandomThreeMillionElements() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        for (int i = 0; i < 5_000_000; i++) {
            dictionary.insert("All hi!");
        }
        ArrayList<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 1_000_000; i < 4_000_000; i++) {
            list.add(dictionary.getRandom());
        }
        long end = System.currentTimeMillis();
        long realTimeWork = end - start;

        Assertions.assertTrue(LIMIT_TIME_WORK > realTimeWork);
    }
}
