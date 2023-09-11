package tumbaev.exception_handler.request_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.exception_handler.ErrorResponse;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.exception.UnsupportedMediaTypeException;

import java.util.List;
import java.util.Map;

public class UnsupportedMediaTypeExceptionHandler extends AbstractExceptionHandler<UnsupportedMediaTypeException> {
    @Override
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        UnsupportedMediaTypeException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.UNSUPPORTED_MEDIA_TYPE,
                "Provided media types is not supported",
                ex.getMessage()
        );
        logger.info("User sent unsupported media type: " + e.getMessage());

        return ResponseEntity
                .code(response.getCode())
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(errorResponseToJson(response))
                .build();
    }
}
