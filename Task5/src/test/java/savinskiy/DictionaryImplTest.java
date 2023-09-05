package savinskiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DictionaryImplTest {
    Dictionary<Integer> dictionary;

    @BeforeEach
    public void init() {
        dictionary = new DictionaryImpl<>();
    }

    @Test
    void insert() {
        dictionary.insert(33);
        assertTrue(dictionary.consist(33));

        try {
            dictionary.insert(33);
        } catch (IllegalStateException e) {
            assert e.getMessage().equals("Element already in dictionary");
        }
    }

    @Test
    void getRandom() {
        dictionary.insert(123433);
        Integer randomElement = dictionary.getRandom();
        assertTrue(dictionary.consist(randomElement));
    }

    @Test
    void delete() {
        dictionary.insert(777);
        dictionary.delete(777);
        assertFalse(dictionary.consist(777));
    }

    @Test
    void consist() {
        dictionary.insert(666);
        assertTrue(dictionary.consist(666));
        assertFalse(dictionary.consist(444));
    }
}
