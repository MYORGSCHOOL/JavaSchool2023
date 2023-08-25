package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImplTest {
    /**
     * Размер словаря для тестов
     */
    private final int DICTIONARY_SIZE_FOR_TESTS = 100_000;

    @Test
    @DisplayName("Тестирование поиска элемента в словаре")
    public void testSuccessConsist() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("Один");

        Assertions.assertTrue(dictionary.consist("Один"));
        Assertions.assertFalse(dictionary.consist("Два"));
    }

    @Test
    @DisplayName("Тест удачного добавления элементов в словарь")
    public void testSuccessInsertInDictionary() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();

        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
                dictionary.insert(Integer.toString(i));
            }
        });
        Assertions.assertTrue(dictionary.consist("9999"));
    }


    @Test
    @DisplayName("Тестирование удаление элемента в словаре")
    public void testSuccessDelete() {

        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        String testString = "Тест";
        dictionary.insert(testString);
        Assertions.assertDoesNotThrow(() -> dictionary.delete(testString));

        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.insert(Integer.toString(i));
        }

        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
                dictionary.delete(Integer.toString(i));
            }
        });
    }

    @Test
    @DisplayName("Тестирование метода рандом элемента в словаре")
    public void testSuccessRandom() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        String testString = "Тест";
        dictionary.insert(testString);
        Assertions.assertDoesNotThrow(() -> dictionary.delete(testString));

        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.insert(Integer.toString(i));
        }

        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
                dictionary.getRandom();
            }
        });
    }

    @Test
    @DisplayName("Тестирование выбрасывания исключений")
    public void testSuccessThrowException() {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        Assertions.assertThrows(DictionaryException.class, dictionary::getRandom);
        Assertions.assertThrows(DictionaryException.class, () -> dictionary.delete("Тест"));
    }

    @Test
    @DisplayName("Вставка + удаление")
    public void testSuccessInsertAndDelete() {
        var dictionary = new DictionaryImpl<String>();
        Assertions.assertDoesNotThrow(() -> {
            dictionary.insert("1");
            dictionary.insert("2");
            dictionary.insert("3");
            dictionary.delete("1");
            dictionary.insert("4");
        });
    }
}
