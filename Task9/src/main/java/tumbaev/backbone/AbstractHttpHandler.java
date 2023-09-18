package tumbaev.backbone;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.exception_handler.ExceptionHandlerManager;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;
import tumbaev.backbone.validator.request_validator.HttpRequestValidatorManager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * A handler which is invoked to process HTTP exchanges. Each HTTP exchange is handled by one of these handlers
 */
public abstract class AbstractHttpHandler implements HttpHandler {
    protected static final Logger logger = Logger.getLogger(AbstractExceptionHandler.class.getName());

    private final ExceptionHandlerManager exceptionHandler;
    private final HttpRequestValidatorManager requestValidator;

    protected AbstractHttpHandler(ExceptionHandlerManager exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        this.requestValidator = new HttpRequestValidatorManager();
    }

    protected void setFirstValidator(HttpRequestValidator firstValidator) {
        this.requestValidator.setFirstValidator(firstValidator);
    }

    /**
     * <strong> Do not override</strong>.
     * <br/>
     * Use {@link #process} to process the request and
     * {@link ExceptionHandlerManager} to handle any exceptions occurred during work of {@link #process}.
     * <br/>
     * Exceptions occurred in {@link HttpRequestValidatorManager} will also be handled by exception handler manager.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException Can occur when sending response to the client. Cannot be handled manually.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ResponseEntity response;
        try {
            response = requestValidator.proceedValidation(exchange)
                    .orElseGet(() -> process(exchange));
        } catch (Throwable e) {
            response = exceptionHandler.handle(e);
        }
        sendResponse(exchange, response);
    }

    /**
     * Sends to client information from response
     *
     * @param exchange exchange of the request
     * @param response information that should be sent to client
     * @throws IOException can occur when working with exchange
     */
    private void sendResponse(HttpExchange exchange, ResponseEntity response) throws IOException {
        putHeadersIfPresent(exchange, response);
        exchange.sendResponseHeaders(response.getCode().asInt(), 0);
        writeBodyIfPresent(exchange, response);
        exchange.close();
    }

    private void putHeadersIfPresent(HttpExchange exchange, ResponseEntity response) {
        response.getHeaders().ifPresent((headers) -> {
            Headers responseHeaders = exchange.getResponseHeaders();
            headers.forEach((h, v) -> responseHeaders.put(h.getName(), v));
        });
    }

    private void writeBodyIfPresent(HttpExchange exchange, ResponseEntity response) throws IOException {
        if (response.getBody().isEmpty()) {
            return;
        }
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBody().get().getBytes());
            logger.info("Successfully sent response: " + response.getBody());
        }
    }

    /**
     * Contains main logic for processing request.
     * <br/>
     * {@link HttpRequestValidatorManager} will be invoked before this function,
     * so exchange will be fully validated when it arrives here.
     * <br/>
     * Exceptions occurred in this method and in {@link HttpRequestValidatorManager}
     * will be processed by {@link ExceptionHandlerManager}
     *
     * @param exchange exchange of this request, validated by {@link HttpRequestValidatorManager}
     * @return response entity, containing information that will be sent to client.
     */
    protected abstract ResponseEntity process(HttpExchange exchange);
}
