package kashuba;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RollbackStringBuilderTest {

    @Test
    @DisplayName("Проверка rollback после применения метода insert")
    void testCheckingTheRollbackAfterApplyingTheInsertMethod() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("12345").insert(1, "5");
        assertEquals("152345", rsb.toString());
        assertEquals("12345", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Проверка rollback после применения метода reverse")
    void testCheckingTheRollbackAfterApplyingTheReverseMethod() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("12345").reverse();
        assertEquals("54321", rsb.toString());
        assertEquals("12345", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Проверка rollback после применения метода append")
    void testCheckingTheRollbackAfterApplyingTheAppendMethod() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("12345");
        assertEquals("12345", rsb.toString());
        assertEquals("", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Проверка rollback после применения метода reverse")
    void testCheckingTheRollbackAfterApplyingTheReplaceMethod() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("12345").replace(1, 4, "999");
        assertEquals("19995", rsb.toString());
        assertEquals("12345", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Проверка rollback при пустом стеке")
    void testRollbackCheckWithEmptyStack() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        assertThrows(RollbackException.class, rsb::rollback);
    }
}
