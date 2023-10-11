package alexenko.exceptions;

public class IllegalCountAnnotationsAfterAllMethodsException extends RuntimeException {
    private static final String MESSAGE = "A test class should not have more than one @AfterAllMethods annotation.";

    public IllegalCountAnnotationsAfterAllMethodsException() {
        super(MESSAGE);
    }
}
