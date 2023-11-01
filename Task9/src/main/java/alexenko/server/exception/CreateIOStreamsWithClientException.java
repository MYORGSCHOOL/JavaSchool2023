package alexenko.server.exception;

/**
 * Исключение используется, когда не удалось создать IO потоки с тем клиентом, который хочет подключиться.
 */
public class CreateIOStreamsWithClientException extends RuntimeException {

    /**
     * Сообщение об ошибке.
     */
    private static final String MESSAGE = "Не удалось создать IO потоки с клиентом.";

    /**
     * Конструктор инициализирующий исключение.
     */
    public CreateIOStreamsWithClientException() {
        super(MESSAGE);
    }
}
