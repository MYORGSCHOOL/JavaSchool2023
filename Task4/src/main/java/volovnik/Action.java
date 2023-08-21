package volovnik;

/**
 * Функциональный интерфейс для реализации метода rollback
 */
@FunctionalInterface
public interface Action {
    void rollback();
}
