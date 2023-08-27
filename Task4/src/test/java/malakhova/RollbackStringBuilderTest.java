package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {
    @Test
    @DisplayName("Тест на проверку добавления строки")
    public void testSuccessAppendStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi");
        Assertions.assertEquals("Hi", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на проверку метода, который разворачивает строку в обратном направлении")
    public void testSuccessReverseStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hello")
                .reverse();
        Assertions.assertEquals("olleH", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на проверку вставки новой строки в имеющуюся")
    public void testSuccessInsertStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi.")
                .append("Im fine")
                .insert(3, "How are you?");
        Assertions.assertEquals("Hi.How are you?Im fine", rollbackStringBuilder.toString());
        Assertions.assertThrows(IllegalArgumentException.class, () -> rollbackStringBuilder.insert(-3, "Hi"));
    }

    @Test
    @DisplayName("Тест на проверку вставки новой строки и удаления части предыдущей")
    public void testSuccessReplaceStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi.Hi")
                .append("How are you?")
                .replace(3, 5, "Hello.");
        Assertions.assertEquals("Hi.Hello.How are you?", rollbackStringBuilder.toString());
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> rollbackStringBuilder.replace(3, 1, "Hi"));

    }

    @Test
    @DisplayName("Тест на проверку метода, выполняющего отмену добавления строки")
    public void testSuccessRollbackAppendStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        Assertions.assertThrows(IllegalArgumentException.class, () -> rollbackStringBuilder.rollback());
        rollbackStringBuilder.append("Hi")
                .rollback();
        Assertions.assertEquals("", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на проверку метода, выполняющего отмену изменения направления строки")
    public void testSuccessRollbackReverseStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi")
                .reverse()
                .rollback();
        Assertions.assertEquals("Hi", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на проверку метода, выполняющего отмену вставки новой строки в имеющуюся")
    public void testSuccessRollbackInsertStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi.")
                .append("Im fine")
                .insert(3, "How are you?")
                .rollback();
        Assertions.assertEquals("Hi.Im fine", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на проверку метода, выполняющего отмену удаления части строки и вставки новой на ее место")
    public void testSuccessRollbackReplaceStringToRollbackStringBuilder() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi.")
                .append("How are you?")
                .replace(3, 5, "Hello.")
                .rollback();
        Assertions.assertEquals("Hi.How are you?", rollbackStringBuilder.toString());
    }
}
