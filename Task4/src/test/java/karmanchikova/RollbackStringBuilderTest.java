package karmanchikova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {
    @Test
    @DisplayName("Тест на проверку добавления строки")
    void testSuccessAppendToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4");
        Assertions.assertEquals("4", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку добавления строки со смещением")
    void testSuccessInsertToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4").insert(0, "fvd");
        Assertions.assertEquals("fvd4", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку добавления строки со смещением, если неверно указано смещение")
    void testExceptionInsertToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> rsb.insert(4, "vfg"));

    }

    @Test
    @DisplayName("Тест на проверку переворачивания строки")
    void testSuccessReverseToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4321").reverse();
        Assertions.assertEquals("1234", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку замены указанной строки")
    void testSuccessReplaceToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4321").replace(0, 2, "dfg");
        Assertions.assertEquals("dfg21", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку замены указанной строки, если неверно указаны индексы")
    void testExceptionReplaceToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4321");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> rsb.replace(6, 8, "=="));
    }

    @Test
    @DisplayName("Тест на проверку отмены операции Reverse")
    void testSuccessReverseRollbackToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4321").replace(0, 2, "df").reverse().rollback();
        Assertions.assertEquals("df21", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку отмены операции Replace")
    void testSuccessReplaceRollbackToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4321").replace(0, 2, "df").rollback();
        Assertions.assertEquals("4321", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку отмены операции Insert")
    void testSuccessInsertRollbackToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4").insert(0, "fvd").rollback();
        Assertions.assertEquals("4", rsb.toString());
    }

    @Test
    @DisplayName("Тест на проверку отмены операции Append")
    void testSuccessAppendRollbackToRollbackStringBuilder() {
        RollbackStringBuilder rsb = new RollbackStringBuilder();
        rsb.append("4").rollback();
        Assertions.assertEquals("", rsb.toString());
    }
}