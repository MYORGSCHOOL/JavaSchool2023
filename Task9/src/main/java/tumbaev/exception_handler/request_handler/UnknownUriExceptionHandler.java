package tumbaev.exception_handler.request_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.exception_handler.ErrorResponse;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.exception.UnknownUriException;

import java.util.List;
import java.util.Map;

public class UnknownUriExceptionHandler extends AbstractExceptionHandler<UnknownUriException> {
    @Override
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        UnknownUriException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.NOT_FOUND,
                "Unknown uri",
                ex.getMessage()
        );
        logger.info("User tried to access unknown uri: " + ex.getMessage());

        return ResponseEntity
                .code(response.getCode())
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(errorResponseToJson(response))
                .build();
    }
}
