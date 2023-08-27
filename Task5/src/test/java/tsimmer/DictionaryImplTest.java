package tsimmer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DictionaryImplTest {
   private final DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
    @Test
    @DisplayName("Тест успешного добавления элемента в справочник")
    void testSuccessInsertElements() {
        dictionary.insert(1);
        Assertions.assertTrue(dictionary.consist(1));
    }
    @Test
    @DisplayName("Тест неудачного добавления элемента в справочник")
    public void testFailInsertElements() {
        dictionary.insert(1);
        Assertions.assertThrows(DictionaryException.class,()->dictionary.insert(1));
    }


    @Test
    @DisplayName("Тест успешного удаления элемента из справочника")
    void testSuccessDeleteElements() {
        dictionary.insert(1);
        dictionary.delete(1);
        Assertions.assertFalse(dictionary.consist(1));
    }
    @Test
    @DisplayName("Тест неудачного удаления элемента в справочник")
    public void testFailDeleteElements() {
        Assertions.assertThrows(DictionaryException.class,()->dictionary.delete(2));
    }

    @Test
    @DisplayName("Тест успешного поиска элемента в справочнике")
    void testSuccessConsistElements() {
        Assertions.assertFalse(dictionary.consist(1));
    }

    @Test
    @DisplayName("Тест поиска случайного элемента в справочнике")
    void testSuccessRandomElements() {
        for (int i = 0; i < 10; i++) {
            dictionary.insert(i);
        }
        Assertions.assertTrue(dictionary.consist(dictionary.getRandom()));
    }
    @Test
    @DisplayName("Тест неудачного поиска случайного элемента в справочнике")
    public void testFailRandomElements() {
        DictionaryImpl<Integer> dictionary1 = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, dictionary1::getRandom);
    }

}
