package abdullaeva;

public class RollbackStringBuilder {
    /**
     * Оригинальный StringBuilder, который будем делегировать
     */
    private final StringBuilder originalStringBuilder;
    /**
     * Собственный Стэк из task3 для хранения действий, совершённых над StringBuilder
     */
    private final Stack stack;

    /**
     * Начальный размер для стэка
     */
    private final static int SIZE = 15;

    public RollbackStringBuilder() {
        this.originalStringBuilder = new StringBuilder();
        this.stack = new Stack(SIZE);
    }

    /**
     * Интерфейс для реализации отката действий, совершенных над строкой StringBuilder'а
     */
    public interface RollbackActions {
        void rollback();
    }

    /**
     * Метод для вставки строки в строку StringBuilder'а с определённым смещением.
     * Сначала с помощью анонимного класса создается "экземпляр" интерфейса, описывающего обратные действия,
     * совершённые над StringBuilder'ом.
     * Реализовывается метод интерфейса rollback, который откатывает изменение в строке в контексте вызываемого
     * метода insert - удаляет вставленную в строку StringBuilder'а сроку с места вставки.
     * Реализация интерфейса фиксируется в action и затем сохраняется в стэке.
     * Исполняется основная работа метода insert - с места указателя offset производится вставка в сроку
     * StringBuilder'а другой строки.
     *
     * @param offset - указатель на место в последовательности символов текущей строки для вставки.
     * @param str    - строка, которую необходимо вставить со смещением offset.
     * @return - ссылка на объект StringBuilder - обновлённая строка.
     */
    public RollbackStringBuilder insert(int offset, String str) {
        RollbackActions action = new RollbackActions() {
            @Override
            public void rollback() {
                originalStringBuilder.delete(offset, offset + originalStringBuilder.length());
            }
        };
        this.stack.push(action);
        this.originalStringBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод для отображения строки StringBuilder'а в обратном порядке.
     * Сначала с помощью анонимного класса создается "экземпляр" интерфейса, описывающего обратные действия,
     * совершённые над StringBuilder'ом.
     * Реализовывается метод интерфейса rollback, который откатывает изменение в строке в контексте вызываемого
     * метода reverse - строка StringBuilder'а перезаписывается в исходном порядке.
     * Реализация интерфейса фиксируется в action и затем сохраняется в стэке.
     * Исполняется основная работа метода reverse - строка StringBuilder'а перезаписывается в обратном порядке.
     *
     * @return - ссылка на объект StringBuilder - обновлённая строка.
     */
    public RollbackStringBuilder reverse() {
        RollbackActions action = new RollbackActions() {
            @Override
            public void rollback() {
                originalStringBuilder.reverse();
            }
        };
        this.stack.push(action);
        this.originalStringBuilder.reverse();
        return this;
    }

    /**
     * Метод для вставки строки в конец строки StringBuilder'а.
     * Сначала с помощью анонимного класса создается "экземпляр" интерфейса, обратные описывающего действия,
     * совершённые над StringBuilder'ом.
     * Реализовывается метод интерфейса rollback, который откатывает изменение в строке в контексте вызываемого
     * метода append - удаление строки из конца строки StringBuilder'а.
     * Реализация интерфейса фиксируется в action и затем сохраняется в стэке.
     * Исполняется основная работа метода append - в конец строки StringBuilder'а вставляется строка.
     *
     * @param str - строка, которую необходимо вставить в конец строки StringBuilder'а.
     * @return - ссылка на объект StringBuilder - обновлённая строка.
     */
    public RollbackStringBuilder append(String str) {
        RollbackActions action = new RollbackActions() {
            @Override
            public void rollback() {
                originalStringBuilder.delete(originalStringBuilder.length() - str.length(), originalStringBuilder.
                        length());
            }
        };
        this.stack.push(action);
        this.originalStringBuilder.append(str);
        return this;
    }

    /**
     * Метод для замены подстроки в строке StringBuilder'а с определенного места.
     * Создается строка originalSubStr, в которой будет храниться исходная подстрока,
     * полученная из строки StringBuilder'а с помощью метода substring.
     * С помощью анонимного класса создается "экземпляр" интерфейса, описывающего обратные действия,
     * совершённые над StringBuilder'ом.
     * Реализовывается метод интерфейса rollback, который откатывает изменение в строке в контексте вызываемого
     * метода append - удаление из строки StringBuilder'а изменённой подстроки и добавление исходной подстроки.
     * Реализация интерфейса фиксируется в action и затем сохраняется в стэке.
     * Исполняется основная работа метода replace - изменяется подстрока в строке StringBuilder'а на указанном месте.
     *
     * @param start  - индекс начала подстроки, которую нужно заменить.
     * @param end    - индекс конца подстроки, которую нужно заменить.
     * @param subStr - подстрока, на которую нужно заменить исходную подстроку.
     * @return - ссылка на объект StringBuilder - обновлённая строка.
     */
    public RollbackStringBuilder replace(int start, int end, String subStr) {
        final String originalSubStr = originalStringBuilder.substring(start, end);
        RollbackActions action = new RollbackActions() {
            @Override
            public void rollback() {
                originalStringBuilder.delete(start, start + subStr.length());
                originalStringBuilder.insert(start, originalSubStr);
            }
        };
        this.stack.push(action);
        this.originalStringBuilder.replace(start, end, subStr);
        return this;
    }

    /**
     * Метод для отката действий, совершённых над строкой StringBuilder'а.
     * Происходит проверка на пустоту стэка, в котором хранятся обратные действия,
     * совершённым над строкой StringBuilder'а. Если действий не было, отлавливается ошибка и выводится сообщение.
     * Достаётся объект из стэка - крайнее действие над строкой StringBuilder'а. Если объект принадлежит реализации
     * интерфейса RollbackActions, вызывается метод соответсвующий крайней оперции метод rollback.
     *
     * @return - ссылка на объект StringBuilder - обновлённая строка.
     */
    public RollbackStringBuilder rollback() {
        if (this.stack.isEmpty()) {
            throw new RollbackException("Error: No action to rollback");
        }
        Object popAction = this.stack.pop();
        if (popAction instanceof RollbackActions) {
            ((RollbackActions) popAction).rollback();
        }
        return this;
    }

    @Override
    public String toString() {
        return originalStringBuilder.toString();
    }
}
