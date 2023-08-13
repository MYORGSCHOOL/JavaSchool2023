package druzhinin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class RollbackStringBuilderTest {
    @Test
    @DisplayName("Тест на успешное создание RollbackStringBuilder с аргументом в виде строки")
    public void testRollbackStringBuilderCreationSuccessWithStringParameter() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("Hello");
        String expected = "Hello";
        Assertions.assertEquals(expected, rollbackStringBuilder.toString());
    }
    @Test
    @DisplayName("Тест на успешное создание RollbackStringBuilder без аргументов")
    public void testRollbackStringBuilderCreationSuccessWithoutParameters() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        String expected = "";
        Assertions.assertEquals(expected, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на успешное добавление ненулевой строки в исходную строку с помощью insert")
    public void testInsertNotNullStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("HelloMyFriend");
        String expected = "HelloMyDearFriend";
        Assertions.assertEquals(expected, rollbackStringBuilder.insert(7, "Dear").toString());
    }

    @Test
    @DisplayName("Тест на успешное добавление нулевой строки в исходную строку с помощью insert")
    public void testInsertNullStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("HelloMyFriend");
        String expected = "HelloMynullFriend";
        Assertions.assertEquals(expected, rollbackStringBuilder.insert(7, null).toString());
    }

    @Test
    @DisplayName("Тест на получение исключения StringIndexOutOfBoundsException" +
            " при указании offset меньше нуля при использовании insert")
    public void testStringIndexOutOfBoundsExceptionWhenInsertWithOffsetLessThanZero() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("HelloMyFriend");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> rollbackStringBuilder.insert(-1, "Dear"));
    }

    @Test
    @DisplayName("Тест на получение исключения StringIndexOutOfBoundsException" +
            " при указании offset больше текущей длины строки при использовании insert")
    public void testStringIndexOutOfBoundsExceptionWhenInsertWithOffsetMoreThanStringLength() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("HelloMyFriend");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> rollbackStringBuilder.insert(100, "Dear"));
    }

    @Test
    @DisplayName("Тест успешное инвертирование пустой строки с помощью reverse")
    public void testReverseEmptyStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        Assertions.assertEquals("", rollbackStringBuilder.reverse().toString());
    }

    @Test
    @DisplayName("Тест на успешное инвертирование непустой строки с помощью reverse")
    public void testReverseNotEmptyStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        String expected = "54321";
        Assertions.assertEquals(expected, rollbackStringBuilder.reverse().toString());
    }

    @Test
    @DisplayName("Тест на успешное добавление ненулевой строки с помощью append")
    public void testAppendNotNullStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        String expected = "123454321";
        Assertions.assertEquals(expected, rollbackStringBuilder.append("4321").toString());
    }

    @Test
    @DisplayName("Тест на успешное добавление нулевой строки с помощью append")
    public void testAppendNullStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        String expected = "12345null";
        Assertions.assertEquals(expected, rollbackStringBuilder.append(null).toString());
    }

    @Test
    @DisplayName("Тест на успешную замену подстроки на ненулевую строку с помощью replace")
    public void testReplaceWithNotNullStringSuccess() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        String expected = "1-----5";
        Assertions.assertEquals(expected, rollbackStringBuilder.replace(1, 4, "-----").toString());
    }

    @Test
    @DisplayName("Тест на получение исключения NullPointerException" +
            " при попытке заменить подстроку нулевой строкой с помощью replace")
    public void testNullPointerExceptionWhenReplaceWithNullString() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        Assertions.assertThrows(NullPointerException.class,
                () -> rollbackStringBuilder.replace(1, 4, null));
    }

    @Test
    @DisplayName("Тест на получение исключения StringIndexOutOfBoundsException" +
            " при попытке заменить подстроку с аргументом start меньше нуля с помощью replace")
    public void testStringIndexOutOfBoundsExceptionWhenReplaceWithStartIndexLessThanZero() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> rollbackStringBuilder.replace(-1, 4, "-----"));
    }

    @Test
    @DisplayName("Тест на получение исключения StringIndexOutOfBoundsException" +
            " при попытке заменить подстроку с аргументом start больше текущей длины строки с помощью replace")
    public void testStringIndexOutOfBoundsExceptionWhenReplaceWithStartIndexMoreThanStringLength() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> rollbackStringBuilder.replace(6, 7, "-----"));
    }

    @Test
    @DisplayName("Тест на получение исключения StringIndexOutOfBoundsException" +
            " при попытке заменить подстроку с аргументом start больше аргумента end с помощью replace")
    public void testStringIndexOutOfBoundsExceptionWhenReplaceWithStartIndexMoreThanEndIndex() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        Assertions.assertThrows(StringIndexOutOfBoundsException.class,
                () -> rollbackStringBuilder.replace(3, 2, "-----"));
    }

    @Test
    @DisplayName("Тест на получение исключения EmptyStackException" +
            " при попытке отката действия с помощью rollback при пустом стеке откатываемых действий")
    public void testEmptyStackExceptionWhenRollbackWithEmptyCancelActionStack() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("12345");
        Assertions.assertThrows(EmptyStackException.class,
                rollbackStringBuilder::rollback);
    }

    @Test
    @DisplayName("Тест на успешный откат действия, совершенного методом insert")
    public void testRollbackSuccessAfterInsert() {
        String initialString = "HelloMyFriend";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(initialString);
        Assertions.assertEquals(initialString,
                rollbackStringBuilder.insert(5, "Dear").rollback().toString());
    }

    @Test
    @DisplayName("Тест на успешный откат действия, совершенного методом reverse")
    public void testRollbackSuccessAfterReverse() {
        String initialString = "HelloMyFriend";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(initialString);
        Assertions.assertEquals(initialString,
                rollbackStringBuilder.reverse().rollback().toString());
    }

    @Test
    @DisplayName("Тест на успешный откат действия, совершенного методом append")
    public void testRollbackSuccessAfterAppend() {
        String initialString = "HelloMyFriend";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(initialString);
        Assertions.assertEquals(initialString,
                rollbackStringBuilder.append("Hello").rollback().toString());
    }

    @Test
    @DisplayName("Тест на успешный откат действия, совершенного методом replace для замены всей строки целиком")
    public void testRollbackSuccessAfterReplaceAllString() {
        String initialString = "HelloMyFriend";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(initialString);
        Assertions.assertEquals(initialString,
                rollbackStringBuilder.replace(0, 100, "NewString").rollback().toString());
    }

    @Test
    @DisplayName("Тест на успешный откат действия, совершенного методом replace для замены одного символа в строке")
    public void testRollbackSuccessAfterReplaceOneChar() {
        String initialString = "HelloMyFriend";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(initialString);
        Assertions.assertEquals(initialString,
                rollbackStringBuilder.replace(0, 0, "HHHHH").rollback().toString());
    }
}
