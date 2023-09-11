package tumbaev.validtor.request_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.exception.UnknownUriException;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;

public class UriValidator implements HttpRequestValidator {

    /**
     * Checks, that request uri matches the uri of this http context.
     *
     * @param exchange request exchange
     * @throws UnknownUriException uri doesn't match.
     */
    @Override
    public void validate(HttpExchange exchange) {
        String uri = exchange.getRequestURI().toString();
        if (!uri.equals(exchange.getHttpContext().getPath())) {
            throw new UnknownUriException(String.format("Uri %s doesn't exist", uri));
        }
    }
}
