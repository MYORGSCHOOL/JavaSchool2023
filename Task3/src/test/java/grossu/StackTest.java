package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {

    @Test
    @DisplayName("checking that the function - push works correctly")
    void pushTest() {
        Object o = new Object();
        Object[] expected = {o};
        Stack actual = new Stack(1);
        actual.push(o);
        Assertions.assertArrayEquals(expected, actual.getStack());
    }

    @Test
    @DisplayName("checking that the function - push works correctly when stack is full")
    void pushOverflowTest() {
        Assertions.assertDoesNotThrow(() -> {
            Stack actual = new Stack(2);
            for (int i = 0; i < 3; i++) {
                actual.push(new Object());
            }
        });
    }

    @Test
    @DisplayName("checking that the function - pop works correctly")
    void popTest() {
        Object expected = new Object();
        Stack actual = new Stack(2);
        actual.push(new Object());
        actual.push(expected);
        Assertions.assertEquals(expected, actual.pop());
    }

    @Test
    @DisplayName("checking that the function - pop works correctly when stack is empty")
    void popEmptyTest() {
        Stack actual = new Stack(0);
        Assertions.assertNull(actual.pop());
    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when stack is empty")
    void isEmptyTest() {
        Stack actual = new Stack(0);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when stack isn't empty")
    void isEmptyFalseTest() {
        Stack actual = new Stack(1);
        actual.push(new Object());
        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    @DisplayName("checking that the function - top works correctly")
    void topTest() {
        Object expected = new Object();
        Stack actual = new Stack(2);
        actual.push(new Object());
        actual.push(expected);
        Assertions.assertEquals(expected, actual.top());
    }

    @Test
    @DisplayName("checking that the function - top works correctly when stack is empty")
    void topEmptyTest() {
        Stack actual = new Stack(0);
        Assertions.assertNull(actual.top());
    }
}

