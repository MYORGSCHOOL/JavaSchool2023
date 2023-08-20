package kashuba;

import proskurina.exceptions.EmptyRollbackStackException;

/**
 * Класс, реализующий методы StringBuilder с методом отката
 */
public class RollbackStringBuilder {
    /**
     * Ссылка на оригинальный StringBuilder
     */
    private final StringBuilder stringBuilder;
    /**
     * Ссылка на Stack from task3
     */
    private final Stack stack;

    public RollbackStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stack = new Stack();
    }

    /**
     * Метод, который вставляет переданную строку с указанного индекса
     *
     * @param offset индекс с которого вставляется строка
     * @param str    строка, которая будет вставлена
     * @return ссылка на экземпляр класса RollbackStringBuilder
     * @throws StringIndexOutOfBoundsException если offset некорректный
     */
    public RollbackStringBuilder insert(int offset, String str) {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.delete(offset, offset + str.length());
            }
        };
        stringBuilder.insert(offset, str);
        stack.push(action);
        return this;
    }

    /**
     * Метод, который переворачивает строку
     *
     * @return ссылка на экземпляр класса RollbackStringBuilder
     */
    public RollbackStringBuilder reverse() {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.reverse();
            }
        };
        stringBuilder.reverse();
        stack.push(action);
        return this;
    }

    /**
     * Метод, который добавляет строку в конец
     *
     * @param str строка, которая будет добавлена
     * @return ссылка на экземпляр класса RollbackStringBuilder
     */
    public RollbackStringBuilder append(String str) {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.delete(stringBuilder.length() - str.length(),
                        stringBuilder.length());
            }
        };
        stringBuilder.append(str);
        stack.push(action);
        return this;
    }

    /**
     * Метод, который заменяет подстроку с start до end
     *
     * @param start индекс начала замены
     * @param end   индекс конца замены
     * @param str   строка на которую происходит замена
     * @return ссылка на экземпляр класса RollbackStringBuilder
     * @throws StringIndexOutOfBoundsException если start и end некорректны
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        final String oldStr = stringBuilder.substring(start, end);
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.replace(start, end, oldStr);
            }
        };
        stringBuilder.replace(start, end, str);
        stack.push(action);
        return this;
    }

    /**
     * Метод, который откатывает последнее действие
     *
     * @return ссылка на экземпляр класса RollbackStringBuilder
     * @throws EmptyRollbackStackException если стек изменений пуст
     */
    public RollbackStringBuilder rollback() {
        if (stack.isEmpty()) {
            throw new RollbackException("Операций не было");
        }
        Object pop = stack.pop();
        if (pop instanceof RollbackAction) {
            ((RollbackAction) pop).rollback();
        }
        return this;
    }

    /**
     * Метод, который StringBuilder возвращает в виде строки
     *
     * @return строку
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
