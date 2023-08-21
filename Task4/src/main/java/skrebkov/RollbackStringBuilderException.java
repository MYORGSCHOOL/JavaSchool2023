package skrebkov;

/**
 * Исключение в RollbackStringBuilder
 */
public class RollbackStringBuilderException extends RuntimeException {
    public RollbackStringBuilderException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
