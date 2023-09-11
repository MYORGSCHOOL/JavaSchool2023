package tumbaev.backbone.validator.request_validator;

import com.sun.net.httpserver.HttpExchange;

/**
 * Validates incoming http request
 *
 * @see HttpRequestValidatorManager
 */
@FunctionalInterface
public interface HttpRequestValidator {

    /**
     * Validates incoming http request
     *
     * @param exchange request exchange
     */
    void validate(HttpExchange exchange);
}
