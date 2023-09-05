package koroliov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RollBackStringBuilderTest {
    RollBackStringBuilder builder = new RollBackStringBuilder();
    StringBuilder realBuilder = new StringBuilder();
    @BeforeEach
    public void prepareDate() {
        builder.append("Hello");
        builder.append(" World!");
        realBuilder.append("Hello");
        realBuilder.append(" World!");
    }

    @Test
    void testAppend() {
        assertEquals(realBuilder.toString(), builder.toString());
    }

    @Test
    void testInsert() {
        builder.insert(3, "O");
        realBuilder.insert(3, "O");
        System.out.println(builder.toString());
        assertEquals(realBuilder.toString(), builder.toString());
    }

    @Test
    void testReplace() {
        builder.replace(0, 3, "O");
        realBuilder.replace(0, 3, "O");
        assertEquals(realBuilder.toString(), builder.toString());
    }

    @Test
    void testReverse() {
        builder.reverse();
        realBuilder.reverse();
        assertEquals(realBuilder.toString(), builder.toString());
    }

    @Test
    void testRollback() {
        builder.append(" Lol!");
        builder.rollback();
        assertEquals("Hello World!", builder.toString());
    }

    @Test
    void testToString() {
        assertEquals("Hello World!", builder.toString());
    }
}
