package tumbaev.exception_handler.request_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.exception_handler.ErrorResponse;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.exception.MethodNotAllowedException;

import java.util.List;
import java.util.Map;

public class MethodNotAllowedExceptionHandler extends AbstractExceptionHandler<MethodNotAllowedException> {
    @Override
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        MethodNotAllowedException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.METHOD_NOT_ALLOWED,
                "Method is not allowed",
                ex.getMessage()
        );
        logger.info("User sent method that is not allowed: " + ex.getMessage());

        return ResponseEntity
                .code(response.getCode())
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(errorResponseToJson(response))
                .build();
    }
}
