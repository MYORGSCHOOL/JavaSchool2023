package tumbaev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RollbackStringBuilderTest {

    @Test
    @DisplayName("Should throw exception if there is no operations to rollback")
    void testExceptionIfNoOperationsToRollback() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        assertThrows(RollbackException.class, rsb::rollback);
    }

    @Test
    @DisplayName("Rollback of insert should bring back original string")
    void testRollbackOfInsertShouldBringBackOriginalString() {
        RollbackStringBuilder rsb = new RollbackStringBuilder()
                .append("1234")
                .insert(2, "INSERT");
        assertEquals("12INSERT34", rsb.toString());
        assertEquals("1234", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Rollback of reverse should bring back original string")
    void testRollbackOfReverseShouldBringBackOriginalString() {
        RollbackStringBuilder rsb = new RollbackStringBuilder()
                .append("1234")
                .reverse();
        assertEquals("4321", rsb.toString());
        assertEquals("1234", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Rollback of append should bring back original string")
    void testRollbackOfAppendShouldBringBackOriginalString() {
        RollbackStringBuilder rsb = new RollbackStringBuilder()
                .append("1234");
        assertEquals("1234", rsb.toString());
        assertEquals("", rsb.rollback().toString());
    }

    @Test
    @DisplayName("Rollback of replace should bring back original string")
    void testRollbackOfReplaceShouldBringBackOriginalString() {
        RollbackStringBuilder rsb = new RollbackStringBuilder()
                .append("1234")
                .replace(1, 3, "BC");
        assertEquals("1BC4", rsb.toString());
        assertEquals("1234", rsb.rollback().toString());
    }
}