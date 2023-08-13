package proskurina;


import proskurina.exceptions.EmptyRollbackStackException;

/**
 * Класс, реализующий StringBuilder с возможностью отмены изменений.
 */
public class RollbackStringBuilder {
    /**
     * интерфейс для отмены изменений.
     */
    interface Action {
        void rollback();
    }
    
    /**
     * Ссылка на оригинальный StringBuilder.
     */
    private final StringBuilder stringBuilder;
    /**
     * Стек изменений.
     */
    private final Stack changes;
    
    public RollbackStringBuilder() {
        changes = new Stack();
        stringBuilder = new StringBuilder();
    }
    
    /**
     * Вставляет строку с указанного смещения.
     *
     * @param offset смещение, с которого сторка будет вставлена
     * @param str    строка
     * @return ссылка на этот экземпляр
     * @throws StringIndexOutOfBoundsException если offset меньше 0 или больше length()
     */
    public RollbackStringBuilder insert(int offset, String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(offset, offset + str.length());
            }
        };
        stringBuilder.insert(offset, str);
        changes.push(action);
        return this;
    }
    
    /**
     * Переворачивает строку.
     *
     * @return ссылка на этот экземпляр
     */
    public RollbackStringBuilder reverse() {
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.reverse();
            }
        };
        stringBuilder.reverse();
        changes.push(action);
        return this;
    }
    
    /**
     * Добавляет к строке указанную строку.
     *
     * @param str добавляемая строка
     * @return ссылка на этот экземпляр
     */
    public RollbackStringBuilder append(String str) {
        Action action = new Action() {
            @Override
            public void rollback() {
                stringBuilder.delete(stringBuilder.length() - str.length(),
                        stringBuilder.length());
            }
        };
        stringBuilder.append(str);
        changes.push(action);
        return this;
    }
    
    /**
     * Заменяет подстроку c start до end (не включительно) указанной строкой.
     *
     * @param start индекс начала
     * @param end   индекс конца
     * @param str   строка для замены
     * @return ссылка на этот экземпляр
     * @throws StringIndexOutOfBoundsException если start меньше 0, больше length() или end
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        
        final String replaced = stringBuilder.substring(start, end);
        Action action = new Action() {
            
            @Override
            public void rollback() {
                stringBuilder.replace(start, end, replaced);
            }
        };
        stringBuilder.replace(start, end, str);
        changes.push(action);
        return this;
    }
    
    /**
     * Отменяет одно изменение у StringBuilder.
     *
     * @return ссылка на этот экземпляр
     * @throws EmptyRollbackStackException если стек изменений пуст
     */
    public RollbackStringBuilder rollback() {
        if (changes.isEmpty()) {
            throw new EmptyRollbackStackException();
        }
        Object action = changes.pop();
        if (action instanceof Action) {
            ((Action) action).rollback();
        }
        return this;
    }
    
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
    
    /**
     * Возвращает текущее количество символов.
     *
     * @return количество символов
     */
    public int lenght() {
        return stringBuilder.length();
    }
}