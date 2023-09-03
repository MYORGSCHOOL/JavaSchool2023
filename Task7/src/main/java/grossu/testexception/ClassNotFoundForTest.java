package grossu.testexception;

/**
 * Класс для выбрасывания исключений в классе {@code TestRunner}
 * при возникновении проблем с тестируемым классом
 */
public class ClassNotFoundForTest extends RuntimeException {
    public ClassNotFoundForTest(String message) {
        super(message);
    }
}
