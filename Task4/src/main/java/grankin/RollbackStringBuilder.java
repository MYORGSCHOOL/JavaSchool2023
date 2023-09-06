package grankin;

/**
 * Класс с реализацией возврата предыдущих действий для строк
 */
public class RollbackStringBuilder {

    /**
     * Внутреннее хранение строки
     */
    private final StringBuilder stringBuilder;

    /**
     * Стек для хранения действий
     */
    private final MyStack myStack;

    /**
     * Стартовый размер стека
     */
    private static final int START_SIZE = 100;

    /**
     * Конструктор по-умолчанию
     */
    public RollbackStringBuilder() {
        this.stringBuilder = new StringBuilder();
        myStack = new MyStack(START_SIZE);
    }

    /**
     * Вставка строки после определенного номера элемента в строке
     *
     * @param offset - индекс от начала строки на место которого будет производится вставка
     * @param str    - вставляемая подстрока
     * @return - указатель на себя
     */
    public RollbackStringBuilder insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(offset, offset + str.length());
            }
        };
        myStack.push(action);
        return this;
    }

    /**
     * Метод переворота строки
     *
     * @return - указатель на себя
     */
    public RollbackStringBuilder reverse() {
        stringBuilder.reverse();
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.reverse();
            }
        };
        myStack.push(action);
        return this;
    }

    /**
     * Добавление подстроки в конец строки
     *
     * @param str - подстрока
     * @return - указатель на себя
     */
    public RollbackStringBuilder append(String str) {
        int start = stringBuilder.length();
        stringBuilder.append(str);
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(start, stringBuilder.length());
            }
        };
        myStack.push(action);
        return this;
    }

    /**
     * Замена подстроки на новую подстроку в строке
     *
     * @param start - начельный индекс оригинльной подстроки
     * @param end   - конечный индекс оригинальной подстроки
     * @param str   - новая подстрока
     * @return - указатель на себя
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        char[] tmp = new char[end - start];
        stringBuilder.getChars(start, end, tmp, 0);
        stringBuilder.replace(start, end, str);
        Action action = new Action() {
            @Override
            public void rollback() {
                String charToString = new String(tmp);
                stringBuilder.replace(start, str.length(), charToString);
            }
        };
        myStack.push(action);
        return this;
    }

    /**
     * Удалить символ по индексу
     *
     * @param index - индекс удаляемого символа
     * @return - указатель на себя
     */
    public RollbackStringBuilder deleteCharAt(int index) {
        char[] tmp = new char[1];
        stringBuilder.getChars(index, index + 1, tmp, 0);
        stringBuilder.deleteCharAt(index);
        Action action = new Action() {
            @Override
            public void rollback() {
                String charToString = new String(tmp);
                stringBuilder.insert(index, charToString);
            }
        };
        myStack.push(action);
        return this;
    }

    /**
     * Вернуться на шаг назад
     *
     * @return - указатель на себя
     */
    public RollbackStringBuilder rollback() {
        Object pop = myStack.pop();
        if (pop instanceof Action) {
            ((Action) pop).rollback();
        }
        return this;
    }

    /**
     * Преобразовать внутреннее представление строки в String
     *
     * @return - строка
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
