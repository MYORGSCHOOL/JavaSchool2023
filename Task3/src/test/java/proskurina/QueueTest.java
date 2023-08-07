package proskurina;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

/**
 * Класс, тестирующий коллекцию Queue
 */
class QueueTest {
    
    @Test
    @DisplayName("Тестируется OutOfMemory при иниализации Integer.MAX_VALUE")
    void testOutOfMemoryOnInitialization() {
        Assertions.assertThrows(OutOfMemoryError.class, () -> new Queue(Integer.MAX_VALUE));
    }
    
    @Test
    @DisplayName("Тестируется IllegalArgumentException при иниализации отрацательным значением")
    void testIllegalArgumentExceptionOnInitialization() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Queue(-1));
    }
    
    @Nested
    @DisplayName("Тесты для пустой очереди")
    class WhenEmpty {
        private Queue queue;
        
        @BeforeEach
        void initializeTwoSizeQueue() {
            queue = new Queue(2);
        }
        
        @Test
        @DisplayName("Тестируется пустота очереди")
        void testIsEmptyStackSuccess() {
            Assertions.assertTrue(queue.isEmpty());
        }
        
        @Test
        @DisplayName("Тестируется NoSuchElementException при вызове dequeue()")
        void testDequeueFromEmptyQueueNoSuchElementException() {
            Assertions.assertThrows(NoSuchElementException.class, () -> queue.dequeue());
        }
        
        @Test
        @DisplayName("Тестируется NoSuchElementException при вызове top()")
        void testTopFromEmptyQueueNoSuchElementException() {
            Assertions.assertThrows(NoSuchElementException.class, () -> queue.top());
        }
        
        @Nested
        @DisplayName("Тесты для проверки корректного расширения очереди")
        class WhenNeedToGrow {
            
            @Test
            @DisplayName("Тестируется корректность последовательности полсе расширения без dequeue")
            void testCorrectSequenceAfterGrowWithoutUsedDequeue() {
                queue.enqueue(1);
                queue.enqueue(2);
                queue.enqueue(3);
                
                Assertions.assertEquals(1, queue.dequeue());
                Assertions.assertEquals(2, queue.dequeue());
                Assertions.assertEquals(3, queue.dequeue());
            }
            
            @Test
            @DisplayName("Тестируется корректность последовательности полсе расширения c dequeue")
            void testCorrectSequenceAfterGrowAfterDequeue() {
                queue.enqueue(1);
                queue.enqueue(2);
                queue.dequeue();
                queue.enqueue(3);
                queue.enqueue(4);
                
                Assertions.assertEquals(2, queue.dequeue());
                Assertions.assertEquals(3, queue.dequeue());
                Assertions.assertEquals(4, queue.dequeue());
            }
        }
        
        @Nested
        @DisplayName("Тесты для очереди с 1 элементом")
        class WhenOneObjectAdded {
            Object testObj;
            
            @BeforeEach
            void enqueueOneObj() {
                testObj = "тестовый объект";
                queue.enqueue(testObj);
            }
            
            @Test
            @DisplayName("Тестируется на не пустоту")
            void testIsNotEmpty() {
                Assertions.assertFalse(queue.isEmpty());
            }
            
            @Test
            @DisplayName("Тестируется корректный возврат значения из top()")
            void testEqualsToTestObjectAfterTop() {
                Assertions.assertEquals(testObj, queue.top());
            }
            
            @Test
            @DisplayName("Тестируется корректный возврат значения из dequeue()")
            void testEqualsToTestObjectAfterDequeue() {
                Assertions.assertEquals(testObj, queue.dequeue());
            }
            
            @Test
            @DisplayName("Тестируется корректный возврат значения из enqueue()")
            void testEqualsToTestObjectAfterEnqueue() {
                Assertions.assertEquals(testObj, queue.enqueue(testObj));
            }
            
            @Test
            @DisplayName("Тестируется удаление элемента после dequeue()")
            void testIsEmptyAfterDequeue() {
                queue.dequeue();
                Assertions.assertTrue(queue.isEmpty());
            }
            
            @Test
            @DisplayName("Тестируется не пустота после top()")
            void testIsNotEmptyAfterTop() {
                queue.top();
                Assertions.assertFalse(queue.isEmpty());
            }
        }
    }
}