package tumbaev.validtor.request_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.exception.MethodNotAllowedException;
import tumbaev.backbone.util.constant.HttpMethod;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;

public class PostMethodValidator implements HttpRequestValidator {

    /**
     * Checks that this is a POST request.
     *
     * @param exchange request exchange
     * @throws MethodNotAllowedException if this is not a POST request
     */
    @Override
    public void validate(HttpExchange exchange) {
        if (!HttpMethod.POST.getName().equals(exchange.getRequestMethod())) {
            throw new MethodNotAllowedException("Only POST method is allowed for this endpoint");
        }
    }
}
