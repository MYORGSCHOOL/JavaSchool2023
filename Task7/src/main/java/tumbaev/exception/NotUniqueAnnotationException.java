package tumbaev.exception;

/**
 * Thrown when in one class there are multiple methods marked with annotation which should only be applied to one method per class
 */
public class NotUniqueAnnotationException extends RuntimeException {
    public NotUniqueAnnotationException(String message) {
        super(message);
    }
}
