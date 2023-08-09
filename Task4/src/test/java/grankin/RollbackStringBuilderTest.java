package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {

    @Test
    @DisplayName("Вставить элемент")
    public void testInsertString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0,"Hello");
        Assertions.assertEquals("Hello", builder.toString());
    }

    @Test
    @DisplayName("Вставить элемент и вернуть на шаг назад")
    public void testInsertAndRollbackString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("START");

        builder.insert(5," Hello");
        builder.rollback();

        Assertions.assertEquals("START", builder.toString());
    }


    @Test
    @DisplayName("Перевернуть строку")
    public void testReversString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0,"Hello");
        builder.reverse();
        Assertions.assertEquals("olleH", builder.toString());
    }

    @Test
    @DisplayName("Перевернуть строку и вернуть на шаг назад")
    public void testReversAndRollbackString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("START");

        builder.reverse();
        builder.rollback();

        Assertions.assertEquals("START", builder.toString());
    }

    @Test
    @DisplayName("Добавить в конец строки")
    public void testAppendString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0, "START");
        builder.append(" Hello");
        Assertions.assertEquals("START Hello", builder.toString());
    }

    @Test
    @DisplayName("Добавить в конец строки и вернуть на шаг назад")
    public void testAppendAndRollbackString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0, "START");
        builder.append(" Hello");
        builder.rollback();
        Assertions.assertEquals("START", builder.toString());
    }

    @Test
    @DisplayName("Заменить подстроку")
    public void testInsertReplaceString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0,"Hello");
        builder.replace(1,5, "i");
        Assertions.assertEquals("Hi", builder.toString());
    }

    @Test
    @DisplayName("Заменить подстроку и вернуть на шаг назад")
    public void testReplaceAndRollbackString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("START");

        builder.replace(0,5," Hello");
        builder.rollback();

        Assertions.assertEquals("START", builder.toString());
    }

    @Test
    @DisplayName("Удалить символ по индексу")
    public void testDeleteCharAtString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0,"Hello");
        builder.deleteCharAt(0);
        Assertions.assertEquals("ello", builder.toString());
    }

    @Test
    @DisplayName("Удалить символ по индексу и вернуть на шаг назад")
    public void testDeleteCharAtAndRollbackString() {

        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.insert(0,"Hello");
        builder.deleteCharAt(0);
        builder.rollback();
        Assertions.assertEquals("Hello", builder.toString());
    }

    @Test
    @DisplayName("Шаг назад")
    public void testRollback() {

        RollbackStringBuilder builder = new RollbackStringBuilder();

        builder.append("START");
        builder.insert(5, " INSERT").append("New string")
                .reverse().replace(0, 6, ")ELIMS")
                .deleteCharAt(3);

        builder.rollback().rollback();
        builder.rollback().rollback().rollback();

        Assertions.assertEquals("START", builder.toString());
    }

    @Test
    @DisplayName("Нет шагов назад")
    public void testRollbackNoSteps() {

        RollbackStringBuilder builder = new RollbackStringBuilder();

        builder.rollback().rollback().rollback();

        Assertions.assertEquals("", builder.toString());
    }

}
