package tumbaev.validator.request_validator;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.util.ErrorResponseConverter;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpMethod;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;

import java.util.Optional;

public class PostMethodValidator extends HttpRequestValidator {
    /**
     * Checks that this is a POST request.
     *
     * @param exchange the HTTP exchange to validate
     * @return {@link Optional} of {@link ResponseEntity} if validation failed, empty otherwise
     */
    @Override
    public Optional<ResponseEntity> validate(HttpExchange exchange) {
        if (HttpMethod.POST.getName().equals(exchange.getRequestMethod())) {
            return Optional.empty();
        }

        ErrorResponse error = new ErrorResponse(
                HttpCode.METHOD_NOT_ALLOWED,
                "Method is not allowed",
                "Only POST method is allowed for this endpoint"
        );

        return Optional.of(ErrorResponseConverter.toResponseEntity(error));
    }
}
