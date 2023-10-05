package proskurina.server;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс-синглтон, позволяющий создать единственный {@code ConcurrentMap},
 * где ключ - это имя пользователя, а значение - связанный с ним {@code PrintWriter}.
 */
public class UsernameToPrintWriter {
    /**
     * Содержит пользователей и их {@code PrintWriter}.
     */
    private final ConcurrentMap<String, PrintWriter> originalMap;
    
    private UsernameToPrintWriter() {
        this.originalMap = new ConcurrentHashMap<>();
    }
    
    /**
     * Класс отложенной инициализации.
     */
    private static class UsernameToPrintWriterHolder {
        private static final UsernameToPrintWriter INSTANCE = new UsernameToPrintWriter();
    }
    
    /**
     * Возвращает экземпляр {@code UsernameToPrintWriter}.
     *
     * @return экземпляр {@code UsernameToPrintWriter}
     */
    public static UsernameToPrintWriter getInstance() {
        return UsernameToPrintWriterHolder.INSTANCE;
    }
    
    /**
     * Возвращает {@code ConcurrentMap} с пользователями.
     *
     * @return возвращает {@code ConcurrentMap} с пользователями
     */
    public ConcurrentMap<String, PrintWriter> getConcurrentMap() {
        return originalMap;
    }
    
}
