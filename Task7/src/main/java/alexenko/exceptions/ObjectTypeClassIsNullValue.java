package alexenko.exceptions;

public class ObjectTypeClassIsNullValue extends RuntimeException {
    private static final String MESSAGE = "An object of type Class has a null value.";

    public ObjectTypeClassIsNullValue() {
        super(MESSAGE);
    }
}
