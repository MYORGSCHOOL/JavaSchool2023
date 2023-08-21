package babadzhanov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RollbackStringBuilderTest {

    @Test
    @DisplayName("Проверка вставки элемента с указанного индекса")
    public void checkInsertElementToRollbackStringBuilder() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123").insert(0, "-");
        Assertions.assertEquals("-123", builder.toString());
    }

    @Test
    @DisplayName("Проверка вставки элемента с указанного индекса, если индекс выходит за границы стэка")
    public void testExceptionForInsertWithWrongOffset() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> builder.insert(25, "-"));
    }

    @Test
    @DisplayName("Проверка переворота элемента")
    public void checkReverseElementInRollbackStringBuilder() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123").reverse();
        Assertions.assertEquals("321", builder.toString());
    }

    @Test
    @DisplayName("Проверка добавления элемента")
    public void checkAppendElementToRollbackStringBuilder() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123");
        Assertions.assertEquals("123", builder.toString());
    }

    @Test
    @DisplayName("Проверка замены элементов, в указанном диапозоне")
    public void checkReplaceElementToRollbackStringBuilder() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123").replace(0, 3, "===");
        Assertions.assertEquals("===", builder.toString());
    }

    @Test
    @DisplayName("Проверка замены элементов, если задан некорректный диапозон")
    public void testExceptionForReplaceWithWrongIndexBound() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> builder.replace(89, 90, "==="));
    }

    @Test
    @DisplayName("Проверка отката последнего изменения")
    public void checkRollbackElementInRollbackStringBuilder() {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("123").reverse().replace(0, 3, "===").rollback();
        Assertions.assertEquals("321", builder.toString());
    }
}