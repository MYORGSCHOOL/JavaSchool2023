package tsimmer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class RollbackStringBuilderTest {

    @Test
    @DisplayName("Тестирование добавления строки")
    public void testSuccessAppendString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("1230");
        Assertions.assertEquals("1230", builder.toString());
    }

    @Test
    @DisplayName("Тестирование вставки в строку с указанного индекса")
    public void testSuccessInsertString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("1230").insert(2, "56");
        Assertions.assertEquals("125630", builder.toString());
    }

    @Test
    @DisplayName("Тестирование переворота строки")
    public void testSuccessReverceString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("1230").reverse();
        Assertions.assertEquals("0321", builder.toString());
    }

    @Test
    @DisplayName("Тестирование замены символов на указанном диапазоне")
    public void testSuccessReplaceString() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("1230").replace(1, 3, "89");
        Assertions.assertEquals("1890", builder.toString());
    }

    @Test
    @DisplayName("Тестирование отмены одного изменения")
    public void testSuccessRollback() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("1230").replace(1, 3, "89").insert(3, "657");
        builder.rollback();
        Assertions.assertEquals("1890", builder.toString());
    }

}
