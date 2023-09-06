package alexenko;

/**
 * Класс RollbackStringBuilder реализует паттерн делегирования над классом StringBuilder.
 */
public class RollbackStringBuilder {

    /**
     * Интерфейс Action позволяет реализовывать анонимные классы.
     * Каждый класс реализует свой undo метод для метода StringBuilder.
     */
    private interface Action {
        void rollback();
    }

    /**
     * Ссылка на оригинальный StringBuilder.
     */
    private final StringBuilder originalBuilder;

    /**
     * Stack, который хранит экземпляры анонимных классов, с соответствующими undo методами.
     */
    private final Stack actions;

    private static final int START_SIZE_STACK = 10;

    /**
     * Конструктор. Инициализирует поля.
     * Задаёт начальный размер стека 10.
     */
    public RollbackStringBuilder() {
        this.originalBuilder = new StringBuilder();
        this.actions = new Stack(START_SIZE_STACK);
    }

    /**
     * Метод осуществляет вставку строки в указанную позицию. Смещение идёт от начала строки.
     *
     * @param offset смещение от начала строки
     * @param str    вставляемая строка
     * @return ссылка на тот объект RollbackStringBuilder, который вызвал метод
     */
    public RollbackStringBuilder insert(int offset, String str) {
        actions.push(new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(offset, offset + str.length());
            }
        });
        originalBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод изменяет порядок символом в строке так, что она становится задом наперёд.
     *
     * @return ссылка на тот объект RollbackStringBuilder, который вызвал метод
     */
    public RollbackStringBuilder reverse() {
        actions.push(new Action() {
            @Override
            public void rollback() {
                originalBuilder.reverse();
            }
        });
        originalBuilder.reverse();
        return this;
    }

    /**
     * Метод добавляет строку в конец той строки, которая уже есть в RollbackStringBuilder.
     *
     * @param str добавляемая строка
     * @return ссылка на тот объект RollbackStringBuilder, который вызвал метод
     */
    public RollbackStringBuilder append(String str) {
        actions.push(new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(originalBuilder.length() - str.length(), originalBuilder.length());
            }
        });
        originalBuilder.append(str);
        return this;
    }

    /**
     * Метод заменяет символы строки из указанного диапазона на новую строку.
     *
     * @param start индекс начала диапазона
     * @param end   индекс конца диапазона
     * @param str   вставляемая строка
     * @return ссылка на тот объект RollbackStringBuilder, который вызвал метод
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        actions.push(new Action() {
            private final String subString = originalBuilder.substring(start, end);

            @Override
            public void rollback() {
                originalBuilder.replace(start, start + str.length(), subString);
            }
        });

        originalBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод реализует отмену предыдущего действия со строкой.
     *
     * @return ссылка на тот объект RollbackStringBuilder, который вызвал метод
     */
    public RollbackStringBuilder rollback() {
        if (actions.isEmpty()) {
            return this;
        }
        Object action = actions.pop();
        if (action instanceof Action) {
            ((Action) action).rollback();
        }
        return this;
    }

    /**
     * Метод реализует строковое представление RollbackStringBuilder.
     *
     * @return строковое представление RollbackStringBuilder
     */
    @Override
    public String toString() {
        return originalBuilder.toString();
    }
}

