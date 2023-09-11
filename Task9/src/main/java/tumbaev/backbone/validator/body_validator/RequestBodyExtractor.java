package tumbaev.backbone.validator.body_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.util.JsonConverter;
import tumbaev.exception.BlankBodyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Extracts body from provided http exchange of the request
 *
 * @param <T> type of body
 */
public class RequestBodyExtractor<T> {
    private final static Logger logger = Logger.getLogger(RequestBodyExtractor.class.getName());

    /**
     * Class of a body. Needed to convert json to object of type {@link T}.
     */
    private final Class<T> bodyClass;

    /**
     * @param bodyClass required for conversion
     */
    public RequestBodyExtractor(Class<T> bodyClass) {
        this.bodyClass = bodyClass;
    }

    /**
     * Extract body of the request and converts it to object.
     *
     * @param exchange http exchange of request
     * @return object of type {@link T}
     * @throws BlankBodyException                                 if provided body was null or empty
     * @throws tumbaev.backbone.exception.JsonConversionException failed to convert the body
     */
    public T extract(HttpExchange exchange) {
        String body = getBodyAsString(exchange);
        if (body == null || body.isBlank()) {
            throw new BlankBodyException("Provided body was null or empty");
        }

        return JsonConverter.toObject(body, bodyClass);
    }

    /**
     * Extract request body from http exchange and converts it to string
     *
     * @param exchange http exchange of the request
     * @return body of the request
     */
    private String getBodyAsString(HttpExchange exchange) {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String body = br.lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            br.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to close BufferedReader after reading request body");
        }
        return body;
    }
}
