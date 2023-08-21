package babadzhanov;

/**
 * Класс реализующий методы класса StringBuilder на основе делегирования,
 * с возможностью отката операций
 */
public class RollbackStringBuilder {

    /**
     * Первоначальный размер расширяемого стэка
     */
    private static final int STACK_SIZE = 14;

    /**
     * Поле для делегирования методов классу StringBuilder
     */
    private final StringBuilder originalBuilder;
    /**
     * Поле для хранения операций в стэке
     */
    private final Stack stack;

    public RollbackStringBuilder() {
        this.stack = new Stack(STACK_SIZE);
        this.originalBuilder = new StringBuilder();
    }

    /**
     * Метод вставляет строку с заданного места
     *
     * @param offset место откуда вставить строку
     * @param s      строка
     * @return экземпляр текущего объекта
     * @throws StringIndexOutOfBoundsException если передан некорректный offset
     */
    public RollbackStringBuilder insert(int offset, String s) throws StringIndexOutOfBoundsException {
        RollbackAction rollbackAction = () -> originalBuilder.delete(offset, offset + (s.length() - 1));
        stack.push(rollbackAction);
        originalBuilder.insert(offset, s);
        return this;
    }

    /**
     * Метод переворачивает строку
     *
     * @return экземпляр текущего объекта
     */
    public RollbackStringBuilder reverse() {
        RollbackAction rollbackAction = () -> originalBuilder.reverse();
        stack.push(rollbackAction);
        originalBuilder.reverse();
        return this;
    }

    /**
     * Метод вставляет строку
     *
     * @param s строка
     * @return экземпляр текущего объекта
     */
    public RollbackStringBuilder append(String s) {
        RollbackAction rollbackAction = () -> originalBuilder.delete(originalBuilder.length() - s.length(), originalBuilder.length());
        stack.push(rollbackAction);
        originalBuilder.append(s);
        return this;
    }

    /**
     * Метод для замены подстроки другой строкой
     *
     * @param start начальный индекс подстроки
     * @param end   конечный индекс подстроки
     * @param s     строка
     * @return экземпляр текущего объекта
     * @throws StringIndexOutOfBoundsException если переданы некоректные индексы
     */
    public RollbackStringBuilder replace(int start, int end, String s) throws StringIndexOutOfBoundsException {
        String change = originalBuilder.substring(start, end);
        RollbackAction rollbackAction = () -> {
            originalBuilder.delete(start, start + s.length());
            originalBuilder.insert(start, change);

        };
        stack.push(rollbackAction);
        originalBuilder.replace(start, end, s);
        return this;
    }

    /**
     * Метод откатывает последние изменения.
     */
    public RollbackStringBuilder rollback() {
        Object pop = stack.pop();
        if (pop instanceof RollbackAction) {
            ((RollbackAction) pop).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return originalBuilder.toString();
    }
}
