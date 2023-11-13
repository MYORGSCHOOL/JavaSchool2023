package korolev.Exceptions;

/**
 * Error: missing methods.
 */
public class MissingNecessaryElements extends RuntimeException {
    public MissingNecessaryElements(String massage) {
        super(massage);
    }
}
