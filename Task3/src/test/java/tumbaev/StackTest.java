package tumbaev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackTest {

    @Test
    @DisplayName("IllegalArgumentException if creating stack with negative size")
    void testIllegalArgumentExceptionWhenCreateStackWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new Stack(-1));
    }

    @Test
    @DisplayName("OutOfMemoryError if creating stack with too large size")
    void testOutOfMemoryErrorWhenCreateStackWithTooLargeSize() {
        assertThrows(OutOfMemoryError.class, () -> new Stack(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("OutOfMemoryError when push too many elements")
    void testOutOfMemoryErrorWhenPushTooManyElements() {
        Stack stack = new Stack();
        assertThrows(OutOfMemoryError.class, () -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                stack.push(i);
            }
        });
    }

    @Test
    @DisplayName("Push element to stack with size 0")
    void testCanPushElementsToStackWithZeroSize() {
        Stack stack = new Stack(0);
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test
    @DisplayName("Push more elements than initial stack size")
    void testCanPushMoreElementsThanInitialSize() {
        Stack stack = new Stack(1);
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.top());
    }

    @Test
    @DisplayName("Top element from empty stack should be null")
    void testTopEmptyStackShouldBeNull() {
        Stack stack = new Stack();
        assertNull(stack.top());
    }

    @Test
    @DisplayName("Top element from stack multiple times should be idempotent")
    void testTopStackMultipleTimesShouldBeIdempotent() {
        Stack stack = new Stack();
        stack.push(1);
        for (int i = 0; i < 3; i++) {
            assertEquals(1, stack.top());
        }
    }

    @Test
    @DisplayName("Pop element from empty stack should throw EmptyStackException")
    void testPopEmptyStackShouldThrowEmptyStackException() {
        Stack stack = new Stack();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("Pop element from stack should remove the element")
    void testPopStackShouldRemoveTheElement() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    @DisplayName("Is empty should return true if stack is empty")
    void testIsEmptyShouldReturnTrueIfStackIsEmpty() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Stack should become empty after popping all elements")
    void testStackShouldBecomeEmptyAfterPoppingAllElements() {
        Stack stack = new Stack();
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}