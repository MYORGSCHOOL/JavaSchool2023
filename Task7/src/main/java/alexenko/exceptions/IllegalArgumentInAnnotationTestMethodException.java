package alexenko.exceptions;

public class IllegalArgumentInAnnotationTestMethodException extends RuntimeException {
    private static final String MESSAGE = "The launch queue number must not be less than one.";

    public IllegalArgumentInAnnotationTestMethodException() {
        super(MESSAGE);
    }
}
