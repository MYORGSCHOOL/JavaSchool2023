package pozdnyakova;

/**
 * Класс, реализующий StringBuilder с возможностью отката изменений
 */
public class RollbackStringBuilder {
    /**
     * оригинальный StringBuilder для делегирования
     */
    private final StringBuilder originalBuilder;
    /**
     * Стек для сохранения действий, необходимых для отката изменений
     */
    private final Stack stack;

    /**
     * интерфейс для отката изменений
     */
    interface Action {
        /**
         * метод для отката прошлой произведенной операции
         */
        void rollback();
    }

    public RollbackStringBuilder() {
        this.stack = new Stack();
        this.originalBuilder = new StringBuilder();
    }

    /**
     * Метод для вставки подстроки по указанному индексу
     *
     * @param offset индекс, перед которым вставляется подстрока
     * @param str    подстрока, которую необходимо вставить
     * @return измененный объект RollbackStringBuilder
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(offset, offset + str.length());
            }
        };
        stack.push(action);
        originalBuilder.insert(offset, str);
        return this;

    }

    /**
     * Метод для реверсирования RollbackStringBuilder
     *
     * @return измененный объект RollbackStringBuilder
     */
    public RollbackStringBuilder reverse() {
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.reverse();
            }
        };
        stack.push(action);
        originalBuilder.reverse();
        return this;

    }

    /**
     * Метод для добавления строки
     *
     * @param str добавляемая строка
     * @return измененный объект RollbackStringBuilder
     */
    public RollbackStringBuilder append(String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(originalBuilder.length() - str.length(), originalBuilder.length());
            }
        };
        stack.push(action);
        originalBuilder.append(str);
        return this;

    }

    /**
     * Метод для замены подстроки другой строкой
     *
     * @param start начальный индекс подстроки
     * @param end   конечный индекс подстроки
     * @param str   строка, которая будет вставлена вместо подстроки от start до end
     * @return измененный объект RollbackStringBuilder
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        String change = originalBuilder.substring(start, end);
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(start, start + str.length());
                originalBuilder.insert(start, change);

            }
        };
        stack.push(action);
        originalBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод для отката предыдущего изменения
     *
     * @return измененный объект RollbackStringBuilder
     */
    public RollbackStringBuilder rollback() {
        Object pop = stack.pop();
        if (pop instanceof Action) {
            ((Action) pop).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return originalBuilder.toString();
    }
}
