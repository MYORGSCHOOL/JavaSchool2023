package tumbaev;

/**
 * An interface for creating actions that rollback previous operation(s).
 */
@FunctionalInterface
public interface RollbackAction {
    void rollback();
}
