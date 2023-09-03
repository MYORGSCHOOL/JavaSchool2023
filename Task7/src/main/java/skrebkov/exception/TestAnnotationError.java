package skrebkov.exception;

/**
 * Ошибка связанная с аннотациями тестируемого класса
 */
public class TestAnnotationError extends RuntimeException {
    public TestAnnotationError(String message) {
        super(message);
    }
}
