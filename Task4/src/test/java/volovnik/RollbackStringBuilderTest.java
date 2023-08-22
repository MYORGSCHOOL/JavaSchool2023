package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {

    @Test
    @DisplayName("Тест на вставку строки и отмены этого действия")
    public void testSuccessInsertWillRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("text").insert(2, "33");
        Assertions.assertEquals("te33xt", rollbackStringBuilder.toString());
        Assertions.assertEquals("text", rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Тест на переворот строки и отмены этого действия")
    public void testSuccessReverseWillRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("asd").reverse();
        Assertions.assertEquals("dsa", rollbackStringBuilder.toString());
        Assertions.assertEquals("asd", rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Тест на добавление строки и отмены этого действия")
    public void testSuccessAppendWillRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("text").append("123");
        Assertions.assertEquals("text123", rollbackStringBuilder.toString());
        Assertions.assertEquals("text", rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Тест на замену строки и отмены этого действия")
    public void testSuccessReplaceWillRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("text").replace(1, 3, "111");
        Assertions.assertEquals("t111t", rollbackStringBuilder.toString());
        Assertions.assertEquals("text", rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Тест на замену строки и отмены этого действия")
    public void testThrowsExceptionWhenNoRollbackAvailable() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        Assertions.assertThrows(NoRollbackException.class, rollbackStringBuilder::rollback);
    }
}
