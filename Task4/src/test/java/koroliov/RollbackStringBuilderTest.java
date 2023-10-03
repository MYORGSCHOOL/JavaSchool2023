package koroliov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {
    private RollbackStringBuilder rbBd = new RollbackStringBuilder("Hello world!");

    @Test
    @DisplayName("A test for adding a substring.")
    void testAppend() {
        this.rbBd.append(" I like java!");
        Assertions.assertEquals("Hello world! I like java!", this.rbBd.toString());
    }

    @Test
    @DisplayName("Test adding a substring by index.")
    void testInsert() {
        this.rbBd.insert(1, "###");
        Assertions.assertEquals("H###ello world!", this.rbBd.toString());
    }

    @Test
    @DisplayName("Test replacing a string at the specified indexes with a new substring.")
    void testReplace() {
        this.rbBd.replace(2, 5, "#");
        Assertions.assertEquals("He# world!", this.rbBd.toString());
    }

    @Test
    @DisplayName("String reversal test.")
    void testReverse() {
        this.rbBd.reverse();
        Assertions.assertEquals("!dlrow olleH", this.rbBd.toString());
    }

    @Test
    @DisplayName("Test how to revert a row to a previous change.")
    void testRollback() {
        this.rbBd.append(" I like Java!");
        this.rbBd.rollback();
        Assertions.assertEquals("Hello world!", this.rbBd.toString());
    }

    @Test
    @DisplayName("Test returning a string from a RollBackStringBuilder.")
    void testToString() {
        Assertions.assertEquals("Hello world!", this.rbBd.toString());
    }
}
