package korolev.Exceptions;

/**
 * Error: Exceeding the number of methods.
 */
public class IteratingOverAnnotations extends RuntimeException {
    public IteratingOverAnnotations(String massage) {
        super(massage);
    }
}
