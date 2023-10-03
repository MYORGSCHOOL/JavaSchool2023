package alexenko.exceptions;

public class IllegalCountAnnotationsOnMethod extends RuntimeException {

    public static final String MESSAGE = "The number of annotations for a method " +
            "exceeds the maximum number of annotations allowed.";

    public IllegalCountAnnotationsOnMethod() {
        super(MESSAGE);
    }
}
