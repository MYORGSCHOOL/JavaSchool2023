package dushkina;

/**
 * Класс, реализующий методы класса StringBuilder и откатывающий операции
 */
public class RollbackStringBuilder {
    /**
     * Ссылка на оригинальный StringBuilder для делегирования методов
     */
    private StringBuilder originalBuilder;
    /**
     * Ссылка на стек, реализованный в Task3, для хранения стека операций
     */
    private final Stack stack;

    /**
     * Интерфейс для отката операций
     */
    interface Action {
        void rollback();
    }

    public RollbackStringBuilder() {
        this.originalBuilder = new StringBuilder();
        this.stack = new Stack();
    }

    /**
     * Метод, добавляющий указанную строку s к оригинальному StringBuilder
     * Добавление происходит за счёт делегирования операции классу StringBuilder
     *
     * @param s - строка, которую необходимо добавить
     * @return текущий объект
     */
    public RollbackStringBuilder append(String s) {
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(originalBuilder.length() - s.length(), originalBuilder.length());
            }
        };
        stack.push(action);
        originalBuilder.append(s);
        return this;
    }

    /**
     * Метод, добавляющий строку str в определённое место оригинального StringBuilder
     * Строка вставляется в содержимое этой последовательности в позиции, указанной символом смещение.
     * Добавление происходит за счёт делегирования операции классу StringBuilder
     *
     * @param offset - смещение (индекс, с которого вставляется строка)
     * @param str    - строка, которую необходимо добавить
     * @return текущий объект
     * @throws StringIndexOutOfBoundsException, если offset<0 или offset>длины исходной строки
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.delete(offset, str.length() + offset);
            }
        };
        stack.push(action);
        originalBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод, заменяющий последовательность символов StringBuilder на обратную последовательности
     * Замена происходит за счёт делегирования операции классу StringBuilder
     *
     * @return текущий объект
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
     * Метод, заменяющий символы в подстроке этой последовательности символами в указанной String.
     * Замена происходит за счёт делегирования операции классу StringBuilder
     *
     * @param start - значение, ссылающееся на начальный индекс.
     * @param end   - значение, ссылающееся на конечный индекс
     * @param str   - значение, ссылающееся на строку, которая заменит предыдущее содержимое
     * @return текущий объект
     * @throws StringIndexOutOfBoundsException - если start<0 или end>длины первоначальной строки
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        final String substring = originalBuilder.substring(start, end);
        Action action = new Action() {
            @Override
            public void rollback() {
                originalBuilder.replace(start, str.length() + start, substring);
            }
        };
        stack.push(action);
        originalBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод, откатывающий произведённые операции над строкой
     *
     * @return текущий объект
     * @throws RollbackException - над строкой не производилось операций, стек операций пуст
     */
    public RollbackStringBuilder rollback() {
        if (stack.isEmpty()) {
            throw new RollbackException("Операций не было");
        }
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
