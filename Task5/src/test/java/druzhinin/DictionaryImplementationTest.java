package druzhinin;

import druzhinin.exceptions.EmptyDictionaryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования DictionaryImplementation.
 *
 * @author Дружинин Артем.
 */
public class DictionaryImplementationTest {
    /**
     * Начальная емкость для словаря с малым количеством элементов.
     */
    private final int INITIAL_CAPACITY_SMALL = 1_000;

    /**
     * Начальная емкость для словаря с большим количеством элементов.
     */
    private final int INITIAL_CAPACITY_LARGE = 1_000_000;

    /**
     * Количество итераций по вставке элементов в словарь с малым количеством элементов.
     */
    private final int ITERATIONS_AMOUNT_SMALL = 600;

    /**
     * Количество итераций по вставке элементов в словарь с большим количеством элементов.
     */
    private final int ITERATIONS_AMOUNT_LARGE = 600_000;

    /**
     * Максимальное время выполнения операции со словарем (в наносекундах).
     */
    private final long MAX_EXECUTION_TIME_NANO = 1_000_000;

    /**
     * Максимальная разность времени выполнения операции со словарем с большим количеством элементов и
     * времени выполнения операции со словарем с малым количеством элементов (в наносекундах).
     */
    private final long MAX_DIFFERENCE_TIME_NANO = 100_000;

    /**
     * Словарь с малым количеством элементов.
     */
    private final Dictionary<Integer> smallCapacityDictionary
            = setUpDictionary(INITIAL_CAPACITY_SMALL, ITERATIONS_AMOUNT_SMALL);

    /**
     * Словарь с большим количеством элементов.
     */
    private final Dictionary<Integer> largeCapacityDictionary
            = setUpDictionary(INITIAL_CAPACITY_LARGE, ITERATIONS_AMOUNT_LARGE);

    @Test
    @DisplayName("Тест на успешное создание пустого словаря через конструктор без параметров")
    void testSuccessCreationEmptyDictionary() {
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        Assertions.assertNotNull(dictionary);
        Assertions.assertEquals(0, dictionary.size());
    }

    @Test
    @DisplayName("Тест на успешное добавление элемента в словарь")
    void testSuccessInsertIntoEmptyDictionary() {
        String element = "Hello";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element);

