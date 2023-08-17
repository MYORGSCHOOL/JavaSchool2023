package pozdnyakova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.EmptyStackException;

/**
 * Класс тестирования RollbackStringBuilder
 */
public class RollbackStringBuilderTest {
    /**
     * Первая строка для теста
     */
    private static final String FIRST_STRING = "first";
    /**
     * Вторая строка для теста
     */
    private static final String SECOND_STRING = "second";

    @Test
    @DisplayName("Тест отката добавления строки к пустой строке")
    public void testRollbackAppendEmptyString() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(FIRST_STRING);
        Assertions.assertEquals(FIRST_STRING, rollbackStringBuilder.toString());
        rollbackStringBuilder.rollback();
        Assertions.assertEquals("", rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест отката добавления строки к непустой строке")
    public void testRollbackAppendNotEmptyString() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(FIRST_STRING).append(SECOND_STRING);
        Assertions.assertEquals(FIRST_STRING + SECOND_STRING, rollbackStringBuilder.toString());
        rollbackStringBuilder.rollback();
        Assertions.assertEquals(FIRST_STRING, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест отката реверсирования")
    public void testRollbackReverse() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(FIRST_STRING).reverse();
        Assertions.assertEquals("tsrif", rollbackStringBuilder.toString());
        rollbackStringBuilder.rollback();
        Assertions.assertEquals(FIRST_STRING, rollbackStringBuilder.toString());

    }

    @Test
    @DisplayName("Тест отката вставки подстроки по индексу")
    public void testRollbackInsertSubstringAtAddress() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(FIRST_STRING).insert(1, SECOND_STRING);
        Assertions.assertEquals(FIRST_STRING.charAt(0) + SECOND_STRING + FIRST_STRING.substring(1, FIRST_STRING.length()),
                rollbackStringBuilder.toString());
        rollbackStringBuilder.rollback();
        Assertions.assertEquals(FIRST_STRING, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест отката замены подстроки на другую строку")
    public void testRollbackReplace() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append(FIRST_STRING).replace(1, FIRST_STRING.length(), SECOND_STRING);
        Assertions.assertEquals(FIRST_STRING.charAt(0) + SECOND_STRING, rollbackStringBuilder.toString());
        rollbackStringBuilder.rollback();
        Assertions.assertEquals(FIRST_STRING, rollbackStringBuilder.toString());
    }

    @Test
    @DisplayName("Тест на неудачный откат, когда никакой метод не применялся")
    public void testFailRollbackMethodsNoUsed() {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        Assertions.assertThrows(EmptyStackException.class, () -> rollbackStringBuilder.rollback());
    }
}
