package tumbaev.validator.request_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.util.ErrorResponseConverter;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;

import java.util.Optional;

public class UriValidator extends HttpRequestValidator {

    /**
     * Checks, that request uri matches the uri of this http context.
     *
     * @param exchange the HTTP exchange to validate
     * @return {@link Optional} of {@link ResponseEntity} if validation failed, empty otherwise
     */
    @Override
    public Optional<ResponseEntity> validate(HttpExchange exchange) {
        String uri = exchange.getRequestURI().toString();
        if (uri.equals(exchange.getHttpContext().getPath())) {
            return Optional.empty();
        }
        ErrorResponse error = new ErrorResponse(
                HttpCode.NOT_FOUND,
                "Unknown uri",
                String.format("Uri %s doesn't exist", uri)
        );
        return Optional.of(ErrorResponseConverter.toResponseEntity(error));
    }
}
