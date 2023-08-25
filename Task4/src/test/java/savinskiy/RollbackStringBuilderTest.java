package savinskiy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RollbackStringBuilderTest {

    @Test
    public void testInsert() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("Kanye");
        rollbackStringBuilder.insert(5, " West");
        assertEquals("Kanye West", rollbackStringBuilder.toString());

        rollbackStringBuilder.rollback();
        assertEquals("Kanye", rollbackStringBuilder.toString());
    }

    @Test
    public void testReverse() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("Kanye");
        rollbackStringBuilder.reverse();
        assertEquals("eynaK", rollbackStringBuilder.toString());

        rollbackStringBuilder.rollback();
        assertEquals("Kanye", rollbackStringBuilder.toString());
    }

    @Test
    public void testAppend() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("Kanye");
        rollbackStringBuilder.append(" Omari ");
        rollbackStringBuilder.append("West");
        assertEquals("Kanye Omari West", rollbackStringBuilder.toString());
    }

    @Test
    public void testReplace() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("Kanye West");
        rollbackStringBuilder.replace(6, 11, "Omari");
        assertEquals("Kanye Omari", rollbackStringBuilder.toString());
    }

    @Test
    public void testRollback() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("Kanye");
        rollbackStringBuilder.append(" Omari ");
        rollbackStringBuilder.append("West");
        rollbackStringBuilder.rollback();
        rollbackStringBuilder.rollback();
        assertEquals("Kanye", rollbackStringBuilder.toString());
    }

    @Test
    public void testExceptions() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("Kanye");
        rollbackStringBuilder.insert(5, " West");
        rollbackStringBuilder.rollback();
        assertEquals("Kanye", rollbackStringBuilder.toString());

        rollbackStringBuilder.rollback();
        assertThrows(IllegalStateException.class, rollbackStringBuilder::rollback);
    }
}
