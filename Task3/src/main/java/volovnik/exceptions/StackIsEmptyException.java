package volovnik.exceptions;

public class StackIsEmptyException extends RuntimeException {
    public StackIsEmptyException() {
        super("Стек пустой");
    }
}
