package alexenko.server.exception;

/**
 * Исключение используется, когда не удалось прочитать то сообщение, которое прислал клиент.
 */
public class GetMessageFromClientException extends RuntimeException {

    /**
     * Сообщение об ошибке.
     */
    private static final String MESSAGE = "Не удалось получить сообщение от клиента";

    /**
     * Конструктор инициализирующий исключение.
     */
    public GetMessageFromClientException() {
        super(MESSAGE);
    }
}
