package tsimmer;

/**
 * Анонимный интерфейс для реализации rollback
 */
interface Action {
    void rollback();
}

/**
 * Класс RollbackStringBuilder является реализацией StringBuilder
 */
public class RollbackStringBuilder {

    /**
     * Ссылка на StringBuilder
     */
    private final StringBuilder originalStringBuilder;
    /**
     * Ссылка на стек из 3 задания
     */
    private final Stack stack;

    public RollbackStringBuilder() {
        this.stack = new Stack();
        this.originalStringBuilder = new StringBuilder();
    }

    /**
     * Метод добавляет строку
     *
     * @param str строка, которую нужно добавить
     * @return ссылка на экземпляр класса
     */
    public RollbackStringBuilder append(String str) {
        Action action = () -> originalStringBuilder.delete(
                originalStringBuilder.length() - str.length(),
                originalStringBuilder.length());
        stack.push(action);
        originalStringBuilder.append(str);
        return this;

    }

    /**
     * Метод позволяет вставить строку с указанного индекса
     *
     * @param offset индекс с которого вставляется строка
     * @param str    строка, которую нужно вставить
     * @return ссылка на экземпляр класса
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = () -> originalStringBuilder.delete(offset,
                offset + str.length());
        stack.push(action);
        originalStringBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод осуществляет переворот строки
     *
     * @return ссылка на экземпляр класса
     */
    public RollbackStringBuilder reverse() {
        Action action = () -> originalStringBuilder.reverse();
        stack.push(action);
        originalStringBuilder.reverse();
        return this;
    }

    /**
     * Метод осуществляет замену символов в строке на указанном диапазоне индексов
     *
     * @param start индекс начала
     * @param end   индекс конца
     * @param str   строка, которую необходимо вставить
     * @return ссылка на экземпляр класса
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        Action action = () -> {
            originalStringBuilder.delete(
                    originalStringBuilder.length() - str.length(),
                    originalStringBuilder.length());
            originalStringBuilder.append(str);
        };
        stack.push(action);
        originalStringBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод с помощью которого можно отменить одно изменение StringBuilder
     *
     * @return ссылка на экземпляр класса
     */
    public RollbackStringBuilder rollback() {
        if (stack.isEmpty()) {
            throw new RollbackStringBuilderException("Стек пуст.");
        }
        Object pop = stack.pop();
        if (pop instanceof Action) {
            ((Action) pop).rollback();
        }
        return this;
    }

    /**
     * Переопределение метода toString. Вывод экземпляра класса в виде строки
     *
     * @return строка
     */
    @Override
    public String toString() {
        return originalStringBuilder.toString();
    }
}
