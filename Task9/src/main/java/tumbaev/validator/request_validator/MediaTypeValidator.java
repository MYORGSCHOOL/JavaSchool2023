package tumbaev.validator.request_validator;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.util.ErrorResponseConverter;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;

import java.util.List;
import java.util.Optional;

public class MediaTypeValidator extends HttpRequestValidator {

    /**
     * Validates the HTTP exchange by checking the content type and accept headers.
     * <br/>
     * 'Content-type' must contain be 'application/json', 'Accept' - either contain 'application/json' or not be present.
     *
     * @param exchange the HTTP exchange to validate
     * @return {@link Optional} of {@link ResponseEntity} if validation failed, empty otherwise
     */
    @Override
    public Optional<ResponseEntity> validate(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        List<String> contentTypes = headers.get(HttpHeader.CONTENT_TYPE.getName());
        List<String> accepts = headers.get(HttpHeader.ACCEPT.getName());

        String details = null;
        if (contentTypes == null || !contentTypes.contains(Mime.APPLICATION_JSON.getName())) {
            details = "Only 'application/json' content type is supported";
        }
        if (accepts != null && !accepts.contains(Mime.APPLICATION_JSON.getName())) {
            details = "'Accept' header must either contain 'application/json', or not be present";
        }
        if (details == null) {
            return Optional.empty();
        }

        ErrorResponse error = new ErrorResponse(
                HttpCode.UNSUPPORTED_MEDIA_TYPE,
                "Provided media type is not supported",
                details
        );

        return Optional.of(ErrorResponseConverter.toResponseEntity(error));
    }
}
