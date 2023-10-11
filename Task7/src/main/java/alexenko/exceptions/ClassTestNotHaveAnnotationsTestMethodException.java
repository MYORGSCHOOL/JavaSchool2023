package alexenko.exceptions;

public class ClassTestNotHaveAnnotationsTestMethodException extends RuntimeException {
    private static final String MESSAGE = "The test class must have at least one method with the @TestMethod annotation.";

    public ClassTestNotHaveAnnotationsTestMethodException() {
        super(MESSAGE);
    }
}
