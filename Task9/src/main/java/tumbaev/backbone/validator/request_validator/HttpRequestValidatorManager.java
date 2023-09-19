package tumbaev.backbone.validator.request_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.ResponseEntity;

import java.util.Optional;

public class HttpRequestValidatorManager {
    private HttpRequestValidator firstValidator;

    /**
     * No validation will be made if you use this constructor.
     */
    public HttpRequestValidatorManager() {
        this(null);
    }

    /**
     * @param firstValidator validator that will be called first
     */
    public HttpRequestValidatorManager(HttpRequestValidator firstValidator) {
        this.firstValidator = firstValidator;
    }

    /**
     * Invokes the chain of {@link HttpRequestValidator}s. and returns the response of validation.
     *
     * @param exchange http exchange
     * @return response entity if validation failed, empty otherwise
     */
    public Optional<ResponseEntity> proceedValidation(HttpExchange exchange) {
        if (firstValidator != null) {
            return firstValidator.proceedValidation(exchange);
        }
        return Optional.empty();
    }

    public void setFirstValidator(HttpRequestValidator firstValidator) {
        this.firstValidator = firstValidator;
    }
}
