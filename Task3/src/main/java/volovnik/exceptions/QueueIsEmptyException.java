package volovnik.exceptions;

public class QueueIsEmptyException extends RuntimeException {
    public QueueIsEmptyException() {
        super("Очередь пустая");
    }
}
