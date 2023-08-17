package piryazev;

/**
 * Класс, реализующий StringBuilder с откатом изменений.
 */
public class RollbackStringBuilder {
    /**
     * Ссылка на оригинальный класс StringBuilder
     */
    private final StringBuilder stringBuilderOriginal;
    /**
     * Стек для хранения изменений для отката
     */
    private final Stack stack;

    /**
     * Анонимный интерфейс отката изменений
     */
    public interface RollbackAction {
        void rollback();
    }

    public RollbackStringBuilder() {
        this.stack = new Stack();
        this.stringBuilderOriginal = new StringBuilder();
    }

    /**
     * Метод вставки строки с заданного места
     *
     * @param offset место откуда вставить новую строку
     * @param str    вставляемая строка
     * @return возвращает новую строку
     */
    public RollbackStringBuilder insert(int offset, String str) {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilderOriginal.delete(offset, offset + str.length());
            }
        };
        this.stringBuilderOriginal.insert(offset, str);
        this.stack.push(action);
        return this;
    }

    /**
     * Метод, отзеркаливающий строку
     *
     * @return новую отзеркаленную строку
     */
    public RollbackStringBuilder reverse() {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilderOriginal.reverse();
            }
        };
        this.stringBuilderOriginal.reverse();
        this.stack.push(action);
        return this;
    }

    /**
     * Метод вставки подстроки в конец строки
     *
     * @param str вставляемая строка
     * @return новую строку
     */
    public RollbackStringBuilder append(String str) {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilderOriginal.delete(stringBuilderOriginal.length() - str.length(),
                        stringBuilderOriginal.length());
            }
        };
        this.stringBuilderOriginal.append(str);
        this.stack.push(action);
        return this;
    }

    /**
     * Метод заменяющий строку с начального заданного значения и конечного заданного значения на новую подстроку
     *
     * @param start старт откуда заменять подстроку
     * @param end   конец заменяемой подстроки
     * @param str   подстрока, на которую заменяется
     * @return новую строку
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        final String originalReplacedString = this.stringBuilderOriginal.toString();
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilderOriginal.delete(0, stringBuilderOriginal.length());
                stringBuilderOriginal.append(originalReplacedString);
            }
        };
        this.stringBuilderOriginal.replace(start, end, str);
        this.stack.push(action);
        return this;
    }

    /**
     * Метод для отката изменения
     *
     * @return возвращает строку без последнего изменения
     */
    public RollbackStringBuilder rollback() {
        if (this.stack.isEmpty()) {
            throw new RollbackException("No operations to rollback");
        }
        Object pop = this.stack.pop();
        if (pop instanceof RollbackAction) {
            ((RollbackAction) pop).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return this.stringBuilderOriginal.toString();
    }
}
