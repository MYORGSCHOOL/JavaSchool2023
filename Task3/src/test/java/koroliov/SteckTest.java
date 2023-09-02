package koroliov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SteckTest {
    Steck steck;
    @BeforeEach
    void prepareDate() {
        steck = new Steck();
        steck.Push("one");
        steck.Push("two");
        steck.Push("three");
    }

    @Test
    void testPop() {
        assertEquals("three", (String) steck.Pop());
    }

    @Test
    void testPush() {
        assertEquals(1, steck.Push("four"));
    }

    @Test
    void testTop() {
        assertEquals("three", steck.Top());
    }

    @Test
    void testGetAll() {
        Object[] arr = {"three", "two", "one"};
        Object[] temp = steck.getAll();
        Boolean result = false;
        for (int i = 0; i < temp.length; i++) {
            if (arr[i].equals(temp[i])) {
                result = true;
            } else result = false;
        }

        assertEquals(true, result);
    }

    @Test
    void testIsEmpty() {
        assertEquals(false, steck.isEmpty());
    }
}