        Assertions.assertEquals(1, dictionary.size());
        Assertions.assertTrue(dictionary.contains(element));
    }

    @Test
    @DisplayName("Тест на добавление только одного из трех повторяющихся элементов в словарь")
    void testNoInsertIntoDictionaryWhenElementIsPresent() {
        String element = "Hello";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element);
        dictionary.insert(element);
        dictionary.insert(element);

        Assertions.assertEquals(1, dictionary.size());
        Assertions.assertTrue(dictionary.contains(element));
    }

    @Test
    @DisplayName("Тест на получение EmptyDictionaryException при попытке вызвать метод getRandom у пустого словаря")
    void testEmptyDictionaryExceptionWhenGettingRandomFromEmptyDictionary() {
        Dictionary<String> dictionary = new DictionaryImplementation<>();

        Assertions.assertThrows(EmptyDictionaryException.class, dictionary::getRandom);
    }

    @Test
    @DisplayName("Тест на успешное получение единственного элемента словаря из метода getRandom")
    void testSuccessGetRandomFromDictionaryWithOneElement() {
        String element = "Hello";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element);

        Assertions.assertEquals(element, dictionary.getRandom());
    }

    @Test
    @DisplayName("Тест на успешное удаление элемента из словаря")
    void testSuccessDeleteElementFromDictionary() {
        String element1 = "Hello";
        String element2 = "World";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element1);
        dictionary.insert(element2);

        Assertions.assertTrue(dictionary.contains(element1));

        dictionary.delete(element1);

        Assertions.assertFalse(dictionary.contains(element1));
        Assertions.assertTrue(dictionary.contains(element2));
    }

    @Test
    @DisplayName("Тест на отсутствие удаления элемента, которого нет в словаре")
    void testNoDeleteElementFromDictionaryThatIsNotPresent() {
        String element1 = "Hello";
        String element2 = "World";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element1);
        dictionary.delete(element2);

        Assertions.assertEquals(1, dictionary.size());
        Assertions.assertTrue(dictionary.contains(element1));
    }

    @Test
    @DisplayName("Тест на получение true при вызове метода contains если элемент присутствует в словаре")
    void testContainsTrueWhenElementIsPresentInDictionary() {
        String element = "Hello";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element);

        Assertions.assertTrue(dictionary.contains(element));
    }

    @Test
    @DisplayName("Тест на получение false при вызове метода contains если элемент отсутствует в словаре")
    void testContainsFalseWhenElementIsNotPresentInDictionary() {
        String element1 = "Hello";
        String element2 = "World";
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert(element1);

        Assertions.assertFalse(dictionary.contains(element2));
    }

    @Test
    @DisplayName("Тест на то, что время вставки элемента в словарь линейное")
    void testInsertTimeIsLinear() {
        long startTimeSmallCapacity = System.nanoTime();
        smallCapacityDictionary.insert(ITERATIONS_AMOUNT_SMALL);
        long smallCapacityExecutionTimeNano = System.nanoTime() - startTimeSmallCapacity;

        long startTimeLargeCapacity = System.nanoTime();
        largeCapacityDictionary.insert(ITERATIONS_AMOUNT_LARGE);
        long largeCapacityExecutionTimeNano = System.nanoTime() - startTimeLargeCapacity;

        Assertions.assertTrue(smallCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(largeCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(Math.abs(largeCapacityExecutionTimeNano -
                smallCapacityExecutionTimeNano) < MAX_DIFFERENCE_TIME_NANO);
    }

    @Test
    @DisplayName("Тест на то, что время получения случайного элемента из словаря линейное")
    void testGetRandomTimeIsLinear() {
        long startTimeSmallCapacity = System.nanoTime();
        smallCapacityDictionary.getRandom();
        long smallCapacityExecutionTimeNano = System.nanoTime() - startTimeSmallCapacity;

        long startTimeLargeCapacity = System.nanoTime();
        largeCapacityDictionary.getRandom();
        long largeCapacityExecutionTimeNano = System.nanoTime() - startTimeLargeCapacity;

        Assertions.assertTrue(smallCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(largeCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(Math.abs(largeCapacityExecutionTimeNano -
                smallCapacityExecutionTimeNano) < MAX_DIFFERENCE_TIME_NANO);
    }

    @Test
    @DisplayName("Тест на то, что время удаления элемента в словарь линейное")
    void testDeleteTimeIsLinear() {
        long startTimeSmallCapacity = System.nanoTime();
        smallCapacityDictionary.delete(smallCapacityDictionary.getRandom());
        long smallCapacityExecutionTimeNano = System.nanoTime() - startTimeSmallCapacity;

        long startTimeLargeCapacity = System.nanoTime();
        largeCapacityDictionary.delete(largeCapacityDictionary.getRandom());
        long largeCapacityExecutionTimeNano = System.nanoTime() - startTimeLargeCapacity;

        Assertions.assertTrue(smallCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(largeCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(Math.abs(largeCapacityExecutionTimeNano -
                smallCapacityExecutionTimeNano) < MAX_DIFFERENCE_TIME_NANO);
    }

    @Test
    @DisplayName("Тест на то, что время проверки, содержится ли элемент в словаре линейное")
    void testContainsTimeIsLinear() {
        long startTimeSmallCapacity = System.nanoTime();
        smallCapacityDictionary.contains(smallCapacityDictionary.getRandom());
        long smallCapacityExecutionTimeNano = System.nanoTime() - startTimeSmallCapacity;

        long startTimeLargeCapacity = System.nanoTime();
        largeCapacityDictionary.contains(largeCapacityDictionary.getRandom());
        long largeCapacityExecutionTimeNano = System.nanoTime() - startTimeLargeCapacity;

        Assertions.assertTrue(smallCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(largeCapacityExecutionTimeNano < MAX_EXECUTION_TIME_NANO);
        Assertions.assertTrue(Math.abs(largeCapacityExecutionTimeNano -
                smallCapacityExecutionTimeNano) < MAX_DIFFERENCE_TIME_NANO);
    }

    /**
     * Метод для создания словаря с заданными начальной емкостью и количеством итераций по вставке элементов.
     * Созданный словарь будет иметь тип {@code Dictionary<Integer>}.
     *
     * @param initialCapacity Начальная емкость словаря.
     * @param iterationsAmount Количество итераций по вставке элементов в словарь.
     * @return Экземпляр словаря с типом {@code Dictionary<Integer>}.
     */
    private Dictionary<Integer> setUpDictionary(int initialCapacity, int iterationsAmount) {
        Dictionary<Integer> dictionary = new DictionaryImplementation<>(initialCapacity);
        for (int i = 0; i < iterationsAmount; i++) {
            dictionary.insert(i);
        }

        return dictionary;
    }
}
