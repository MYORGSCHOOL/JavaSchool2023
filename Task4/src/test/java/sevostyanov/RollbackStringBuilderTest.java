package sevostyanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс тестирующий RollbackStringBuilder
 */
public class RollbackStringBuilderTest {
    private final String testS = "Здесь что то должно быть";
    private RollbackStringBuilder rollbackStringBuilder;

    @BeforeEach
    void setUp() {
        rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(testS);
    }

    @Test
    @DisplayName("RollbackStackException при отсутствии изменений")
    void testEmptyRollbackStackExceptionWhenNoChangesMade() {
        rollbackStringBuilder.rollback();
        Assertions.assertThrows(RollbackStackException.class,
                () -> rollbackStringBuilder.rollback());
    }

    @Test
    @DisplayName("Корректность rollback после добавления")
    void testCorrectRollbackAfterAppend() {
        rollbackStringBuilder.append(testS);
        Assertions.assertEquals(testS, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Корректность rollback после полной замены")
    void testCorrectRollbackAfterFullReplace() {
        rollbackStringBuilder.replace(0, testS.length(), "replace");
        Assertions.assertEquals(testS, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Корректность rollback после замены 1 символа")
    void testCorrectRollbackAfterOneCharReplace() {
        rollbackStringBuilder.replace(0, 1, "1");
        Assertions.assertEquals(testS, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Корректность rollback после вставки строки")
    void testCorrectRollbackAfterInsert() {
        rollbackStringBuilder.insert(0, "insert");
        Assertions.assertEquals(testS, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Корректность rollback после разворота строки")
    void testCorrectRollbackAfterReverse() {
        rollbackStringBuilder.reverse();
        Assertions.assertEquals(testS, rollbackStringBuilder.rollback().toString());
    }
}
