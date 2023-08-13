package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

/**
 * Класс для тестирования методов класса Stack
 */
public class StackTest {

    @Test
    @DisplayName("checking that the function - push works correctly")
    void testSuccessPushWithOneElementInStack() {
        Object o = new Object();
        Object[] expected = {o};
        Stack actual = new Stack(1);
        actual.push(o);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("checking that the function - push works correctly when stack is full")
    void testDoesNotTrowPushWhenStackIsFull() {
        Assertions.assertDoesNotThrow(() -> {
            Stack actual = new Stack(2);
            for (int i = 0; i < 3; i++) {
                actual.push(new Object());
            }
        });
    }

    @Test
    @DisplayName("checking that the function - pop works correctly")
    void testSuccessPopWithTwoElementInStack() {
        Object expected = new Object();
        Stack actual = new Stack(2);
        actual.push(new Object());
        actual.push(expected);
        Assertions.assertEquals(expected, actual.pop());
    }

    @Test
    @DisplayName("checking that the function - pop works correctly when stack is empty")
    void testGetThrowPopWhenStackIsEmpty() {
        Stack actual = new Stack(0);
        Assertions.assertThrows(EmptyStackException.class, actual::pop);
    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when stack is empty")
    void testSuccessIsEmptyWhenStackIsEmpty() {
        Stack actual = new Stack(0);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("checking that the function - isEmpty works correctly when stack isn't empty")
    void testFalseIsEmptyWhenStackIsNotEmpty() {
        Stack actual = new Stack(1);
        actual.push(new Object());
        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    @DisplayName("checking that the function - top works correctly")
    void testSuccessTopWithTwoElementInStack() {
        Object expected = new Object();
        Stack actual = new Stack(2);
        actual.push(new Object());
        actual.push(expected);
        Assertions.assertEquals(expected, actual.top());
    }

    @Test
    @DisplayName("checking that the function - top works correctly when stack is empty")
    void testReturnNullTopWhenStackIsEmpty() {
        Stack actual = new Stack(0);
        Assertions.assertNull(actual.top());
    }
}

