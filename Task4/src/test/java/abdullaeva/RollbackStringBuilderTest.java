package abdullaeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {
    @Test
    @DisplayName("Тест для проверки успешной вставки строки со смещением")
    public void testInsert() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        String test = "string for test";
        rollbackStringBuilder.insert(0, test);
        Assertions.assertEquals(test, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки отката после вставки строки со смещением insert")
    public void testInsertRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.insert(0, "string for test");
        rollbackStringBuilder.rollback();
        Assertions.assertEquals("", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки успешного отображения строки в обратном порядке")
    public void testReverse() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("string for test");
        rollbackStringBuilder.reverse();
        String test = "tset rof gnirts";
        Assertions.assertEquals(test, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки отката после отображения строки в обратном порядке reverse")
    public void testReverseRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("string for test");
        rollbackStringBuilder.reverse();
        rollbackStringBuilder.rollback();
        String test = "string for test";
        Assertions.assertEquals(test, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки успешной вставки строки в конец строки")
    public void testAppend() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("string for test");
        rollbackStringBuilder.append(" 1");
        String test = "string for test 1";
        Assertions.assertEquals(test, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки отката после вставки строки в конец строки append")
    public void testAppendRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("string for test");
        rollbackStringBuilder.rollback();
        Assertions.assertEquals("", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки успешной замены подстроки в указанном месте строки")
    public void testReplace() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("string for test");
        rollbackStringBuilder.replace(7, 10, "success");
        String test = "string success test";
        Assertions.assertEquals(test, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки отката после замены подстроки в указанном месте replace")
    public void testReplaceRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("string for test");
        rollbackStringBuilder.replace(7, 10, "success");
        rollbackStringBuilder.rollback();
        String test = "string for test";
        Assertions.assertEquals(test, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест для проверки исключения вызова rollback при отсутствии операций над строкой")
    public void testRollbackException() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        Assertions.assertThrows(RollbackException.class, rollbackStringBuilder::rollback);
    }
}
