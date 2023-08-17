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
        stack.push(action);
        stringBuilderOriginal.insert(offset, str);
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
        stack.push(action);
        stringBuilderOriginal.reverse();
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
        stack.push(action);
        stringBuilderOriginal.append(str);
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
        final String originalReplacedString = stringBuilderOriginal.toString();
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilderOriginal.delete(0, stringBuilderOriginal.length());
                stringBuilderOriginal.append(originalReplacedString);
            }
        };
        stack.push(action);
        stringBuilderOriginal.replace(start, end, str);
        return this;
    }

    /**
     * Метод для отката изменения
     *
     * @return возвращает строку без последнего изменения
     */
    public RollbackStringBuilder rollback() {
        if (stack.isEmpty()) {
            throw new RollbackException("No operations to rollback");
        }
        Object pop = stack.pop();
        if (pop instanceof RollbackAction) {
            ((RollbackAction) pop).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return stringBuilderOriginal.toString();
    }
}
