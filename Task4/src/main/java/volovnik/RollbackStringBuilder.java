package volovnik;

/**
 * Класс для работы со StringBuilder, поддерживающий откат действия
 */
public class RollbackStringBuilder {

    private final StringBuilder stringBuilder;
    private final Stack stack;

    public RollbackStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stack = new Stack();
    }

    /**
     * Метод позволяет вставить строку на определённую позицию в исходной
     * @param offset начало вставки
     * @param str строка
     * @return ссылка на объект
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = () -> stringBuilder.delete(offset, offset + str.length());
        stack.push(action);
        stringBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод выполняет переворот строки
     * @return ссылка на объект
     */
    public RollbackStringBuilder reverse() {
        Action action = stringBuilder::reverse;
        stack.push(action);
        stringBuilder.reverse();
        return this;
    }

    /**
     * Метод добавляет новую строку к исходной
     * @param str добавляемая строка
     * @return ссылка на объект
     */
    public RollbackStringBuilder append(String str) {
        Action action = () -> stringBuilder.delete(stringBuilder.length() - str.length(), stringBuilder.length());
        stack.push(action);
        stringBuilder.append(str);
        return this;
    }

    /**
     * Метод выполняет замену части строки по заданным индексам
     * @param start индекс старта
     * @param end индекс конца
     * @param str строка для замены
     * @return ссылка на объект
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        String savedString = stringBuilder.toString();
        Action action = () -> {
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(savedString);
        };
        stack.push(action);
        stringBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод осуществляет откат на 1 действие
     * @return ссылка на объект
     * @throws NoRollbackException нет обратных действий
     */
    public RollbackStringBuilder rollback() {
        if (stack.isEmpty()) {
            throw new NoRollbackException("Нет обратных действий");
        }
        Object action = stack.pop();
        if (action instanceof Action) {
            ((Action) action).rollback();
        }
        return this;
    }

    /**
     * Метод возвращает текущую строку
     * @return текущая строка
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
