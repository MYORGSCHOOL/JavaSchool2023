package piryazev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {
    @Test
    @DisplayName("test success insert string to empty string")
    void testSuccessInsertOnEmptyString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0, "avadakedavra");
        Assertions.assertEquals("avadakedavra", builder.toString());
    }

    @Test
    @DisplayName("test success rollback after insert")
    void testSuccessRollbackInsert() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0, "avadakedavra");
        builder.rollback();
        Assertions.assertEquals("", builder.toString());
    }

    @Test
    @DisplayName("test success reverse")
    void testSuccessReverseString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Daenerys Targaryen").reverse();
        Assertions.assertEquals("neyragraT syreneaD", builder.toString());
    }

    @Test
    @DisplayName("test success rollback after reverse")
    void testSuccessRollbackReverseString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Daenerys Targaryen").append(" XIII").reverse().rollback();
        Assertions.assertEquals("Daenerys Targaryen XIII", builder.toString());
    }

    @Test
    @DisplayName("test success new append")
    void testSuccessAppend() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Daenerys Targaryen").append(" XIII");
        builder.append(" Mother of dragons")
                .append(" Breaker of worlds");
        Assertions.assertEquals("Daenerys Targaryen XIII Mother of dragons Breaker of worlds",
                builder.toString());
    }

    @Test
    @DisplayName("test success rollback after append")
    void testSuccessRollbackAppend() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Daenerys Targaryen").append(" XIII");
        builder.append(" Mother of dragons")
                .append(" Breaker of worlds")
                .rollback();
        Assertions.assertEquals("Daenerys Targaryen XIII Mother of dragons",
                builder.toString());
    }

    @Test
    @DisplayName("test success replace new string")
    void testSuccessReplace() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Daenerys Targaryen").replace(5, 8, "Snow");
        Assertions.assertEquals("DaeneSnow Targaryen",
                builder.toString());
    }

    @Test
    @DisplayName("test success rollback after replace")
    void testSuccessReplaceRollback() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Daenerys Targaryen").replace(5, 8, "Snow")
                .replace(0, 4, "John")
                .append("No")
                .rollback();
        Assertions.assertEquals("JohneSnow Targaryen",
                builder.toString());
    }
}
