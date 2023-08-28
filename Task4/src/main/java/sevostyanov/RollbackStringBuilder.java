package sevostyanov;
/**
 * Класс, реализующий StringBuilder с возможностью отмены изменений.
 */
public class RollbackStringBuilder {

    /**
     * Ссылка на оригинальный StringBuilder
     */
    private final StringBuilder stringBuilder;

    /**
     *
     */
    private final Stack stackRollback;

    /**
     * Начальный размер стека
     */
    private final static int  INITIAL_SIZE_ARRAY = 5;
    public RollbackStringBuilder() {
        stringBuilder = new StringBuilder();
        stackRollback = new Stack(INITIAL_SIZE_ARRAY);
    }

    /**
     * Метод insert(int offset, string str) позволяет вставить строку (str) в указанную позицию (offset) в объекте StringBuilder.
     * Внутри метода создается объект класса rollbackaction, который реализует интерфейс rollbackaction с методом rollback().
     * Метод rollback() вызывается при необходимости отката операции и удаляет вставленную строку из объекта StringBuilder.
     * Созданный объект добавляется в стек (stack).
     *
     * @param offset индекс элемента с которого вставляется строка
     * @param str    строка которая будет вставлена
     * @return ссылка на данный экземпляр
     */
    public RollbackStringBuilder insert(int offset, String str) {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.delete(offset, offset + str.length());
            }
        };
        stringBuilder.insert(offset, str);
        stackRollback.push(action);
        return this;
    }

    /**
     * Метод reverse() изменяет порядок символов в объекте StringBuilder на обратный и возвращает сам объект StringBuilder.
     * Внутри метода создается экземпляр класса rollbackAction, который содержит логику отката изменения - в данном случае, вызывается метод reverse() у объекта StringBuilder.
     * Затем вызывается метод reverse() у самого объекта StringBuilder и экземпляр rollbackAction добавляется в стек отката stackRollback.
     * В итоге метод возвращает текущий объект StringBuilder.
     *
     * @return ссылку на этот экземпляр
     */
    public RollbackStringBuilder reverse() {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.reverse();
            }
        };
        stringBuilder.reverse();
        stackRollback.push(action);
        return this;
    }

    /**
     * Он предназначен для добавления строки (представленной параметром str) к объекту StringBuilder.
     * Для каждого добавления строки он создает новый объект класса rollbackaction, который является интерфейсом.
     * Метод rollback() удаляет добавленную строку, вернув объект StringBuilder к состоянию до добавления.
     * Этот объект rollbackaction помещается в стек stackRollback, чтобы быть использованным при необходимости отмены операции добавления строки.
     *
     * @param str строка которую мы добваляем
     * @return ссылка на текущий экземпляр
     */
    public RollbackStringBuilder append(String str) {
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                stringBuilder.delete(stringBuilder.length() - str.length(), stringBuilder.length());
            }
        };
        stringBuilder.append(str);
        stackRollback.push(action);
        return this;
    }

    /**
     * Метод выполняет замену с использованием переданных значений start, end и str.
     * Замененный диапазон символов затем добавляется в стек stackrollback.
     *
     * @param start строка, которая будет вставлена в этот диапазон.
     * @param end   диапазон символов, которые нужно заменить.
     * @param str   диапазон символов, которые нужно заменить.
     * @return ссылка на текущий экземпляр
     */
    public RollbackStringBuilder replace(int start, int end, String str) {

        final String replaced = stringBuilder.substring(start, end);
        RollbackAction action = new RollbackAction() {

            @Override
            public void rollback() {
                stringBuilder.replace(start, end, replaced);
            }
        };
        stringBuilder.replace(start, end, str);
        stackRollback.push(action);
        return this;
    }

    /**
     * Метод выполняет откат действия (rollback) в объекте класса StringBuilder.
     * Вначале метод проверяет, не является ли стек stackRollback пустым. Если стек пуст, выбрасывается исключение rollbackStackException.
     * Затем метод извлекает верхний (последний добавленный) элемент из стека stackRollback с помощью метода pop() и сохраняет его в переменную action.
     *
     * @return возвращает текущий обьект rollbackStringBuilder
     */
    public RollbackStringBuilder rollback() {
        if (stackRollback.isEmpty()) {
            throw new RollbackStackException();
        }
        Object action = stackRollback.pop();
        if (action instanceof RollbackAction) {
            ((RollbackAction) action).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

}