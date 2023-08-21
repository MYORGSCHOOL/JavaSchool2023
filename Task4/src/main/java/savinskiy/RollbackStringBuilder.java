package savinskiy;

/**
 * Класс Стрингбилдер с возможностью отката действий
 */
public class RollbackStringBuilder {
    /**
     * Стрингбилдер
     */
    private final StringBuilder stringBuilder;
    /**
     * Стэк для хранения действий
     */
    private final Stack actionStack;

    public RollbackStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.actionStack = new Stack();
    }

    /**
     * Метод вставляет строку str в указанный индекс offset
     *
     * @param offset индекс по которому нужно вставить str
     * @param str строка для вставки
     * @return stringBuilder после вставки строки
     */
    public RollbackStringBuilder insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        actionStack.push(new InsertAction(offset, str.length()));
        return this;
    }

    /**
     * Метод возвращает обратную последовательность символов
     *
     * @return stringBuilder в обратном порядке
     */
    public RollbackStringBuilder reverse() {
        stringBuilder.reverse();
        actionStack.push(new ReverseAction());
        return this;
    }

    /**
     * Метод вставляет str в конец последовательности stringBuilder
     *
     * @param str строка для вставки
     * @return stringBuilder после добавления строки
     */
    public RollbackStringBuilder append(String str) {
        stringBuilder.append(str);
        actionStack.push(new AppendAction(str.length()));
        return this;
    }

    /**
     * Метод заменяет символы в подстроке на символы из указаной строки
     *
     * @param start первый символ
     * @param end конечный символ
     * @param str строка для замены
     * @return stringBuilder после замены подстроки
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        stringBuilder.replace(start, end, str);
        actionStack.push(new ReplaceAction(start, str.length()));
        return this;
    }

    /**
     * Метод отменяет последнее действие
     *
     * @return обновленный RollbackStringBuilder
     */
    public RollbackStringBuilder rollback() {
        if (actionStack.isEmpty()) {
            throw new IllegalStateException("There is nothing to rollback");
        }
        Action previousAction = (Action) actionStack.pop();
        previousAction.rollback(stringBuilder);
        return this;
    }

    /**
     * Метод возращает содержимое stringBuilder в виде строки
     *
     * @return строку с содержимым stringBuilder
     */
    public String toString() {
        return stringBuilder.toString();
    }

    /**
     * Класс для вставки строки
     */
    private static class InsertAction implements Action {
        private final int offset;
        private final int length;

        public InsertAction(int offset, int length) {
            this.offset = offset;
            this.length = length;
        }

        @Override
        public void rollback(StringBuilder stringBuilder) {
            stringBuilder.delete(offset, offset + length);
        }
    }

    /**
     * Класс для разворота строки
     */
    private static class ReverseAction implements Action {
        @Override
        public void rollback(StringBuilder stringBuilder) {
            stringBuilder.reverse();
        }
    }

    /**
     * Класс для добавления текста к строке
     */
    private static class AppendAction implements Action {
        private final int length;

        public AppendAction(int length) {
            this.length = length;
        }

        @Override
        public void rollback(StringBuilder stringBuilder) {
            stringBuilder.delete(stringBuilder.length() - length, stringBuilder.length());
        }
    }

    /**
     * Класс для замены подстроки в строке
     */
    private static class ReplaceAction implements Action {
        private final int start;
        private final int length;

        public ReplaceAction(int start, int length) {
            this.start = start;
            this.length = length;
        }

        @Override
        public void rollback(StringBuilder stringBuilder) {
            stringBuilder.delete(start, start + length);
            stringBuilder.insert(start, stringBuilder.substring(start, start + length));
        }
    }
}