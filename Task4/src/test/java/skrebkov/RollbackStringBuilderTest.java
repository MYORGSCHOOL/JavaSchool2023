package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RollbackStringBuilderTest {

    @Test
    @DisplayName("Успешное добавления в конец строки с откатом обратно")
    public void testSuccessAppendWithRollback() {
        String startString = "Старт";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(startString);
        Assertions.assertEquals("СтартКонец",
                rollbackStringBuilder.append("Конец").toString());
        Assertions.assertEquals(startString, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Успешное замещения подмтроки в строке с откатом обратно")
    public void testSuccessReplaceWithRollback() {
        String startString = "Ёжик в тумане";
        RollbackStringBuilder rollbackStringBuilder =
                new RollbackStringBuilder(startString);
        rollbackStringBuilder.replace(0, 4, "Выдра");
        Assertions.assertEquals("Выдра в тумане", rollbackStringBuilder.toString());
        Assertions.assertEquals(startString, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Успешное вставка подстроки в строку с откатом обратно")
    public void testSuccessInsertWithRollback() {
        String startString = "Ёжик в тумане";
        RollbackStringBuilder rollbackStringBuilder =
                new RollbackStringBuilder(startString);
        rollbackStringBuilder.insert(1, "ЁЁЁЁ");
        Assertions.assertEquals("ЁЁЁЁЁжик в тумане", rollbackStringBuilder.toString());
        Assertions.assertEquals(startString, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Успешное переворот строку с откатом обратно")
    public void testSuccessReversWithRollback() {
        String startString = "Ёжик в тумане";
        RollbackStringBuilder rollbackStringBuilder =
                new RollbackStringBuilder(startString);
        rollbackStringBuilder.reverse();
        Assertions.assertEquals((new StringBuilder(startString)).reverse().toString(),
                rollbackStringBuilder.toString());
        Assertions.assertEquals(startString, rollbackStringBuilder.rollback().toString());
    }

    @Test
    @DisplayName("Успешное проверка множественного отката")
    public void testSuccessRollback() {
        String startString = "Старт";
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder(startString);
        for (int i = 0; i < 1000; i++) {
            rollbackStringBuilder.append(Integer.toString(i));
        }
        for (int i = 0; i < 1000; i++) {
            rollbackStringBuilder.rollback();
        }
        Assertions.assertEquals(startString, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест исключения отката при пустом стаке")
    public void testRollbackException() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        Assertions.assertThrowsExactly(RollbackStringBuilderException.class, rollbackStringBuilder::rollback);
    }
}
