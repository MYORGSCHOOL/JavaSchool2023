package proskurina;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import proskurina.exceptions.EmptyRollbackStackException;


/**
 * Класс, тестирующий RollbackStringBuilder
 */
class RollbackStringBuilderTest {
    private RollbackStringBuilder rollbackStringBuilder;
    private final String testStr = "тестовая строка";
    
    @BeforeEach
    void setUp() {
        rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(testStr);
    }
    
    @Test
    @DisplayName("Тестируется EmptyRollbackStackException при отсутствии изменений")
    void testEmptyRollbackStackExceptionWhenNoChangesMade() {
        rollbackStringBuilder.rollback();
        Assertions.assertThrows(EmptyRollbackStackException.class,
                () -> rollbackStringBuilder.rollback());
    }
    
    @Test
    @DisplayName("Тестируется корректность rollback после добавления")
    void testCorrectRollbackAfterAppend() {
        rollbackStringBuilder.append(testStr);
        Assertions.assertEquals(testStr, rollbackStringBuilder.rollback().toString());
    }
    
    @Test
    @DisplayName("Тестируется корректность rollback после полной замены")
    void testCorrectRollbackAfterFullReplace() {
        rollbackStringBuilder.replace(0, testStr.length(), "replace");
        Assertions.assertEquals(testStr, rollbackStringBuilder.rollback().toString());
    }
    
    @Test
    @DisplayName("Тестируется корректность rollback после замены 1 символа")
    void testCorrectRollbackAfterOneCharReplace() {
        rollbackStringBuilder.replace(0, 1, "1");
        Assertions.assertEquals(testStr, rollbackStringBuilder.rollback().toString());
    }
    
    @Test
    @DisplayName("Тестируется корректность rollback после вставки строки")
    void testCorrectRollbackAfterInsert() {
        rollbackStringBuilder.insert(0, "insert");
        Assertions.assertEquals(testStr, rollbackStringBuilder.rollback().toString());
    }
    
    @Test
    @DisplayName("Тестируется корректность rollback после разворота строки")
    void testCorrectRollbackAfterReverse() {
        rollbackStringBuilder.reverse();
        Assertions.assertEquals(testStr, rollbackStringBuilder.rollback().toString());
    }
}