package proskurina;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.EmptyStackException;

/**
 * Класс, тестирующий коллекцию Stack
 */
class StackTest {
    
    @Test
    @DisplayName("Тестируется OutOfMemory при иниализации Integer.MAX_VALUE")
    void testOutOfMemoryOnInitialization() {
        Assertions.assertThrows(OutOfMemoryError.class, () -> new Stack(Integer.MAX_VALUE));
    }
    
    @Test
    @DisplayName("Тестируется IllegalArgumentException при иниализации отрацательным значением")
    void testIllegalArgumentExceptionOnInitialization() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Stack(-1));
    }
    
    @Nested
    @DisplayName("Тесты для пустого стека")
    class WhenEmptyStack {
        private Stack stack;
        
        @BeforeEach
        void initializeTwoSizeStack() {
            stack = new Stack(2);
        }
        
        @Test
        @DisplayName("Тестируется пустота стека")
        void testIsEmptyStackSuccess() {
            Assertions.assertTrue(stack.isEmpty());
        }
        
        @Test
        @DisplayName("Тестируется EmptyStackException при вызове pop()")
        void testPopFromEmptyStackException() {
            Assertions.assertThrows(EmptyStackException.class, () -> stack.pop());
        }
        
        @Test
        @DisplayName("Тестируется EmptyStackException при вызове top()")
        void testTopFromEmptyStackException() {
            Assertions.assertThrows(EmptyStackException.class, () -> stack.top());
        }
        
        @Nested
        @DisplayName("Тесты для стека с 1 элементом")
        class WhenOneObjectAdded {
            Object testObj;
            
            @BeforeEach
            void pushOneObj() {
                testObj = "тестовый объект";
                stack.push(testObj);
            }
            
            @Test
            @DisplayName("Тестируется на не пустоту")
            void testIsNotEmpty() {
                Assertions.assertFalse(stack.isEmpty());
            }
            
            @Test
            @DisplayName("Тестируется корректный возврат значения из top()")
            void testEqualsToTestObjectAfterTop() {
                Assertions.assertEquals(testObj, stack.top());
            }
            
            @Test
            @DisplayName("Тестируется корректный возврат значения из pop()")
            void testEqualsToTestObjectAfterPop() {
                Assertions.assertEquals(testObj, stack.pop());
            }
            
            @Test
            @DisplayName("Тестируется корректный возврат значения из push()")
            void testEqualsToTestObjectAfterPush() {
                Assertions.assertEquals(testObj, stack.push(testObj));
            }
            
            @Test
            @DisplayName("Тестируется удаление элемента после pop()")
            void testIsEmptyAfterPop() {
                stack.pop();
                Assertions.assertTrue(stack.isEmpty());
            }
            
            @Test
            @DisplayName("Тестируется не пустота после top()")
            void testIsNotEmptyAfterTop() {
                stack.top();
                Assertions.assertFalse(stack.isEmpty());
            }
        }
    }
}