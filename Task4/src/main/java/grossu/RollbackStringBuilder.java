package grossu;

/**
 * Класс реализующий методы класса StringBuilder с возможностью отката операций
 */
public class RollbackStringBuilder {
    /**
     * Поле для делегирования методов классу StringBuilder
     */
    private final StringBuilder stringBuilder;
    /**
     * Поле для хранения стека операций
     */
    private final Stack stack;

    /**
     * Interface для отката операций
     */
    interface Action {
        void rollback();
    }

    public RollbackStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stack = new Stack();
    }

    /**
     * Метод вставляет передаваемую строку в исходную с указанного смещения от начала
     * Вставка происходит за счет делегирования этой операции классу StringBuilder и использования его метода insert
     * В случае если offset<0 или offset>лины исходной строки StringBuilder выбросит исключение
     *
     * @param offset - смещение, позиция с которой происходит вставка строки
     * @param str    - строка, которая будет вставлена в исходную
     * @return текущий объект
     * @throws StringIndexOutOfBoundsException, если offset некорректный
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(offset, offset + str.length());
            }
        };
        stack.push(action);
        stringBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод для того, чтобы перевернуть текущую строку, которая находится в StringBuilder
     * Разворот строки происходит за счет делегирования этой операции классу StringBuilder и использования его метода reverse
     *
     * @return текущий объект
     */
    public RollbackStringBuilder reverse() {
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.reverse();
            }
        };
        stack.push(action);
        stringBuilder.reverse();
        return this;
    }

    /**
     * Метод для того, чтобы к текущей строке с конца добавить передаваемую строку
     * Добавление происходит за счет делегирования этой операции классу StringBuilder и использования его метода append
     *
     * @param str - строка, которая будет добавлена с конца к текущей строке
     * @return текущий объект
     */
    public RollbackStringBuilder append(String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(stringBuilder.length() - str.length(),
                        stringBuilder.length());
            }

        };
        stack.push(action);
        stringBuilder.append(str);
        return this;
    }

    /**
     * Метод для того, чтобы переписать подстроку в текущей строке на передаваемую строку
     * Подстрока начинается с индекса start и продолжается до end-1
     * Замена подстроки строкой происходит за счет делегирование этой операции классу StringBuilder и использования его метода replace
     * В случае, если start<0 или end>длины первоначальной строки StringBuilder выбросит исключение
     *
     * @param start - начальный индекс, включительно
     * @param end   - конечный индекс, end-1
     * @param str   - строка на замену подстроки
     * @return текущий объект
     * @throws StringIndexOutOfBoundsException - при некорректных start и end
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        final String oldStr = stringBuilder.substring(start, end);
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(start, start + str.length());
                stringBuilder.insert(start, oldStr);
            }
        };
        stack.push(action);
        stringBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод для отката произведенных над строкой операций
     * Если над строкой еще не проводилось операций, то будет выброшено исключение
     *
     * @return текущий объект
     * @throws RollbackException - при пустом стеке
     */
    public RollbackStringBuilder rollback() {
        if (stack.isEmpty()) {
            throw new RollbackException("No operations to rollback");
        }
        Object pop = stack.pop();
        if (pop instanceof Action) {
            ((Action) pop).rollback();
        }
        return this;
    }

    /**
     * Возвращает текущее состояние StringBuilder в формате String
     *
     * @return текущую строку в StringBuilder
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}


