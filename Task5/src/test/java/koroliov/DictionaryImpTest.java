package koroliov;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class DictionaryImpTest {
    private DictionaryImp<String> dimp = new DictionaryImp<>();
    /**
     * Variable for measuring the execution time of the method.
     */
    private final long TIME_LIMIT = 1000;

    @Test
    @DisplayName("A test to check an item in a dictionary.")
    @Timeout(value = TIME_LIMIT, unit = TimeUnit.MICROSECONDS)
    void testConsist() {
        this.dimp.insert("one");
        this.dimp.insert("two");
        this.dimp.insert("three");
        this.dimp.insert("four");

        Assertions.assertTrue(this.dimp.consist("two"));
    }

    @Test
    @DisplayName("Checks to see if the item has been removed from the dictionary.")
    @Timeout(value = TIME_LIMIT, unit = TimeUnit.MICROSECONDS)
    void testDelete() {
        this.dimp.insert("one");
        this.dimp.insert("two");
        this.dimp.insert("three");
        this.dimp.insert("four");
        this.dimp.delete("one");

        Assertions.assertFalse(this.dimp.consist("one"));
    }

    @Test
    @DisplayName("Checking the selection of a random element from the dictionary.")
    @Timeout(value = TIME_LIMIT, unit = TimeUnit.MICROSECONDS)
    void testGetRandom() {
        this.dimp.insert("one");
        this.dimp.insert("two");
        this.dimp.insert("three");
        this.dimp.insert("four");

        Assertions.assertTrue(this.dimp.consist(this.dimp.getRandom()));
    } 

    @Test
    @DisplayName("Verify that a new item has been added to the dictionary.")
    @Timeout(value = TIME_LIMIT, unit = TimeUnit.MICROSECONDS)
    void testInsert() {
        this.dimp.insert("one");
        this.dimp.insert("two");

        Assertions.assertTrue(this.dimp.consist("one"));
    }
}
