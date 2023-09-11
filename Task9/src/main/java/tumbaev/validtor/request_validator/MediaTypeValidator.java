package tumbaev.validtor.request_validator;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;
import tumbaev.exception.UnsupportedMediaTypeException;

import java.util.List;

public class MediaTypeValidator implements HttpRequestValidator {

    /**
     * Validates the HTTP exchange by checking the content type and accept headers.
     * <br/>
     * 'Content-type' must contain be 'application/json', 'Accept' - either contain 'application/json' or not be present.
     *
     * @param exchange the HTTP exchange to validate
     * @throws UnsupportedMediaTypeException wrong 'Content-type' or 'Accept' headers
     */
    @Override
    public void validate(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        List<String> contentTypes = headers.get(HttpHeader.CONTENT_TYPE.getName());
        List<String> accepts = headers.get(HttpHeader.ACCEPT.getName());

        if (contentTypes == null || !contentTypes.contains(Mime.APPLICATION_JSON.getName())) {
            throw new UnsupportedMediaTypeException("Only 'application/json' content type is supported");
        }
        if (accepts != null && !accepts.contains(Mime.APPLICATION_JSON.getName())) {
            throw new UnsupportedMediaTypeException(
                    "'Accept' header must either contain 'application/json', or not be present");
        }
    }
}
