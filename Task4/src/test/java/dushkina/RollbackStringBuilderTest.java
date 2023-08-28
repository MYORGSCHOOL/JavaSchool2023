package dushkina;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс - тестирование методов класса RollbackStringBuilder
 */
public class RollbackStringBuilderTest {
    @Test
    @DisplayName("Проверка работы метода append, а также его отката")
    public void testAppend() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Held");//"Held"
        assertEquals("Held", builder.toString());
        assertEquals("", builder.rollback().toString());
    }

    @Test
    @DisplayName("Проверка работы метода insert, а также его отката")
    public void testInsert() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Held");//"Held"
        builder.insert(2, "llo, Wor");//"Hello, World"
        assertEquals("Hello, World", builder.toString());
        assertEquals("Held", builder.rollback().toString());
    }

    @Test
    @DisplayName("Проверка работы метода reverse, а также его отката")
    public void testReverse() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Held");//"Held"
        builder.reverse();//"dleH"
        assertEquals("dleH", builder.toString());
        assertEquals("Held", builder.rollback().toString());
    }

    @Test
    @DisplayName("Проверка работы метода replace, а также его отката")
    public void testReplace() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Held");//"Held"
        builder.replace(2, 4, "llo!");//"Hello!"
        assertEquals("Hello!", builder.toString());
        assertEquals("Held", builder.rollback().toString());
    }

    @Test
    @DisplayName("Проверка работы метода rollback, когда операций не производилось")
    public void testRollback() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        assertThrows(RollbackException.class, () -> builder.rollback());
    }
}
