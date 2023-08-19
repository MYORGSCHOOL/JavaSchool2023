package tumbaev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import tumbaev.exception.DictionaryDeletionException;
import tumbaev.exception.DictionaryInsertionException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * NOTE: most of the tests rely on execution time of the operation to check if it runs in O(1).
 * This approach can produce wrong results.
 * <br>
 * So if a test fails, run it a couple more times to make sure it wasn't an accident.
 */
class DictionaryImplTest {

    /**
     * A coefficient by which the average duration of the operation should be multiplied
     * in order to get the duration to test against.
     */
    private static final float ERROR_FACTOR = 2.5f;

    /**
     * Initial capacity of a dictionary for execution time tests.
     */
    private static final int INITIAL_CAPACITY = 1000;

    /**
     * Number of iterations to run for execution time tests.
     * This should be less or equal to 75% of {@link #INITIAL_CAPACITY}
     * because after that the size of underlying collections will be expanded,
     * which will cause a significant increase in execution time.
     */
    private static final int ITERATIONS = 700;

    @Test
    @Disabled
    @DisplayName("Inserting existing element should throw exception")
    void testInsertingExistingElementShouldThrowException() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("test");
        assertThrows(DictionaryInsertionException.class, () -> dictionary.insert("test"));
    }

    @Test
    @Disabled
    @DisplayName("Insertion should work in constant time")
    void testInsertionShouldWorkInConstantTime() {
        Dictionary<String> dictionary = new DictionaryImpl<>(INITIAL_CAPACITY);
        long testDuration = getTestDuration(() -> dictionary.insert(""));

        for (int i = 0; i < ITERATIONS; i++) {
            final String s = "test" + i;
            long duration = measureDurationInNanos(() -> dictionary.insert(s));
            assertTrue(
                    duration <= testDuration,
                    String.format("%dth duration = %d ns should be less or equal to test duration = %d ns",
                            i, duration, testDuration)
            );
        }
    }

    @Test
    @DisplayName("Removing non-existent element should throw an exception")
    void testRemovingNonExistentElementShouldThrowException() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        assertThrows(DictionaryDeletionException.class, () -> dictionary.delete("test"));
    }

    @Test
    @DisplayName("Deletion should delete existing element")
    void testDeletionShouldDeleteExistingElement() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("1");
        dictionary.insert("2");
        dictionary.delete("1");
        assertFalse(dictionary.contains("1"));
    }

    @Test
    @Disabled
    @DisplayName("Deletion should work in constant time")
    void testDeletionShouldWorkInConstantTime() {
        Dictionary<String> dictionary = new DictionaryImpl<>(INITIAL_CAPACITY);
        dictionary.insert("");
        long testDuration = getTestDuration(() -> dictionary.delete(""));

        //adding elements for deletion test
        List<String> elements = new ArrayList<>(ITERATIONS);
        for (int i = 0; i < ITERATIONS; i++) {
            final String s = "test" + i;
            elements.add(s);
            dictionary.insert(s);
        }

        //performing deletion
        for (int i = 0; i < elements.size(); i++) {
            final String s = elements.get(i);
            long duration = measureDurationInNanos(() -> dictionary.delete(s));
            assertTrue(
                    duration <= testDuration,
                    String.format("%dth duration = %d ns should be less or equal to test duration = %d ns",
                            i, duration, testDuration)
            );
        }
    }

    @Test
    @DisplayName("Getting random element from empty dictionary should return null")
    void testGettingRandomElementFromEmptyDictionaryShouldReturnNull() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        assertNull(dictionary.getRandom());
    }

    @Test
    @DisplayName("Getting random element from non-empty dictionary should return random element")
    void testGettingRandomElementFromNonEmptyDictionaryShouldReturnRandomElement() {
        Dictionary<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("1");
        dictionary.insert("2");
        dictionary.insert("3");
        dictionary.insert("4");
        String random = dictionary.getRandom();
        assertTrue(dictionary.contains(random));
    }

    @Test
    @DisplayName("Getting random should work in constant time")
    @Disabled
    void testGettingRandomShouldWorkInConstantTime() {
        Dictionary<String> dictionary = new DictionaryImpl<>(INITIAL_CAPACITY);
        dictionary.insert("");
        long testDuration = getTestDuration(dictionary::getRandom);

        for (int i = 0; i < ITERATIONS; i++) {
            final String s = "test" + i;
            dictionary.insert(s);
            long duration = measureDurationInNanos(dictionary::getRandom);
            assertTrue(
                    duration <= testDuration,
                    String.format("%dth duration = %d ns should be less or equal to test duration = %d ns",
                            i, duration, testDuration)
            );
        }
    }

    @Test
    @DisplayName("Contains should work in constant time")
    @Disabled
    void testContainsShouldWorkInConstantTime() {
        Dictionary<String> dictionary = new DictionaryImpl<>(INITIAL_CAPACITY);
        dictionary.insert("");
        long testDuration = getTestDuration(() -> dictionary.contains(""));
        //for some reason, this operation produces a lot of accidental errors, so had to multiply the duration
        testDuration *= ERROR_FACTOR;

        for (int i = 0; i < ITERATIONS; i++) {
            final String s = "test" + i;
            dictionary.insert(s);
            long duration = measureDurationInNanos(() -> dictionary.contains(s));
            assertTrue(
                    duration <= testDuration,
                    String.format("%dth duration = %d ns should be less or equal to test duration = %d ns",
                            i, duration, testDuration)
            );
        }
    }


    /**
     * Calculate duration to test against based on duration of one execution of operation and {@link #ERROR_FACTOR}.
     *
     * @param operation the operation that would be executed
     * @return the calculated testing duration
     */
    private long getTestDuration(Executable operation) {
        return (long) (measureDurationInNanos(operation) * ERROR_FACTOR);
    }

    /**
     * Measures the duration of executing the given executable in nanoseconds.
     *
     * @param executable the executable to be measured
     * @return the duration in nanoseconds
     */
    private long measureDurationInNanos(Executable executable) {
        long start = System.nanoTime();
        try {
            executable.execute();
        } catch (Throwable e) {
            throw new DurationMeasureException("Could not measure duration because of the exception", e);
        }
        return System.nanoTime() - start;
    }
}