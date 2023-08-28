package malakhova;

/**
 * Класс, реализующий аналог StringBuilder
 * Класс RollbackStringBuilder состоит из нескольких полей:
 * arrayStack - аналог стека, созданный в предыдущем задании
 * rollbackBuilder - StringBuilder на основе которого создается RollbackStringBuilder
 * В данном классе содержится интерфейс, объявляющий метод rollback, и конструктор
 * Также в классе RollbackStringBuilder есть переопределенный метод toString и расширенный метод rollback
 * Класс RollbackStringBuilder также реализует свои методы:
 * append-добавляющий строку к имеющейся,
 * replace - метод, которому передаются значения, начиная с какого элемента вставить новую строку и до какого элемента удалить предыдущую
 * insert - метод, вставляющий новую строку в имеющуюся, по указанному адресу
 * reverse - метод, который разворачивает строку в обратном направлении
 */
public class RollbackStringBuilder {
    private final Stack arrayStack;
    private final StringBuilder rollbackBuilder;

    interface Action {
        void rollback();
    }

    public RollbackStringBuilder() {
        this.rollbackBuilder = new StringBuilder();
        this.arrayStack = new Stack();
    }

    /**
     * Метод, добавляющий новую строку в конец имеющейся
     *
     * @param s - строка, которую необходимо добавить к имеющейся
     * @return - получившаяся строка
     */
    public RollbackStringBuilder append(String s) {
        Action action = new Action() {
            @Override
            public void rollback() {
                rollbackBuilder.delete(rollbackBuilder.length() - s.length(), rollbackBuilder.length());
            }
        };
        arrayStack.push(action);
        rollbackBuilder.append(s);
        return this;
    }

    /**
     * Метод, разворачивающий строку в обратном направлении
     *
     * @return - получившаяся строка
     */
    public RollbackStringBuilder reverse() {
        Action action = new Action() {
            @Override
            public void rollback() {
                rollbackBuilder.reverse();
            }
        };
        arrayStack.push(action);
        rollbackBuilder.reverse();
        return this;
    }

    /**
     * Метод, вставляющий в исходную строку  новую по передаваемому в нем параметру
     *
     * @param offset - номер элемента по которому нужно добавить новую строку
     * @param str    - добавляемая строка
     * @return - получившаяся строка
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                rollbackBuilder.delete(offset, offset + str.length());
            }
        };
        if (offset < 0 || offset > rollbackBuilder.length()) {
            throw new IllegalArgumentException();
        }
        arrayStack.push(action);
        rollbackBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод, который удаляет часть строки по указанным параметрам и добавляет на ее место новую строку
     *
     * @param start - индекс символа с которого удаляется часть строки
     * @param end   - индекс символа до которого удаляется часть строки
     * @param str   - добавляемая строка
     * @return - получившаяся строка
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        Action action = new Action() {
            String copyOfBuilder = rollbackBuilder.substring(start, end);

            @Override
            public void rollback() {
                rollbackBuilder.delete(start, start + str.length());
                rollbackBuilder.insert(start, copyOfBuilder);
            }
        };
        if (start < 0 || end > rollbackBuilder.length() || end < start) {
            throw new StringIndexOutOfBoundsException();
        }
        arrayStack.push(action);
        rollbackBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод, отменяющий выполнение предыдущего метода
     *
     * @return - получившаяся строка
     */
    public RollbackStringBuilder rollback() {
        if (arrayStack.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Object popElements = arrayStack.pop();
        if (popElements instanceof Action) {
            ((Action) popElements).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return rollbackBuilder.toString();
    }
}
