package karmanchikova;

import java.util.EmptyStackException;

/**
 * Реализация интерфейса для отмены изменений
 */
interface Action {
    void rollback();
}

/**
 * Класс реализующий свой StringBuilder с возможным откатом операций
 */
public class RollbackStringBuilder {
    private final StringBuilder originalBuilder;
    private final Stack stack;

    public RollbackStringBuilder() {
        this.originalBuilder = new StringBuilder();
        this.stack = new Stack();
    }

    /**
     * Метод для добавления со смещением
     *
     * @param offset смещение, с которого будет вставлена строка
     * @param str    строка
     * @return ссылка на этот экземпляр
     */
    public RollbackStringBuilder insert(int offset, String str) throws StringIndexOutOfBoundsException {
        Action action = () -> originalBuilder.delete(offset, offset + str.length());
        originalBuilder.insert(offset, str);
        stack.push(action);
        return this;
    }

    /**
     * Метод для добавления стороки
     *
     * @param str строка
     * @return ссылка на этот экземпляр
     */
    public RollbackStringBuilder append(String str) {
        Action action = () -> originalBuilder.delete(originalBuilder.length() - str.length(),
                originalBuilder.length());
        originalBuilder.append(str);
        stack.push(action);
        return this;
    }

    /**
     * Метод для переворачивания строки
     *
     * @return ссылка на этот экземпляр
     */
    public RollbackStringBuilder reverse() {
        Action action = originalBuilder::reverse;
        originalBuilder.reverse();
        stack.push(action);
        return this;
    }

    /**
     * Метод для заменя указанной строки на новую строку
     *
     * @param start стартовый индекс строки для замены
     * @param end   конечный индекс строки для замены
     * @param str   строка
     * @return ссылка на этот экземпляр
     */
    public RollbackStringBuilder replace(int start, int end, String str) throws StringIndexOutOfBoundsException {
        final String repl = originalBuilder.substring(start, end);
        Action action = () -> originalBuilder.replace(start, end, repl);
        originalBuilder.replace(start, end, str);
        stack.push(action);
        return this;
    }

    /**
     * Метод для откаты операций
     *
     * @return ссылка на этот метод
     */
    public RollbackStringBuilder rollback() {
        if (originalBuilder.isEmpty()) {
            throw new EmptyStackException();
        }
        Object action = stack.pop();
        if (action instanceof Action) {
            ((Action) action).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return originalBuilder.toString();
    }
}