package babadzhanov;

/**
 * Интерфейс позволяющий откатывать последние изменения.
 */
public interface RollbackAction {

    /**
     * Метод откатывает последние изменения.
     */
    void rollback();
}
