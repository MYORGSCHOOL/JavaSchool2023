package grankin;

/**
 * Nсключение
 * Неверное количество аннотаций Before или After
 */
public class IllegalCountBeforeOrAfterAnnotationsException extends Exception {
    private String message = "";

    public IllegalCountBeforeOrAfterAnnotationsException() {
    }

    public IllegalCountBeforeOrAfterAnnotationsException(String message) {
        this.message = message;
    }
}
