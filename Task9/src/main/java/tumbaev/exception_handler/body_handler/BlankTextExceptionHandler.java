package tumbaev.exception_handler.body_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.exception_handler.ErrorResponse;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.exception.BlankTextException;

import java.util.List;
import java.util.Map;

public class BlankTextExceptionHandler extends AbstractExceptionHandler<BlankTextException> {
    @Override
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        BlankTextException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.BAD_REQUEST,
                "Incorrect data in the request body",
                ex.getMessage()
        );
        logger.info("User sent incorrect data in the request body: " + ex.getMessage());

        return ResponseEntity
                .code(response.getCode())
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(errorResponseToJson(response))
                .build();
    }
}
