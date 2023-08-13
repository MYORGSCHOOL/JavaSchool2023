package druzhinin;

import java.util.EmptyStackException;

/**
 * Класс откатываемого StringBuilder'а
 * (то есть действия всех методов, возвращающих RollbackStringBuilder (кроме rollback)
 * можно откатить, то есть вернуть RollbackStringBuilder в состояние до выполнения этих методов).
 *
 * @author Дружинин Артем
 */
public class RollbackStringBuilder {
    /**
     * StringBuilder, которому на выполнение
     * будут делегироваться основные действия со строкой.
     */
    private final StringBuilder originalBuilder;

    /**
     * Стек действий для отката на шаг
     * назад (одно действие для отмены изменений одного метода).
     */
    private final Stack cancelActionStack;

    public RollbackStringBuilder() {
        this("");
    }

    public RollbackStringBuilder(String str) {
        originalBuilder = new StringBuilder(str);
        cancelActionStack = new Stack();
    }

    /**
     * Метод для вставки строки в текущую последовательность символов внутри originalBuilder.
     * Вставка строки начинается с индекса {@code offset}, а все символы после {@code offset}
     * сдвигаются вправо на величину, равную длине строки {@code str}.
     *
     * @param offset Отступ от нулевого символа. С индекса {@code offset} начинается вставка строки.
     * @param str Строка, которая вставляется. Если строка равна {@code null}, то в последовательность
     *            символов вставляется строка {@code "null"}.
     * @return Возвращает {@code this}, то есть объект RollbackStringBuilder, для которого был вызван данный метод.
     * @throws StringIndexOutOfBoundsException при передаче
     * в качестве {@code offset} значения меньше нуля или больше текущей длины строки.
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Cancellable cancellable =
                () -> originalBuilder.delete(offset, offset + str.length());
        cancelActionStack.push(cancellable);
        originalBuilder.insert(offset, str);
        return this;
    }

    /**
     * Метод для реверсирования текущей символьной последовательности, которая хранится в {@code originalBuilder}.
     *
     * @return Возвращает {@code this}, то есть объект RollbackStringBuilder, для которого был вызван данный метод.
     */
    public RollbackStringBuilder reverse() {
        Cancellable cancellable = originalBuilder::reverse;
        cancelActionStack.push(cancellable);
        originalBuilder.reverse();
        return this;
    }

    /**
     * Метод для добавления строки {@code str} в конец текущей
     * символьной последовательности, которая хранится в {@code originalBuilder}.
     *
     * @param str Строка для добавления в конец. Если строка равна {@code null}, то в конец последовательности
     *            символов вставляется строка {@code "null"}.
     * @return Возвращает {@code this}, то есть объект RollbackStringBuilder, для которого был вызван данный метод.
     */
    public RollbackStringBuilder append(String str) {
        Cancellable cancellable =
                () -> originalBuilder.delete(originalBuilder.length() - str.length(),
                originalBuilder.length());
        cancelActionStack.push(cancellable);
        originalBuilder.append(str);
        return this;
    }

    /**
     * Метод для замены подстроки, начинающейся с индекса
     * {@code start} и заканчивающейся на индексе {@code end - 1}, строкой {@code str}.
     *
     * @param start Индекс начала заменяемой подстроки (включительно).
     * @param end Индекс конца заменяемой подстроки (исключительно).
     * @param str Строка, на которую должна быть заменена данная подстрока.
     * @return Возвращает {@code this}, то есть объект RollbackStringBuilder, для которого был вызван данный метод.
     * @throws NullPointerException при передаче {@code null} вместо параметра {@code str}.
     * @throws StringIndexOutOfBoundsException если аргумент {@code start} меньше нуля,
     * больше текущей длины строки или больше аргумента {@code end}.
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        String initialString = originalBuilder.toString();
        Cancellable cancellable =
                () -> originalBuilder.replace(0, originalBuilder.length(), initialString);
        cancelActionStack.push(cancellable);
        originalBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Метод для отката предыдущего действия (первого действия, не являющегося {@code rollback()}).
     *
     * @return Возвращает {@code this}, то есть объект RollbackStringBuilder, для которого был вызван данный метод.
     * @throws EmptyStackException при попытке откатить действие при пустом стеке действий для отката.
     */
    public RollbackStringBuilder rollback() {
        if (cancelActionStack.top() == null)
            throw new EmptyStackException();
        Object pop = cancelActionStack.pop();
        if (pop instanceof Cancellable) {
            ((Cancellable) pop).rollback();
        }
        return this;
    }

    /**
     * Метод для представления содержимого {@code originalBuilder} в виде строки.
     *
     * @return Строковое представление содержимого {@code originalBuilder}.
     */
    @Override
    public String toString() {
        return originalBuilder.toString();
    }
}
