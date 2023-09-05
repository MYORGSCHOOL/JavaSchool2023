package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {

    @Test
    @DisplayName("Тест вставки элемента в справочник и проверка на содержание")
    public void testSuccessInsert() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertDoesNotThrow(() -> dictionary.insert("Test"));
        Assertions.assertTrue(dictionary.consist("Test"));
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.insert("Test"));
    }

    @Test
    @DisplayName("Тест получения случайного элемента из справочника")
    public void testSuccessGetRandom() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, dictionary::getRandom);
        dictionary.insert("Test");
        dictionary.insert("String");
        dictionary.insert("Random");
        Assertions.assertTrue(dictionary.consist(dictionary.getRandom()));
    }

    @Test
    @DisplayName("Тест удаления элемента из справочника")
    public void testSuccessDelete() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Test");
        Assertions.assertDoesNotThrow(() -> dictionary.delete("Test"));
        Assertions.assertFalse(dictionary.consist("Test"));
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.delete("Test"));
    }
}
