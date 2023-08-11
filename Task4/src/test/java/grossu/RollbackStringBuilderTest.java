package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования методов класса RollbackStringBuilder
 */
public class RollbackStringBuilderTest {

    @Test
    @DisplayName("Checking the successful rollback of the operation of inserting a substring into a string")
    void testSuccessInsertAndSuccessInsertRollbackInString() {
        RollbackStringBuilder actual = new RollbackStringBuilder();
        actual.append("111").insert(1, "3");
        String expected = "1311";
        Assertions.assertEquals(expected, actual.toString());
        String expectedRollback = "111";
        Assertions.assertEquals(expectedRollback, actual.rollback().toString());
    }

    @Test
    @DisplayName("Checking for a successful rollback of a string reverse operation")
    void testSuccessReverseStringAndSuccessReverseRollback() {
        RollbackStringBuilder actual = new RollbackStringBuilder();
        actual.append("123").reverse();
        String expected = "321";
        Assertions.assertEquals(expected, actual.toString());
        String expectedRollback = "123";
        Assertions.assertEquals(expectedRollback, actual.rollback().toString());
    }

    @Test
    @DisplayName("Checking for a successful rollback of a string adding operation")
    void testSuccessAppendStringAndSuccessAppendRollback() {
        RollbackStringBuilder actual = new RollbackStringBuilder();
        actual.append("555");
        String expected = "555";
        Assertions.assertEquals(expected, actual.toString());
        String expectedRollback = "";
        Assertions.assertEquals(expectedRollback, actual.rollback().toString());
    }

    @Test
    @DisplayName("Checking for a successful rollback of a substring insertion operation")
    void testSuccessReplaceSubstringAndSuccessReplaceRollback() {
        RollbackStringBuilder actual = new RollbackStringBuilder();
        actual.append("12345").replace(2, 4, "66");
        String expected = "12665";
        Assertions.assertEquals(expected, actual.toString());
        String expectedRollback = "12345";
        Assertions.assertEquals(expectedRollback, actual.rollback().toString());
    }

    @Test
    @DisplayName("Checking that the function rollback works correctly when stack is empty")
    void testGetThrowWhenStackRollBackIsEmpty() {
        RollbackStringBuilder actual = new RollbackStringBuilder();
        Assertions.assertThrows(RollbackException.class, actual::rollback);
    }
}
