package alexenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RollbackStringBuilderTest {

    @Test
    @DisplayName("Вставка строки в строку по индексу")
    public void testInsertStringIntoStringByIndex() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hello").insert(1, "E").insert(3, "E");

        Assertions.assertEquals("HEeEllo", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Перестановка символов в строке задом наперёд")
    public void testReverseSymbolsInStringBackwards() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("123").reverse();

        Assertions.assertEquals("321", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Добавление строки в конец имеющейся строки")
    public void testAppendStringIntoHavingString() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("1").append("2").append("3");

        Assertions.assertEquals("123", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Замена подстроки из указанного диапазона на новую строку")
    public void testReplaceSubstringOnNewString() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("0").append("1").append("3").append("3").append("3");
        rollbackStringBuilder.replace(2, 5, "234");

        Assertions.assertEquals("01234", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Отменить предыдущее действие произведённое с RollbackStringBuilder")
    void testRollbackLatestDo() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("0").append("1").insert(2,"2").reverse().replace(0,2,"777");
        rollbackStringBuilder.rollback().rollback().rollback().rollback();

        Assertions.assertEquals("0",rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Получить собранную RollbackStringBuilder строку")
    void testToString() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("0").append("1").insert(2,"2").reverse().replace(0,3,"777");
        String string = rollbackStringBuilder.toString();

        Assertions.assertEquals("777",string);
    }
}
