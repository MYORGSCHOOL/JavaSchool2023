package skrebkov;

/**
 * StringBuilder с возможностью откатить изменения
 */
public class RollbackStringBuilder {
    /**
     * Оригинальный StringBuilder
     */
    private final StringBuilder stringBuilder;
    /**
     * Стэк для хранения действий для возвращения StringBuilder до предыдущего состояния
     */
    private final Stack rollbackSteps;

    /**
     * Конструктор с инициализацией пустого стэка и StringBuilder
     */
    public RollbackStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.rollbackSteps = new Stack();
    }

    /**
     * Конструктор с пустым стеком и инициализация stringBuilder со строкой
     *
     * @param startString изначальная строка для StringBuilder
     */
    public RollbackStringBuilder(String startString) {
        this.stringBuilder = new StringBuilder(startString);
        this.rollbackSteps = new Stack();
    }

    /**
     * Вставка в строку со смещением.
     * Анонимный класс реализует метод удаляющий втсавленные символы из StringBuilder
     *
     * @param offset число символов для смещения
     * @param str    строка для вставки
     * @return текущий объект
     */
    public RollbackStringBuilder insert(Integer offset, String str) {
        this.stringBuilder.insert(offset, str);
        this.rollbackSteps.push(new Rollback() {
            @Override
            public void rollback() {
                final int start = offset;
                final int end = offset + str.length();
                stringBuilder.delete(start, end);
            }
        });
        return this;
    }

    /**
     * Переворачиваем текущую строку.
     * Анонимный класс в стек реализующий метод переворачивающий строку
     *
     * @return текущий объект
     */
    public RollbackStringBuilder reverse() {
        this.stringBuilder.reverse();
        this.rollbackSteps.push(new Rollback() {
            @Override
            public void rollback() {
                stringBuilder.reverse();
            }
        });
        return this;
    }

    /**
     * Добавляем строку в конец строки в StringBuilder.
     * Анонимный класс реализует метод для удаления добавленных символов из конца строки
     *
     * @param str строка для добавления
     * @return текущий объект
     */
    public RollbackStringBuilder append(String str) {
        this.stringBuilder.append(str);
        this.rollbackSteps.push(new Rollback() {
            @Override
            public void rollback() {
                final int stringLength = str.length();
                final int start = stringBuilder.length() - stringLength;
                final int end = stringBuilder.length();
                stringBuilder.delete(start, end);
            }
        });
        return this;
    }

    /**
     * Заменить часть строки на введенную строку.
     * Анонимный класс реализует обратный метод заменяющий новую часть строки, старой
     *
     * @param start начала сегмента для замены
     * @param end   конец сегмента для замены
     * @param str   страка на которую заменим
     * @return текущий объект
     */
    public RollbackStringBuilder replace(Integer start, Integer end, String str) {
        String replacedString = this.stringBuilder.substring(start, end);
        this.stringBuilder.replace(start, end, str);
        this.rollbackSteps.push(new Rollback() {
            @Override
            public void rollback() {
                final int newSubStringEnd = start + str.length();
                stringBuilder.replace(start, newSubStringEnd, replacedString);
            }
        });
        return this;
    }

    /**
     * Откатывает изменения в StringBuilder
     *
     * @return текущий объект
     */
    public RollbackStringBuilder rollback() {
        if (rollbackSteps.isEmpty()) {
            throw new RollbackStringBuilderException("Empty rollback stack");
        }
        ((Rollback) this.rollbackSteps.pop()).rollback();
        return this;
    }

    /**
     * Возвращает строку хранящуюся в StringBuilder
     *
     * @return строка в StringBuilder
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    /**
     * Интерфейс для реализации анонимных классов
     */
    interface Rollback {
        void rollback();
    }
}


