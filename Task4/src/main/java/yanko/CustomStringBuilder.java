package yanko;

import java.util.EmptyStackException;

/**
 * класс, реализующий модифицированный StringBuilder с функцией отката действий
 */
public class CustomStringBuilder {
    /**
     * ссылка на оригинальный StringBuilder
     */
    private final StringBuilder stringBuilder;
    /**
     * ссылка на стек из task3
     */
    private final Stack stack;

    /**
     * анонимный интерфейс для реализации откатов
     */
    interface Action {
        /**
         * метод отката действий
         */
        void rollback();
    }

    public CustomStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stack = new Stack();
    }

    /**
     * вставляет строку с заданного индекса
     *
     * @param offset индекс, с которого надо вставить строку
     * @param str строка
     * @return ссылка на экземпляр класса CustomStringBuilder
     */
    public CustomStringBuilder insert(int offset, String str) {
        if(stringBuilder.length() < offset || offset < 0) {
            System.out.println("index value is incorrect");
            throw new IndexOutOfBoundsException();
        }
        Action action = new Action () {
            /**
             * переопределение метода отката для метода insert
             */
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
     * переставляет символы строки в обратном порядке
     *
     * @return ссылка на экземпляр класса CustomStringBuilder
     */
    public CustomStringBuilder reverse() {
        if(stringBuilder.length() == 0) {
            System.out.println("stringbuilder is empty, nothing to reverse");
            throw new IndexOutOfBoundsException();
        }
        Action action = new Action () {
            /**
             * переопределение метода отката для метода reverse
             */
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
     * добавляет строку в конец
     *
     * @param str строка
     * @return ссылка на экземпляр класса CustomStringBuilder
     */
    public CustomStringBuilder append(String str) {
        Action action = new Action () {
            /**
             * переопределение метода отката для метода append
             */
            @Override
            public void rollback() {
                stringBuilder.delete(stringBuilder.length() - str.length(), stringBuilder.length());
            }
        };
        stack.push(action);
        stringBuilder.append(str);
        return this;
    }

    /**
     * заменяет символы в заданном диапазоне на символы заданной строки
     *
     * @param start индекс начала
     * @param end индекс конца
     * @param str строка
     * @return ссылка на экземпляр класса CustomStringBuilder
     */
    public CustomStringBuilder replace(int start, int end, String str) {
        if(stringBuilder.length() < start) {
            System.out.println("index value is incorrect");
            throw new IndexOutOfBoundsException();
        }
        String temp = stringBuilder.toString();
        Action action = new Action () {
            /**
             * переопределение метода отката для метода replace
             */
            @Override
            public void rollback() {
                stringBuilder.delete(0, stringBuilder.length());
                stringBuilder.append(temp);
            }
        };
        stack.push(action);
        stringBuilder.replace(start, end, str);
        return this;
    }

    /**
     * осуществляет откат последнего выполненного действия
     *
     * @return ссылка на экземпляр класса CustomStringBuilder
     */
    public CustomStringBuilder rollback() {
        if(stack.top() == null) {
            System.out.println("stringbuilder is empty, nothing to rollback");
            throw new EmptyStackException();
        }
        Object pop = stack.pop();
        if (pop instanceof Action) {
            ((Action) pop).rollback();
        }
        return this;
    }

    /**
     * выводит экземпляр класса как строку
     *
     * @return строка
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
