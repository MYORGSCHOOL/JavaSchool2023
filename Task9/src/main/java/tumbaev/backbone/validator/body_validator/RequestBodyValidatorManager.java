package tumbaev.backbone.validator.body_validator;

import com.sun.net.httpserver.HttpExchange;

import java.util.List;

/**
 * Extracts the body from given http exchange invokes {@link RequestBodyValidator}s.
 *
 * @param <T> type of the body
 * @see RequestBodyExtractor
 * @see RequestBodyValidator
 */
public class RequestBodyValidatorManager<T> {
    private final RequestBodyExtractor<T> extractor;
    private final List<RequestBodyValidator<T>> validators;

    /**
     * @param validators Validators that will validate the body, extracted from http exchange.
     *                   Validators will be invoked in exact same order they are provided in the list.
     * @param bodyClass  required to extract the body
     */
    public RequestBodyValidatorManager(List<RequestBodyValidator<T>> validators, Class<T> bodyClass) {
        this.validators = validators;
        this.extractor = new RequestBodyExtractor<>(bodyClass);
    }

    /**
     * Extract the body from exchange and invokes validators to validate the body.
     * <br/>
     * Validators are invoked in the exact same order they were provided in the list.
     *
     * @param httpExchange exchange
     * @return request body from http exchange
     */
    public T validateBodyOf(HttpExchange httpExchange) {
        T body = extractor.extract(httpExchange);
        for (RequestBodyValidator<T> validator : validators) {
            validator.validate(body);
        }
        return body;
    }
}
