package tumbaev.backbone.validator.request_validator;

import com.sun.net.httpserver.HttpExchange;

import java.util.List;

/**
 * Invokes given {@link HttpRequestValidator}s
 *
 * @see HttpRequestValidator
 */
public class HttpRequestValidatorManager {
    private final List<HttpRequestValidator> validators;

    /**
     * @param validators Validators that will validate incoming request.
     *                   Validators will be invoked in exact same order they are provided in the list.
     */
    public HttpRequestValidatorManager(List<HttpRequestValidator> validators) {
        this.validators = validators;
    }

    /**
     * Iterates through list of validators and invokes them in the exact same order they were provided in the list.
     *
     * @param exchange exchange of incoming request
     */
    public void validate(HttpExchange exchange) {
        validators.forEach(v -> v.validate(exchange));
    }
}
