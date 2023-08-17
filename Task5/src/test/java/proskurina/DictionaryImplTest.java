package proskurina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import proskurina.exceptions.AlreadyExistsException;
import proskurina.exceptions.EmptyDictionaryException;

import java.util.NoSuchElementException;

/**
 * Класс, тестирующий {@code DictionaryImpl}.
 */
class DictionaryImplTest {
    
    private DictionaryImpl<Integer> dictionary;
    private final Integer testObject = 1;
    private final int operationsCount = 1_000_000;
    
    @BeforeEach
    void setUp() {
        // Во избежание вызова resize в HashMap.
        dictionary = new DictionaryImpl<>((int) (operationsCount * 1.75F));
    }
    
    @Test
    @DisplayName("Тестируется EmptyDictionaryException после getRandom")
    void testEmptyDictionaryExceptionAfterGetRandom() {
        Assertions.assertThrows(EmptyDictionaryException.class, () -> dictionary.getRandom());
    }
    
    @Test
    @DisplayName("Тестируется NoSuchElementException после delete")
    void testNoSuchElementExceptionAfterDelete() {
        Assertions.assertThrows(NoSuchElementException.class, () -> dictionary.delete(testObject));
    }
    
    @Test
    @DisplayName("Тестируется AlreadyExistsException после вставки дублирующегося элемента")
    void testAlreadyExistsExceptionAfterInsert() {
        dictionary.insert(testObject);
        Assertions.assertThrows(AlreadyExistsException.class, () -> dictionary.insert(testObject));
    }
    
    @Test
    @DisplayName("Тестируется корректность insert")
    void testCorrectInsert() {
        dictionary.insert(testObject);
        Assertions.assertTrue(dictionary.consist(testObject));
    }
    
    @Test
    @DisplayName("Тестируется корректность delete")
    void testCorrectDelete() {
        dictionary.insert(testObject);
        dictionary.delete(testObject);
        
        Assertions.assertFalse(dictionary.consist(testObject));
    }
    
    @Test
    @DisplayName("Тестируется корректность consists")
    void testCorrectConsists() {
        Assertions.assertFalse(dictionary.consist(testObject));
    }
    
    @Test
    @DisplayName("Тестируется корректность getRandom")
    void testCorrectGetRandom() {
        dictionary.insert(testObject);
        Assertions.assertEquals(testObject, dictionary.getRandom());
    }
    
    @Nested
    @DisplayName("Тесты для проверки времени выполнения методов")
    class TimeCheckTest {
        /**
         * Время начала выполнения метода.
         */
        private long startTime;
        /**
         * Время выполнения метода меньше 1 секунды.
         */
        private final int expectedTime = 1000;
        
        @Test
        @DisplayName("Тестрируется отработка insert меньше, чем за 1 секудну")
        void testInsertForLessThanOneSecond() {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < operationsCount; i++) {
                dictionary.insert(i);
            }
            Assertions.assertTrue(System.currentTimeMillis() - startTime < expectedTime);
        }
        
        @Test
        @DisplayName("Тестрируется отработка delete меньше, чем за 1 секудну")
        void testDeleteForLessThanOneSecond() {
            fillDictionary();
            
            startTime = System.currentTimeMillis();
            for (int i = 0; i < operationsCount; i++) {
                dictionary.delete(i);
            }
            Assertions.assertTrue(System.currentTimeMillis() - startTime < expectedTime);
        }
        
        @Test
        @DisplayName("Тестрируется отработка consists меньше, чем за 1 секудну")
        void testConsistsForLessThanOneSecond() {
            fillDictionary();
            
            startTime = System.currentTimeMillis();
            for (int i = 0; i < operationsCount; i++) {
                dictionary.consist(i);
            }
            Assertions.assertTrue(System.currentTimeMillis() - startTime < expectedTime);
        }
        
        @Test
        @DisplayName("Тестрируется отработка getRandom меньше, чем за 1 секудну")
        void testGetRandomForLessThanOneSecond() {
            fillDictionary();
            
            startTime = System.currentTimeMillis();
            for (int i = 0; i < operationsCount; i++) {
                dictionary.getRandom();
            }
            Assertions.assertTrue(System.currentTimeMillis() - startTime < expectedTime);
        }
        
        /**
         * Заполняет справочник элементами от 0 до {@code operationsCount}.
         */
        private void fillDictionary() {
            for (int i = 0; i < operationsCount; i++) {
                dictionary.insert(i);
            }
        }
    }
}