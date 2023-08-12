package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class CustomStringBuilderTest {
    @Test
    @DisplayName("Вставка в непустую строку с корректного индекса")
    public void testSuccessInsertIntoNotEmptyStringCorrectIndex() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234").insert(1, "000");
        Assertions.assertEquals("1000234", builder.toString());
    }

    @Test
    @DisplayName("Вставка в пустую строку с корректного индекса")
    public void testSuccessInsertIntoEmptyStringCorrectIndex() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.insert(0, "000");
        Assertions.assertEquals("000", builder.toString());
    }

    @Test
    @DisplayName("Вставка в непустую строку с некорректного индекса")
    public void testFailInsertIntoNotEmptyStringIncorrectIndex() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> builder.insert(6, "000"));
    }

    @Test
    @DisplayName("Перестановка символов непустой строки в обратном порядке")
    public void testSuccessReverseNotEmptyString() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234").reverse();
        Assertions.assertEquals("4321", builder.toString());
    }

    @Test
    @DisplayName("Перестановка символов пустой строки в обратном порядке")
    public void testFailReverseEmptyString() {
        CustomStringBuilder builder = new CustomStringBuilder();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> builder.reverse());
    }

    @Test
    @DisplayName("Вставка строки в конец пустой строки")
    public void testSuccessAppendIntoEmptyString() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234");
        Assertions.assertEquals("1234", builder.toString());
    }

    @Test
    @DisplayName("Замена символов строки в корректном диапазоне")
    public void testSuccessReplaceWithCorrectIndexes() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234").replace(1, 3, "000");
        Assertions.assertEquals("10004", builder.toString());
    }

    @Test
    @DisplayName("Замена символов строки в некорректном диапазоне")
    public void testFailReplaceWithIncorrectIndexes() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> builder.replace(-1, 3, "000"));
    }

    @Test
    @DisplayName("Откат после вставки новых символов в непустую строку")
    public void testSuccessRollbackAfterAppendIntoNotEmptyString() {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("1234").append("5678").rollback();
        Assertions.assertEquals("1234", builder.toString());
    }

    @Test
    @DisplayName("Откат сразу после инициализации")
    public void testFailRollbackAfterInitialize() {
        CustomStringBuilder builder = new CustomStringBuilder();
        Assertions.assertThrows(EmptyStackException.class, () -> builder.rollback());
    }
}
