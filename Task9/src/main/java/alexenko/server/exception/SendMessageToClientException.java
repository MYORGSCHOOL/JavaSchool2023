package alexenko.server.exception;

/**
 * Исключение используется, когда не удалось отправить сообщение клиенту.
 */
public class SendMessageToClientException extends RuntimeException {

    /**
     * Сообщение об ошибке.
     */
    private static final String MESSAGE = "Не удалось отправить сообщение пользователю";

    /**
     * Конструктор инициализирующий исключение.
     */
    public SendMessageToClientException() {
        super(MESSAGE);
    }
}
