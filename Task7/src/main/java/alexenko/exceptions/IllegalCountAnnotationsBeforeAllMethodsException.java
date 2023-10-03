package alexenko.exceptions;

public class IllegalCountAnnotationsBeforeAllMethodsException extends RuntimeException {
    private static final String MESSAGE = "A test class should not have more than one @BeforeAllMethods annotation.";

    public IllegalCountAnnotationsBeforeAllMethodsException() {
        super(MESSAGE);
    }
}
