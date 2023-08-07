package piryazev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class StackTest {
    @Test
    @DisplayName("Checking that the stack expanded after start size")
    void testSuccessExpandStackAfterStartSize() {
        Stack stack = new Stack();
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
        Assertions.assertEquals(11, stack.getCurrentSize());
    }

    @Test
    @DisplayName("Checking number of objects after push")
    void testNumberOfObjectsAfterPush() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push('2');
        Assertions.assertEquals(2, stack.getCurrentSize());
    }

    @Test
    @DisplayName("Checking the correct exception after popping an object from the stack")
    void testCorrectExceptionAfterPop() {
        Stack stack = new Stack();
        Assertions.assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    @DisplayName("Checking number of objects after pop")
    void testNumberOfObjectsAfterPop() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push("sad");
        stack.pop();
        Assertions.assertEquals(1, stack.getCurrentSize());
    }

    @Test
    @DisplayName("Checking the correct exception after return top object from empty stack")
    void testCorrectExceptionReturnAfterTopStack() {
        Stack stack = new Stack();
        Assertions.assertThrows(NoSuchElementException.class, stack::top);
    }

    @Test
    @DisplayName("Checking the correct object after return top object")
    void testCorrectObjectReturn() {
        Stack stack = new Stack();
        stack.push("7890");
        Assertions.assertEquals("7890", stack.top());
    }

    @Test
    @DisplayName("Checking isEmpty function on True statement")
    void testTrueStatementIsEmpty() {
        Stack stack = new Stack();
        Assertions.assertEquals(Boolean.TRUE, stack.isEmpty());
    }

    @Test
    @DisplayName("Checking isEmpty function on False statement")
    void testFalseStatementIsEmpty() {
        Stack stack = new Stack();
        stack.push(42);
        Assertions.assertEquals(Boolean.FALSE, stack.isEmpty());
    }

    @Test
    @DisplayName("Checking stack after push some objects and after pop it")
    void testStackAfterPushAndPop() {
        Stack stack = new Stack();

        for (int i = 0; i < 7; i++) {
            stack.push(i);
        }

        stack.pop(); //удаляем 6
        stack.pop(); //удаляем 5
        Object expected = "Stack{stackArray=[0, 1, 2, 3, 4, null, null, null, null, null]}";
        Assertions.assertEquals(expected.toString(), stack.toString());
    }
}
