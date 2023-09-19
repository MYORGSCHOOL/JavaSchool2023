package tumbaev.backbone.validator.request_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.ResponseEntity;

import java.util.Optional;

/**
 * Abstract class for chain of validators
 */
public abstract class HttpRequestValidator {
    private HttpRequestValidator nextValidator;

    /**
     * Method to be called on the first validator in the chain.
     * Validates the request and passes it to the next validator if validation was successful.
     *
     * @param exchange http exchange
     * @return response entity if validation failed, empty otherwise
     * @see HttpRequestValidatorManager
     */
    Optional<ResponseEntity> proceedValidation(HttpExchange exchange) {
        Optional<ResponseEntity> responseOpt = validate(exchange);
        if (responseOpt.isPresent()) {
            return responseOpt;
        }
        if (nextValidator != null) {
            return nextValidator.proceedValidation(exchange);
        }
        return Optional.empty();
    }

    public void setNextValidator(HttpRequestValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    /**
     * Validates the HTTP exchange.
     *
     * @param exchange the HTTP exchange to validate
     * @return {@link Optional} of {@link ResponseEntity} if validation failed, empty otherwise
     */
    public abstract Optional<ResponseEntity> validate(HttpExchange exchange);
}
